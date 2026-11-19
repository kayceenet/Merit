package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CodeFormulaText
import com.example.ui.theme.CodeSurface
import com.example.ui.theme.CourseraAzureAccent
import com.example.ui.theme.LocalBoldEquations
import com.example.ui.theme.LocalLineSpacingMultiplier

sealed interface FormulaToken {
  data class Inline(val text: String) : FormulaToken
  data class StackedFraction(val numerator: String, val denominator: String) : FormulaToken
  data class MatrixBlock(
    val leftDelim: String,
    val rightDelim: String,
    val rows: List<List<String>>,
    val isCases: Boolean = false,
  ) : FormulaToken
}

object ScientificNotationEngine {

  private val unicodeSuperscripts =
    mapOf(
      '⁰' to "0",
      '¹' to "1",
      '²' to "2",
      '³' to "3",
      '⁴' to "4",
      '⁵' to "5",
      '⁶' to "6",
      '⁷' to "7",
      '⁸' to "8",
      '⁹' to "9",
      '⁺' to "+",
      '⁻' to "−",
      'ᵃ' to "a",
      'ᵇ' to "b",
      'ᶜ' to "c",
      'ᵈ' to "d",
      'ᵉ' to "e",
      'ᶠ' to "f",
      'ᵍ' to "g",
      'ʰ' to "h",
      'ⁱ' to "i",
      'ʲ' to "j",
      'ᵏ' to "k",
      'ˡ' to "l",
      'ᵐ' to "m",
      'ⁿ' to "n",
      'ᵒ' to "o",
      'ᵖ' to "p",
      'ʳ' to "r",
      'ˢ' to "s",
      'ᵗ' to "t",
      'ᵘ' to "u",
      'ᵛ' to "v",
      'ʷ' to "w",
      'ˣ' to "x",
      'ʸ' to "y",
      'ᶻ' to "z",
    )

  private val unicodeSubscripts =
    mapOf(
      '₀' to "0",
      '₁' to "1",
      '₂' to "2",
      '₃' to "3",
      '₄' to "4",
      '₅' to "5",
      '₆' to "6",
      '₇' to "7",
      '₈' to "8",
      '₉' to "9",
      '₊' to "+",
      '₋' to "−",
      'ₐ' to "a",
      'ₑ' to "e",
      'ₕ' to "h",
      'ᵢ' to "i",
      'ⱼ' to "j",
      'ₖ' to "k",
      'ₗ' to "l",
      'ₘ' to "m",
      'ₙ' to "n",
      'ₒ' to "o",
      'ₚ' to "p",
      'ᵣ' to "r",
      'ₛ' to "s",
      'ₜ' to "t",
      'ᵤ' to "u",
      'ᵥ' to "v",
      'ₓ' to "x",
      'ᵧ' to "y",
    )

  // Longest tokens first so prefixes like \le never shadow \left or \leq
  private val latexReplacements =
    listOf(
      "\\rightleftharpoons" to "⇌",
      "\\longrightarrow" to "→",
      "\\leftrightarrow" to "↔",
      "\\Rightarrow" to "⇒",
      "\\rightarrow" to "→",
      "\\leftarrow" to "←",
      "\\downarrow" to "↓",
      "\\uparrow" to "↑",
      "\\therefore" to "∴",
      "\\because" to "∵",
      "\\subseteq" to "⊆",
      "\\implies" to "⇒",
      "\\emptyset" to "∅",
      "\\varepsilon" to "ε",
      "\\epsilon" to "ε",
      "\\subset" to "⊂",
      "\\forall" to "∀",
      "\\exists" to "∃",
      "\\qquad" to "     ",
      "\\quad" to "   ",
      "\\angle" to "∠",
      "\\approx" to "≈",
      "\\degree" to "°",
      "^{\\circ}" to "°",
      "^\\circ" to "°",
      "\\equiv" to "≡",
      "\\infty" to "∞",
      "\\Lambda" to "Λ",
      "\\lambda" to "λ",
      "\\Omega" to "Ω",
      "\\omega" to "ω",
      "\\propto" to "∝",
      "\\right" to "",
      "\\left" to "",
      "\\Sigma" to "Σ",
      "\\sigma" to "σ",
      "\\Theta" to "Θ",
      "\\theta" to "θ",
      "\\times" to "×",
      "\\alpha" to "α",
      "\\Delta" to "Δ",
      "\\delta" to "δ",
      "\\gamma" to "γ",
      "\\cdot" to "·",
      "\\beta" to "β",
      "\\dots" to "…",
      "\\perp" to "⊥",
      "\\iff" to "⇔",
      "\\geq" to "≥",
      "\\leq" to "≤",
      "\\neq" to "≠",
      "\\Phi" to "Φ",
      "\\phi" to "φ",
      "\\psi" to "ψ",
      "\\rho" to "ρ",
      "\\sim" to "∼",
      "\\sum" to "Σ",
      "\\tau" to "τ",
      "\\eta" to "η",
      "\\cap" to "∩",
      "\\cup" to "∪",
      "\\div" to "÷",
      "\\int" to "∫",
      "\\max" to "max",
      "\\det" to "det",
      "\\sin" to "sin",
      "\\cos" to "cos",
      "\\tan" to "tan",
      "\\log" to "log",
      "\\ln" to "ln",
      "\\in" to "∈",
      "\\big" to "",
      "\\ge" to "≥",
      "\\le" to "≤",
      "\\mu" to "μ",
      "\\mp" to "∓",
      "\\pi" to "π",
      "\\pm" to "±",
      "\\to" to "→",
      "\\%" to "%",
      "\\&" to "&",
      "\\$" to "$",
      "\\!" to "",
      "\\:" to " ",
      "\\," to " ",
      "\\;" to " ",
      "<=>" to "⇌",
      "=>" to "⇒",
      "->" to "→",
      "<=" to "≤",
      ">=" to "≥",
      "!=" to "≠",
    )

