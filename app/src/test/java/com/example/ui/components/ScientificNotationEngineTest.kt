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
}
