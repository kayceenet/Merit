package com.example.ui.components

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ScientificNotationEngineTest {

  @Test
  fun `normalizes scientific e-notation and latex symbols`() {
    val normalized = ScientificNotationEngine.normalizeMarkup("e = 1.6e-19 C, N_A = 6.02e23")
    assertTrue(normalized.contains("1.6 × 10^{−19}"))
    assertTrue(normalized.contains("6.02 × 10^{23}"))

    val latex = ScientificNotationEngine.normalizeMarkup("""$\theta = 30^\circ, \Delta x \rightarrow \infty$""")
    assertEquals("θ = 30°, Δ x → ∞", latex)
  }

  @Test
  fun `handles sigma sum quad bar matrix and nested sqrt latex cleanly`() {
    val stats = ScientificNotationEngine.normalizeMarkup("""s = \sqrt{\frac{\Sigma(x - \bar{x})^2}{N}}""")
    assertTrue(stats.startsWith("s = √("))
    assertTrue(stats.contains("Σ"))
    assertTrue(stats.contains("x\u0304"))

    val vec = ScientificNotationEngine.tokenizeEquationWithFractions("""\tan(\alpha) = \frac{\Sigma F_y}{\Sigma F_x}""")
    assertEquals(FormulaToken.Inline("tan(α) = "), vec[0])
    assertEquals(FormulaToken.StackedFraction("Σ F_y", "Σ F_x"), vec[1])

    val quadFormula = ScientificNotationEngine.normalizeMarkup("""R = \sqrt{F_1^2 + F_2^2 + 2 F_1 F_2 \cos(\theta)} \quad (90^\circ)""")
    assertTrue(!quadFormula.contains("\\quad"))
    assertTrue(quadFormula.contains("90°"))
  }

  @Test
  fun `formats chemical formulas and subscripts without swallowing trailing atoms`() {
    val annotated = ScientificNotationEngine.formatAnnotated("Na_2CO_3 + 2HCl -> 2NaCl + H_2O + CO_2")
    assertEquals("Na2CO3 + 2HCl → 2NaCl + H2O + CO2", annotated.text)

    val ch3cooh = ScientificNotationEngine.formatAnnotated("CH_3COOH")
    assertEquals("CH3COOH", ch3cooh.text)
    // Verify subscript span covers only "3" (index 2..3), not "COOH"
    val subSpans = ch3cooh.spanStyles.filter { it.item.baselineShift != null }
    assertEquals(1, subSpans.size)
    assertEquals(2, subSpans.first().start)
    assertEquals(3, subSpans.first().end)

    // Verify F_1^2 separates subscript 1 and superscript 2 cleanly
    val f1Sq = ScientificNotationEngine.formatAnnotated("F_1^2")
    val fSpans = f1Sq.spanStyles.filter { it.item.baselineShift != null }
    assertEquals(2, fSpans.size)
    assertTrue(fSpans[0].item.baselineShift!!.multiplier < 0f)
    assertTrue(fSpans[1].item.baselineShift!!.multiplier > 0f)
  }

  @Test
  fun `tokenizes latex and algebraic fractions for stacked rendering while keeping units inline`() {
    val latexTokens = ScientificNotationEngine.tokenizeEquationWithFractions("""T = \frac{2u \sin\theta}{g}""")
    assertEquals(2, latexTokens.size)
    assertEquals(FormulaToken.Inline("T = "), latexTokens[0])
    assertEquals(FormulaToken.StackedFraction("2u sinθ", "g"), latexTokens[1])

    val algebraicTokens =
      ScientificNotationEngine.tokenizeEquationWithFractions("(C_A · V_A) / (C_B · V_B) = n_A / n_B")
    assertTrue(algebraicTokens.any { it == FormulaToken.StackedFraction("C_A · V_A", "C_B · V_B") })
    assertTrue(algebraicTokens.any { it == FormulaToken.StackedFraction("n_A", "n_B") })

    val unitTokens = ScientificNotationEngine.tokenizeEquationWithFractions("v = 40 m/s")
    assertTrue(unitTokens.none { it is FormulaToken.StackedFraction })
  }

  @Test
  fun `renders advanced latex expressions from user audit without broken breaks`() {
    val pctTokens =
      ScientificNotationEngine.tokenizeEquationWithFractions(
        """\%Error = \frac{|E|}{|\text{True}|} \times 100\%"""
      )
    assertEquals(3, pctTokens.size)
    assertEquals(FormulaToken.Inline("%Error = "), pctTokens[0])
    assertEquals(FormulaToken.StackedFraction("|E|", "|True|"), pctTokens[1])
    assertEquals(FormulaToken.Inline(" × 100%"), pctTokens[2])

    val rootPower = ScientificNotationEngine.normalizeMarkup("""a^{p/q} = (\sqrt[q]{a})^p""")
    assertTrue(rootPower.contains("^{p/q}"))
    assertTrue(rootPower.contains("^{q}√(a)^{p}") || rootPower.contains("q") && rootPower.contains("√"))

    val logDiff = ScientificNotationEngine.tokenizeEquationWithFractions("""\log_a\!(x/y) = \log_a x - \log_a y""")
    assertTrue(logDiff.any { it == FormulaToken.StackedFraction("x", "y") })

    val lines =
      ScientificNotationEngine.splitFormulaLines(
        """\%Error = \frac{|E|}{|\text{True}|} \times 100\% \;|\; n = \frac{m}{M}"""
      )
    assertEquals(2, lines.size)

    val evalSin = ScientificExpressionEvaluator.evaluate("sin(30)")
    assertEquals(0.5, evalSin.getOrThrow(), 1e-6)

    val evalLog = ScientificExpressionEvaluator.evaluate("2^3 + sqrt(16) + log(100)")
    assertEquals(14.0, evalLog.getOrThrow(), 1e-6)

    val evalDivZero = ScientificExpressionEvaluator.evaluate("5 / 0")
    assertTrue(evalDivZero.isFailure)
  }

  @Test
  fun `tokenizes latex matrices and piecewise cases into multi-line MatrixBlock tokens`() {
    val matrixTokens =
      ScientificNotationEngine.tokenizeEquationWithFractions(
        """A = \begin{pmatrix} a & b \\ c & d \end{pmatrix}"""
      )
    assertEquals(2, matrixTokens.size)
    assertEquals(FormulaToken.Inline("A = "), matrixTokens[0])
    assertEquals(
      FormulaToken.MatrixBlock(
        leftDelim = "(",
        rightDelim = ")",
        rows = listOf(listOf("a", "b"), listOf("c", "d")),
        isCases = false,
      ),
      matrixTokens[1],
    )

    val casesTokens =
      ScientificNotationEngine.tokenizeEquationWithFractions(
        """|x| = \begin{cases} x & x \ge 0 \\ -x & x < 0 \end{cases}"""
      )
    assertEquals(2, casesTokens.size)
    assertEquals(FormulaToken.Inline("|x| = "), casesTokens[0])
    assertEquals(
      FormulaToken.MatrixBlock(
        leftDelim = "{",
        rightDelim = "",
        rows = listOf(listOf("x", "x ≥ 0"), listOf("-x", "x < 0")),
        isCases = true,
      ),
      casesTokens[1],
    )

    val inlineNormalized =
      ScientificNotationEngine.normalizeMarkup("""\det\begin{bmatrix} 2 & 1 \\ 4 & 3 \end{bmatrix}""")
    assertEquals("det[ 2 1 ; 4 3 ]", inlineNormalized)
  }

  @Test
  fun `scientific calculator supports DEG and RAD trig, inverse trig, factorial, and ten powers`() {
    assertEquals(1.0, ScientificExpressionEvaluator.evaluate("sin(90)", isDegreeMode = true).getOrThrow(), 1e-6)
    assertEquals(1.0, ScientificExpressionEvaluator.evaluate("sin(π÷2)", isDegreeMode = false).getOrThrow(), 1e-6)
    assertEquals(30.0, ScientificExpressionEvaluator.evaluate("sin⁻¹(0.5)", isDegreeMode = true).getOrThrow(), 1e-6)
    assertEquals(60.0, ScientificExpressionEvaluator.evaluate("cos⁻¹(0.5)", isDegreeMode = true).getOrThrow(), 1e-6)
    assertEquals(45.0, ScientificExpressionEvaluator.evaluate("tan⁻¹(1)", isDegreeMode = true).getOrThrow(), 1e-6)
    assertEquals(120.0, ScientificExpressionEvaluator.evaluate("5!").getOrThrow(), 1e-6)
    assertEquals(2000.0, ScientificExpressionEvaluator.evaluate("2×10^3").getOrThrow(), 1e-6)
  }
}