  /**
   * Splits a combined formula block into individual equation rows without breaking absolute-value
   * bars like `|E|`, `|kA|`, `|x|`, or electrochemical cell bridges.
   */
  fun splitFormulaLines(formula: String): List<String> =
    formula
      .split(Regex("""\n|\\;\|\\;|\s{2,}\|\s{2,}"""))
      .map { it.trim() }
      .filter { it.isNotEmpty() }

  /**
   * Normalizes LaTeX, AsciiMath, Markdown math delimiters, scientific e-notation, and chemical
   * syntax into a uniform internal representation with `^{...}` / `_{...}` markers before rendering.
   */
  fun normalizeMarkup(raw: String): String =
    preNormalizeCore(raw, collapseFractions = true)

  private fun preNormalizeWithoutCollapsingFrac(raw: String): String =
    preNormalizeCore(raw, collapseFractions = false)

  private fun preNormalizeCore(raw: String, collapseFractions: Boolean): String {
    var s = raw
    // Strip outer Markdown math delimiters ($...$, $$...$$, \(...\), \[...\])
    s = s.replace("$$", "").replace("\\(", "").replace("\\)", "").replace("\\[", "").replace("\\]", "")
    s = Regex("""\$([^$]+)\$""").replace(s) { it.groupValues[1] }
    s = s.replace("{}", "")

    // Replace matrices \begin{pmatrix|bmatrix|vmatrix|cases} ... \end{...} cleanly when flattening
    s = replaceMatrices(s, preserveBlocks = !collapseFractions)

    // Replace \xrightarrow{label} and \underset{sub}{base}
    s = replaceCommandWithSingleBraceArg(s, "\\xrightarrow") { arg -> " →[$arg] " }
    s = replaceTwoBraceCommand(s, "\\underset") { sub, base -> "$base ($sub)" }
    s = replaceCommandWithSingleBraceArg(s, "\\pmod") { arg -> " (mod $arg)" }
    s = replaceCommandWithSingleBraceArg(s, "\\overline") { arg -> applyCombiningOverline(arg) }
    s = replaceCommandWithSingleBraceArg(s, "\\bar") { arg -> applyCombiningOverline(arg) }
    s = replaceCommandWithSingleBraceArg(s, "\\hat") { arg -> "${arg.trim()}\u0302" }
    s = replaceCommandWithSingleBraceArg(s, "\\vec") { arg -> "${arg}\u20D7" }

    // Replace \text{...}, \mathrm{...}, \mathbf{...}
    s = replaceCommandWithSingleBraceArg(s, "\\text") { it }
    s = replaceCommandWithSingleBraceArg(s, "\\mathrm") { it }
    s = replaceCommandWithSingleBraceArg(s, "\\mathbf") { it }

    // Replace \sqrt[n]{...} and \sqrt{...} with full nested brace support and sqrt(...)
    s = replaceNestedSqrt(s)
    s = Regex("""\bsqrt\(([^)]+)\)""").replace(s) { m -> "√(${m.groupValues[1]})" }

    // Apply symbol replacements (longest matches first)
    for ((token, symbol) in latexReplacements) {
      s = s.replace(token, symbol)
    }

    // Scientific E-notation: e.g., 1.6e-19 or 6.022E23 -> 1.6 × 10^{-19}
    s =
      Regex("""(\d+(?:\.\d+)?)[eE]([+-]?\d+)""")
        .replace(s) { m ->
          val mantissa = m.groupValues[1]
          val exp = m.groupValues[2].removePrefix("+").replace("-", "−")
          "$mantissa × 10^{$exp}"
        }

    // Convert runs of Unicode superscript characters into ^{...}
    val superRegex = Regex("""[⁰¹²³⁴⁵⁶⁷⁸⁹⁺⁻ᵃᵇᶜᵈᵉᶠᵍʰⁱʲᵏˡᵐⁿᵒᵖʳˢᵗᵘᵛʷˣʸᶻ]+""")
    s =
      superRegex.replace(s) { m ->
        val decoded = m.value.map { unicodeSuperscripts[it] ?: it.toString() }.joinToString("")
        "^{$decoded}"
      }

    // Convert runs of Unicode subscript characters into _{...}
    val subRegex = Regex("""[₀₁₂₃₄₅₆₇₈₉₊₋ₐₑₕᵢⱼₖₗₘₙₒₚᵣₛₜᵤᵥₓᵧ]+""")
    s =
      subRegex.replace(s) { m ->
        val decoded = m.value.map { unicodeSubscripts[it] ?: it.toString() }.joinToString("")
        "_{$decoded}"
      }

    if (collapseFractions) {
      s = collapseInlineLatexFractions(s)
    }

    return s.replace(Regex(""" {2,}"""), " ")
  }

  private fun applyCombiningOverline(arg: String): String {
    val trimmed = arg.trim()
    return if (trimmed.length == 1) "$trimmed\u0304"
    else trimmed.map { if (it.isLetterOrDigit()) "$it\u0304" else it.toString() }.joinToString("")
  }

  private fun replaceMatrices(input: String, preserveBlocks: Boolean = false): String {
    if (preserveBlocks) return input
    val regex = Regex("""\\begin\{([pbv]?matrix|cases)\}(.*?)\\end\{\1\}""", RegexOption.DOT_MATCHES_ALL)
    return regex.replace(input) { match ->
      val env = match.groupValues[1]
      val body = match.groupValues[2].trim()
      val rows =
        body.split("\\\\")
          .map { row -> row.split("&").joinToString("   ") { it.trim() } }
          .filter { it.isNotEmpty() }
      when (env) {
        "cases" -> "{ ${rows.joinToString(" ; ")} }"
        "pmatrix" -> "( ${rows.joinToString(" ; ")} )"
        "vmatrix" -> "| ${rows.joinToString(" ; ")} |"
        else -> "[ ${rows.joinToString(" ; ")} ]"
      }
    }
  }

  private fun normalizeBlockCell(rawCell: String): String =
    preNormalizeCore(rawCell.trim(), collapseFractions = true)

  private fun replaceCommandWithSingleBraceArg(
    input: String,
    command: String,
    transform: (String) -> String,
  ): String {
    val needle = "$command{"
    if (!input.contains(needle)) return input
    val sb = StringBuilder()
    var i = 0
    while (i < input.length) {
      if (input.startsWith(needle, i)) {
        val openIdx = i + command.length
        val closeIdx = findMatchingBrace(input, openIdx)
        if (closeIdx != -1) {
          val inner = input.substring(openIdx + 1, closeIdx)
          sb.append(transform(inner))
          i = closeIdx + 1
          continue
        }
      }
      sb.append(input[i])
      i++
    }
    return sb.toString()
  }

  private fun replaceTwoBraceCommand(
    input: String,
    command: String,
    transform: (String, String) -> String,
  ): String {
    val needle = "$command{"
    if (!input.contains(needle)) return input
    val sb = StringBuilder()
    var i = 0
    while (i < input.length) {
      if (input.startsWith(needle, i)) {
        val open1 = i + command.length
        val close1 = findMatchingBrace(input, open1)
        if (close1 != -1 && close1 + 1 < input.length && input[close1 + 1] == '{') {
          val close2 = findMatchingBrace(input, close1 + 1)
          if (close2 != -1) {
            val arg1 = input.substring(open1 + 1, close1)
            val arg2 = input.substring(close1 + 2, close2)
            sb.append(transform(arg1, arg2))
            i = close2 + 1
            continue
          }
        }
      }
      sb.append(input[i])
      i++
    }
    return sb.toString()
  }

  private fun replaceNestedSqrt(input: String): String {
    var current = input
    var guard = 0
    while (current.contains("\\sqrt") && guard < 10) {
      guard++
      val sb = StringBuilder()
      var i = 0
      var replacedAny = false
      while (i < current.length) {
        if (current.startsWith("\\sqrt[", i)) {
          val closeBracket = current.indexOf(']', i + 6)
          if (closeBracket != -1 && closeBracket + 1 < current.length && current[closeBracket + 1] == '{') {
            val closeBrace = findMatchingBrace(current, closeBracket + 1)
            if (closeBrace != -1) {
              val rootIndex = current.substring(i + 6, closeBracket).trim()
              val inner = current.substring(closeBracket + 2, closeBrace).trim()
              val radical = if (inner.length <= 2 && !inner.contains('\\')) "√$inner" else "√($inner)"
              sb.append("^{$rootIndex}$radical")
              i = closeBrace + 1
              replacedAny = true
              continue
            }
          }
        }
        if (current.startsWith("\\sqrt{", i)) {
          val openIdx = i + 5
          val closeIdx = findMatchingBrace(current, openIdx)
          if (closeIdx != -1) {
            val inner = current.substring(openIdx + 1, closeIdx)
            val formatted = if (inner.length <= 2 && !inner.contains('\\')) "√$inner" else "√($inner)"
            sb.append(formatted)
            i = closeIdx + 1
            replacedAny = true
            continue
          }
        }
        sb.append(current[i])
        i++
      }
      current = sb.toString()
      if (!replacedAny) break
    }
    return current
  }

  private fun collapseInlineLatexFractions(input: String): String {
    var current = input
    var guard = 0
    while (current.contains("\\frac{") && guard < 8) {
      guard++
      val sb = StringBuilder()
      var i = 0
      var replacedAny = false
      while (i < current.length) {
        if (current.startsWith("\\frac{", i)) {
          val numClose = findMatchingBrace(current, i + 5)
          if (numClose != -1 && numClose + 1 < current.length && current[numClose + 1] == '{') {
            val denClose = findMatchingBrace(current, numClose + 1)
            if (denClose != -1) {
              val num = current.substring(i + 6, numClose).trim()
              val den = current.substring(numClose + 2, denClose).trim()
              val wrappedNum = if (num.length > 2 && ' ' in num) "($num)" else num
              val wrappedDen = if (den.length > 2 && ' ' in den) "($den)" else den
              sb.append("$wrappedNum/$wrappedDen")
              i = denClose + 1
              replacedAny = true
              continue
            }
          }
        }
        sb.append(current[i])
        i++
      }
      current = sb.toString()
      if (!replacedAny) break
    }
    return current
  }

  /**
   * Builds a Compose AnnotatedString supporting Markdown **bold**, `code`, LaTeX/AsciiMath
   * superscripts (`^{...}`, `^2`, `^-3`), and subscripts (`_{...}`, `_x`, `_A`, `_2`).
   */
  fun formatAnnotated(
    raw: String,
    baseFontSizeSp: Float = 15f,
    codeFontFamily: FontFamily = FontFamily.Monospace,
  ): AnnotatedString {
    val text = normalizeMarkup(raw)
    val scriptSize = (baseFontSizeSp * 0.70f).coerceAtLeast(10f).sp

    return buildAnnotatedString {
      var i = 0
      while (i < text.length) {
        // Bold Markdown **...**
        if (i + 1 < text.length && text[i] == '*' && text[i + 1] == '*') {
          val close = text.indexOf("**", i + 2)
          if (close != -1) {
            pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
            appendFormattedCore(text.substring(i + 2, close), scriptSize)
            pop()
            i = close + 2
            continue
          }
        }

        // Inline code `...`
        if (text[i] == '`') {
          val close = text.indexOf('`', i + 1)
          if (close != -1) {
            pushStyle(SpanStyle(fontFamily = codeFontFamily, fontWeight = FontWeight.SemiBold))
            appendFormattedCore(text.substring(i + 1, close), scriptSize)
            pop()
            i = close + 1
            continue
          }
        }

        val nextMarker = findNextMarkdownMarker(text, i)
        val chunk = text.substring(i, nextMarker)
        appendFormattedCore(chunk, scriptSize)
        i = nextMarker
      }
    }
  }

  private fun findNextMarkdownMarker(text: String, start: Int): Int {
    var idx = start + 1
    while (idx < text.length) {
      if (text[idx] == '`') return idx
      if (idx + 1 < text.length && text[idx] == '*' && text[idx + 1] == '*') return idx
      idx++
    }
    return text.length
  }

  private fun AnnotatedString.Builder.appendFormattedCore(
    chunk: String,
    scriptSize: TextUnit,
  ) {
    var i = 0
    var lastWasSubscript = false
    while (i < chunk.length) {
      val c = chunk[i]

      // Handle grouped or single-token superscript ^ (supports ^{...}, ^(...), and ^2 / ^-3)
      if (c == '^' && i + 1 < chunk.length) {
        if (chunk[i + 1] == '{') {
          val close = findMatchingBrace(chunk, i + 1)
          if (close != -1) {
            val inner = chunk.substring(i + 2, close).replace("-", "−")
            if (lastWasSubscript) append("\u200A")
            pushStyle(
              SpanStyle(
                fontSize = scriptSize,
                baselineShift = BaselineShift(0.40f),
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.02.em,
              )
            )
            append(inner)
            pop()
            i = close + 1
            lastWasSubscript = false
            continue
          }
        } else if (chunk[i + 1] == '(') {
          val close = findMatchingParen(chunk, i + 1)
          if (close != -1) {
            val inner = chunk.substring(i + 2, close).replace("-", "−")
            if (lastWasSubscript) append("\u200A")
            pushStyle(
              SpanStyle(
                fontSize = scriptSize,
                baselineShift = BaselineShift(0.40f),
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.02.em,
              )
            )
            append(inner)
            pop()
            i = close + 1
            lastWasSubscript = false
            continue
          }
        } else {
          // Single-token exponent like ^2, ^-3, ^+
          var end = i + 1
          if (end < chunk.length && (chunk[end] == '-' || chunk[end] == '+' || chunk[end] == '−')) {
            end++
          }
          while (end < chunk.length && chunk[end].isDigit()) {
            end++
          }
          if (end == i + 1 && end < chunk.length && (chunk[end].isLetter() || chunk[end] == '+')) {
            end++
          }
          if (end > i + 1) {
            val exp = chunk.substring(i + 1, end).replace("-", "−")
            if (lastWasSubscript) append("\u200A")
            pushStyle(
              SpanStyle(
                fontSize = scriptSize,
                baselineShift = BaselineShift(0.40f),
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.02.em,
              )
            )
            append(exp)
            pop()
            i = end
            lastWasSubscript = false
            continue
          }
        }
      }

      // Handle grouped or short identifier subscript _
      if (c == '_' && i + 1 < chunk.length && i > 0 && !chunk[i - 1].isWhitespace()) {
        if (chunk[i + 1] == '{') {
          val close = findMatchingBrace(chunk, i + 1)
          if (close != -1) {
            val inner = chunk.substring(i + 2, close)
            pushStyle(
              SpanStyle(
                fontSize = scriptSize,
                baselineShift = BaselineShift(-0.32f),
                fontWeight = FontWeight.Medium,
              )
            )
            append(inner)
            pop()
            i = close + 1
            lastWasSubscript = true
            continue
          }
        } else if (chunk[i + 1] == '(') {
          val close = findMatchingParen(chunk, i + 1)
          if (close != -1) {
            val inner = chunk.substring(i + 2, close)
            pushStyle(
              SpanStyle(
                fontSize = scriptSize,
                baselineShift = BaselineShift(-0.32f),
                fontWeight = FontWeight.Medium,
              )
            )
            append(inner)
            pop()
            i = close + 1
            lastWasSubscript = true
            continue
          }
        } else if (chunk[i + 1].isDigit()) {
          var end = i + 1
          while (end < chunk.length && chunk[end].isDigit()) {
            end++
          }
          val sub = chunk.substring(i + 1, end)
          pushStyle(
            SpanStyle(
              fontSize = scriptSize,
              baselineShift = BaselineShift(-0.32f),
              fontWeight = FontWeight.Medium,
            )
          )
          append(sub)
          pop()
          i = end
          lastWasSubscript = true
          continue
        } else if (chunk[i + 1].isLetter()) {
          var end = i + 1
          while (end < chunk.length && chunk[end].isLetterOrDigit() && end - (i + 1) < 6) {
            end++
          }
          val sub = chunk.substring(i + 1, end)
          pushStyle(
            SpanStyle(
              fontSize = scriptSize,
              baselineShift = BaselineShift(-0.32f),
              fontWeight = FontWeight.Medium,
            )
          )
          append(sub)
          pop()
          i = end
          lastWasSubscript = true
          continue
        }
      }

      lastWasSubscript = false
      append(c)
      i++
    }
  }

  private fun findMatchingBrace(text: String, openIndex: Int): Int {
    var depth = 0
    for (i in openIndex until text.length) {
      when (text[i]) {
        '{' -> depth++
        '}' -> {
          depth--
          if (depth == 0) return i
        }
      }
    }
    return -1
  }

  /**
   * Splits a single equation expression into inline segments and true vertical stacked fractions.
   * Supports both LaTeX `\frac{num}{den}` and algebraic `(num)/(den)` or `n_A / n_B` expressions,
   * while preserving physical unit slashes like `m/s` or `mol/dm³` inline.
   */
  fun tokenizeEquationWithFractions(rawExpression: String): List<FormulaToken> {
    val prepared = preNormalizeWithoutCollapsingFrac(rawExpression.trim())
    val tokens = mutableListOf<FormulaToken>()
    val inlineBuffer = StringBuilder()

    fun flushInline() {
      val s = inlineBuffer.toString()
      if (s.isNotEmpty()) {
        tokens.add(FormulaToken.Inline(s))
        inlineBuffer.clear()
      }
    }

    var i = 0
    var braceDepth = 0
    while (i < prepared.length) {
      if (prepared[i] == '{') {
        braceDepth++
        inlineBuffer.append('{')
        i++
        continue
      }
      if (prepared[i] == '}') {
        braceDepth = (braceDepth - 1).coerceAtLeast(0)
        inlineBuffer.append('}')
        i++
        continue
      }

      // Case 0: Multi-line Matrix or Piecewise Cases \begin{pmatrix|bmatrix|vmatrix|cases} ... \end{...}
      if (braceDepth == 0 && prepared.startsWith("\\begin{", i)) {
        val envEnd = prepared.indexOf('}', i + 7)
        if (envEnd != -1) {
          val env = prepared.substring(i + 7, envEnd)
          if (env in setOf("pmatrix", "bmatrix", "vmatrix", "matrix", "cases")) {
            val endTag = "\\end{$env}"
            val endPos = prepared.indexOf(endTag, envEnd + 1)
            if (endPos != -1) {
              flushInline()
              val body = prepared.substring(envEnd + 1, endPos).trim()
              val rows =
                body.split("\\\\")
                  .map { row ->
                    row.split("&").map { cell -> normalizeBlockCell(cell) }
                  }
                  .filter { row -> row.any { it.isNotEmpty() } }
              val (leftDelim, rightDelim, isCases) =
                when (env) {
                  "pmatrix" -> Triple("(", ")", false)
                  "vmatrix" -> Triple("|", "|", false)
                  "cases" -> Triple("{", "", true)
                  else -> Triple("[", "]", false)
                }
              tokens.add(FormulaToken.MatrixBlock(leftDelim, rightDelim, rows, isCases))
              i = endPos + endTag.length
              continue
            }
          }
        }
      }

      // Case 1: LaTeX \frac{num}{den} (only stack top-level \frac; nested \frac inside √(...) or exponents collapses cleanly)
      if (braceDepth == 0 && prepared.startsWith("\\frac{", i)) {
        val numClose = findMatchingBrace(prepared, i + 5)
        if (numClose != -1 && numClose + 1 < prepared.length && prepared[numClose + 1] == '{') {
          val denClose = findMatchingBrace(prepared, numClose + 1)
          if (denClose != -1) {
            flushInline()
            val num = prepared.substring(i + 6, numClose).trim()
            val den = prepared.substring(numClose + 2, denClose).trim()
            tokens.add(FormulaToken.StackedFraction(num, den))
            i = denClose + 1
            continue
          }
        }
      }

      // Case 2: Balanced "(numerator) / (denominator)" or "(numerator)/denom" at braceDepth == 0
      if (braceDepth == 0 && prepared[i] == '(') {
        val closeParen = findMatchingParen(prepared, i)
        if (closeParen != -1) {
          var slashIdx = closeParen + 1
          while (slashIdx < prepared.length && prepared[slashIdx] == ' ') slashIdx++
          if (slashIdx < prepared.length && prepared[slashIdx] == '/') {
            var denStart = slashIdx + 1
            while (denStart < prepared.length && prepared[denStart] == ' ') denStart++
            if (denStart < prepared.length) {
              val num = prepared.substring(i + 1, closeParen).trim()
              if (prepared[denStart] == '(') {
                val denClose = findMatchingParen(prepared, denStart)
                if (denClose != -1) {
                  val den = prepared.substring(denStart + 1, denClose).trim()
                  flushInline()
                  tokens.add(FormulaToken.StackedFraction(num, den))
                  i = denClose + 1
                  continue
                }
              } else {
                var denEnd = denStart
                while (
                  denEnd < prepared.length &&
                    !prepared[denEnd].isWhitespace() &&
                    prepared[denEnd] !in charArrayOf('=', '+', '−', '|', ',', ';', ')')
                ) {
                  denEnd++
                }
                val den = prepared.substring(denStart, denEnd).trim()
                if (den.isNotEmpty() && !isUnitDenominator(num, den)) {
                  flushInline()
                  tokens.add(FormulaToken.StackedFraction(num, den))
                  i = denEnd
                  continue
                }
              }
            }
          }
        }
      }

      // Case 3: Algebraic spaced or subscripted ratio like "n_A / n_B" or "Δp/Δt" at braceDepth == 0
      if (braceDepth == 0 && prepared[i] == '/' && inlineBuffer.isNotEmpty()) {
        val leftStr = inlineBuffer.toString()
        val numStart = findFractionTermStart(leftStr)
        val numCandidate = leftStr.substring(numStart).trim()
        var denStart = i + 1
        while (denStart < prepared.length && prepared[denStart] == ' ') denStart++
        if (denStart < prepared.length && numCandidate.isNotEmpty()) {
          val denTokenEnd =
            if (prepared[denStart] == '(') {
              val close = findMatchingParen(prepared, denStart)
              if (close != -1) close + 1 else denStart
            } else {
              var end = denStart
              while (
                end < prepared.length &&
                  !prepared[end].isWhitespace() &&
                  prepared[end] !in charArrayOf('=', '+', '−', '|', ',', ';', ')')
              ) {
                end++
              }
              end
            }
          val rawDen = prepared.substring(denStart, denTokenEnd).trim()
          val cleanDen =
            if (rawDen.startsWith("(") && rawDen.endsWith(")") && rawDen.length > 2) {
              rawDen.substring(1, rawDen.length - 1)
            } else rawDen

          if (shouldStackSimpleFraction(numCandidate, cleanDen)) {
            val prefix = leftStr.substring(0, numStart)
            inlineBuffer.clear()
            inlineBuffer.append(prefix)
            flushInline()
            tokens.add(FormulaToken.StackedFraction(numCandidate, cleanDen))
            i = denTokenEnd
            continue
          }
        }
      }

      inlineBuffer.append(prepared[i])
      i++
    }
    flushInline()
    return tokens
  }

  private fun findMatchingParen(text: String, openIndex: Int): Int {
    var depth = 0
    for (i in openIndex until text.length) {
      when (text[i]) {
        '(' -> depth++
        ')' -> {
          depth--
          if (depth == 0) return i
        }
      }
    }
    return -1
  }

  private fun findFractionTermStart(left: String): Int {
    var idx = left.length - 1
    while (idx >= 0 && left[idx] == ' ') idx--
    while (idx >= 0) {
      val ch = left[idx]
      if (ch == ' ' || ch in charArrayOf('=', '+', '−', '|', '(', ',')) {
        return idx + 1
      }
      idx--
    }
    return 0
  }

  private fun isUnitDenominator(num: String, den: String): Boolean {
    val units = setOf("s", "s²", "s^{2}", "dm³", "dm^{3}", "cm³", "L", "h", "min", "K", "kg", "mol")
    return den in units && num in setOf("m", "km", "mol", "g", "J", "N", "C", "V")
  }

  private fun shouldStackSimpleFraction(num: String, den: String): Boolean {
    if (num.isEmpty() || den.isEmpty()) return false
    if (isUnitDenominator(num, den)) return false
    if (num.length >= 4 && num.all { it.isLetter() } && den.length >= 4 && den.all { it.isLetter() }) {
      return false
    }
    return num.any { it.isDigit() || it in "_^{}Δαβθπ" || it.isLetter() } &&
      den.length <= 12
  }
}

@Composable
fun ScientificText(
  text: String,
  modifier: Modifier = Modifier,
  style: TextStyle = LocalTextStyle.current,
  color: Color = Color.Unspecified,
  fontWeight: FontWeight? = null,
  fontStyle: FontStyle? = null,
  maxLines: Int = Int.MAX_VALUE,
  softWrap: Boolean = true,
  overflow: TextOverflow = TextOverflow.Clip,
  textAlign: TextAlign? = null,
) {
  val baseSp = if (style.fontSize.value > 0f) style.fontSize.value else 15f
  val lineMultiplier = LocalLineSpacingMultiplier.current
  val annotated =
    remember(text, baseSp) {
      ScientificNotationEngine.formatAnnotated(raw = text, baseFontSizeSp = baseSp)
    }
  val effectiveLineHeight =
    if (style.lineHeight != TextUnit.Unspecified && style.lineHeight.value >= baseSp * lineMultiplier) {
      style.lineHeight
    } else {
      (baseSp * lineMultiplier.coerceAtLeast(1.36f)).sp
    }
  Text(
    text = annotated,
    modifier = modifier,
    style = style.copy(lineHeight = effectiveLineHeight),
    color = color,
    fontWeight = fontWeight,
    fontStyle = fontStyle,
    maxLines = maxLines,
    softWrap = softWrap,
    overflow = overflow,
    textAlign = textAlign,
  )
}

@Composable
fun EquationDisplayCard(
  formula: String,
  modifier: Modifier = Modifier,
  containerColor: Color = CodeSurface,
  textColor: Color = CodeFormulaText,
  accentColor: Color = CourseraAzureAccent,
  badgeLabel: String? = null,
  onSaveFormula: ((String) -> Unit)? = null,
) {
  val expressions = remember(formula) { ScientificNotationEngine.splitFormulaLines(formula) }

  Row(
    modifier =
      modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
        .clip(RoundedCornerShape(14.dp))
        .background(containerColor)
        .border(1.dp, accentColor.copy(alpha = 0.28f), RoundedCornerShape(14.dp)),
  ) {
    Box(modifier = Modifier.width(4.dp).fillMaxHeight().background(accentColor))

    Column(
      modifier = Modifier.weight(1f).padding(horizontal = 16.dp, vertical = 14.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      if (!badgeLabel.isNullOrBlank() || onSaveFormula != null) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          if (!badgeLabel.isNullOrBlank()) {
            Surface(
              color = accentColor.copy(alpha = 0.16f),
              shape = RoundedCornerShape(6.dp),
            ) {
              Text(
                text = badgeLabel,
                style = MaterialTheme.typography.labelSmall,
                color = textColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp),
              )
            }
          } else {
            Box(Modifier)
          }
          if (onSaveFormula != null) {
            IconButton(
              onClick = { onSaveFormula(formula) },
              modifier = Modifier.minimumInteractiveComponentSize(),
            ) {
              Icon(
                imageVector = Icons.Outlined.BookmarkAdd,
                contentDescription = "Save formula to notebook",
                tint = accentColor,
              )
            }
          }
        }
      }

      val formulaWeight = if (LocalBoldEquations.current) FontWeight.Bold else FontWeight.SemiBold
      expressions.forEach { expr ->
        val tokens = remember(expr) { ScientificNotationEngine.tokenizeEquationWithFractions(expr) }
        Row(
          modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(4.dp),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          tokens.forEach { token ->
            when (token) {
              is FormulaToken.Inline -> {
                ScientificText(
                  text = token.text,
                  style =
                    MaterialTheme.typography.bodyLarge.copy(
                      fontFamily = FontFamily.Serif,
                      fontWeight = formulaWeight,
                    ),
                  color = textColor,
                  maxLines = 1,
                  softWrap = false,
                )
              }

              is FormulaToken.StackedFraction -> {
                Column(
                  modifier =
                    Modifier.width(IntrinsicSize.Max)
                      .widthIn(min = 32.dp)
                      .padding(horizontal = 4.dp),
                  horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                  ScientificText(
                    text = token.numerator,
                    style =
                      MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Serif,
                        fontWeight = formulaWeight,
                      ),
                    color = textColor,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    softWrap = false,
                    modifier = Modifier.padding(horizontal = 6.dp),
                  )
                  Box(
                    modifier =
                      Modifier.fillMaxWidth()
                        .padding(vertical = 3.dp)
                        .height(1.5.dp)
                        .background(textColor.copy(alpha = 0.9f))
                  )
                  ScientificText(
                    text = token.denominator,
                    style =
                      MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily.Serif,
                        fontWeight = formulaWeight,
                      ),
                    color = textColor,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    softWrap = false,
                    modifier = Modifier.padding(horizontal = 6.dp),
                  )
                }
              }

              is FormulaToken.MatrixBlock -> {
                val maxCols = token.rows.maxOfOrNull { it.size } ?: 1
                Row(
                  modifier = Modifier.height(IntrinsicSize.Min).padding(horizontal = 4.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                  // Drawn tall left bracket / paren / determinant bar / curly brace
                  Box(
                    modifier =
                      Modifier.width(if (token.isCases) 10.dp else 7.dp)
                        .fillMaxHeight()
                        .padding(vertical = 2.dp),
                    contentAlignment = Alignment.Center,
                  ) {
                    val bracketWidth = if (token.leftDelim == "(") 3.dp else 2.dp
                    when {
                      token.isCases -> {
                        Text(
                          text = "{",
                          style =
                            MaterialTheme.typography.headlineMedium.copy(
                              fontFamily = FontFamily.Serif,
                              fontWeight = FontWeight.Light,
                            ),
                          color = textColor,
                        )
                      }
                      token.leftDelim == "|" -> {
                        Box(Modifier.width(2.dp).fillMaxHeight().background(textColor))
                      }
                      else -> {
                        Box(
                          Modifier.width(6.dp)
                            .fillMaxHeight()
                            .border(
                              width = bracketWidth,
                              color = textColor,
                              shape = RoundedCornerShape(topStart = if (token.leftDelim == "(") 10.dp else 2.dp, bottomStart = if (token.leftDelim == "(") 10.dp else 2.dp),
                            )
                        )
                      }
                    }
                  }

                  Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = if (token.isCases) Alignment.Start else Alignment.CenterHorizontally,
                  ) {
                    token.rows.forEach { rowCells ->
                      Row(
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                      ) {
                        for (cIdx in 0 until maxCols) {
                          val cell = rowCells.getOrElse(cIdx) { "" }
                          ScientificText(
                            text = cell,
                            style =
                              MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = FontFamily.Serif,
                                fontWeight = formulaWeight,
                              ),
                            color = textColor,
                            maxLines = 1,
                            softWrap = false,
                          )
                        }
                      }
                    }
                  }

                  if (token.rightDelim.isNotEmpty()) {
                    Box(
                      modifier = Modifier.width(7.dp).fillMaxHeight().padding(vertical = 2.dp),
                      contentAlignment = Alignment.Center,
                    ) {
                      val bracketWidth = if (token.rightDelim == ")") 3.dp else 2.dp
                      if (token.rightDelim == "|") {
                        Box(Modifier.width(2.dp).fillMaxHeight().background(textColor))
                      } else {
                        Box(
                          Modifier.width(6.dp)
                            .fillMaxHeight()
                            .border(
                              width = bracketWidth,
                              color = textColor,
                              shape = RoundedCornerShape(topEnd = if (token.rightDelim == ")") 10.dp else 2.dp, bottomEnd = if (token.rightDelim == ")") 10.dp else 2.dp),
                            )
                        )
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
