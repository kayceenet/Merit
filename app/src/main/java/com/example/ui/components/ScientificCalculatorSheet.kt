package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.roundToLong
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

object ScientificExpressionEvaluator {

  fun evaluate(rawExpression: String, ansValue: Double = 0.0): Result<Double> {
    if (rawExpression.isBlank()) return Result.failure(IllegalArgumentException("Empty expression"))
    val normalized =
      rawExpression
        .replace("×", "*")
        .replace("÷", "/")
        .replace("−", "-")
        .replace("√", "sqrt")
        .replace("π", "pi")
        .replace("Ans", "($ansValue)")
        .replace(" ", "")
    return runCatching {
      val parser = Parser(normalized)
      val value = parser.parseExpression()
      if (!parser.isAtEnd()) throw IllegalArgumentException("Unexpected symbol")
      if (value.isNaN() || value.isInfinite()) throw ArithmeticException("Math Error")
      value
    }
  }

  fun formatResult(value: Double): String {
    if (value.isNaN() || value.isInfinite()) return "Math Error"
    if (kotlin.math.abs(value) < 1e-12) return "0"
    val roundedLong = value.roundToLong()
    if (kotlin.math.abs(value - roundedLong.toDouble()) < 1e-10) {
      return roundedLong.toString()
    }
    return String.format("%.8g", value).trimEnd('0').trimEnd('.')
  }

  private class Parser(private val src: String) {
    private var pos = 0

    fun isAtEnd(): Boolean = pos >= src.length

    private fun peek(): Char = if (pos < src.length) src[pos] else '\u0000'

    private fun advance(): Char = src[pos++]

    fun parseExpression(): Double {
      var x = parseTerm()
      while (!isAtEnd()) {
        when (peek()) {
          '+' -> {
            advance()
            x += parseTerm()
          }
          '-' -> {
            advance()
            x -= parseTerm()
          }
          else -> return x
        }
      }
      return x
    }

    private fun parseTerm(): Double {
      var x = parsePower()
      while (!isAtEnd()) {
        val c = peek()
        when {
          c == '*' -> {
            advance()
            x *= parsePower()
          }
          c == '/' -> {
            advance()
            val denom = parsePower()
            if (denom == 0.0) throw ArithmeticException("Division by zero")
            x /= denom
          }
          c == '%' -> {
            advance()
            x /= 100.0
          }
          c == '(' || c.isLetter() -> {
            // Implicit multiplication e.g. 2(3+4), 2sin(30), 2pi
            x *= parsePower()
          }
          else -> return x
        }
      }
      return x
    }

    private fun parsePower(): Double {
      val base = parseUnary()
      return if (peek() == '^') {
        advance()
        val exp = parsePower()
        base.pow(exp)
      } else {
        base
      }
    }

    private fun parseUnary(): Double {
      return when (peek()) {
        '+' -> {
          advance()
          parseUnary()
        }
        '-' -> {
          advance()
          -parseUnary()
        }
        else -> parsePrimary()
      }
    }

    private fun parsePrimary(): Double {
      if (peek() == '(') {
        advance()
        val inner = parseExpression()
        if (peek() == ')') advance() else throw IllegalArgumentException("Missing ')'")
        return inner
      }
      if (peek().isDigit() || peek() == '.') {
        val start = pos
        while (!isAtEnd() && (peek().isDigit() || peek() == '.')) advance()
        return src.substring(start, pos).toDouble()
      }
      if (peek().isLetter()) {
        val start = pos
        while (!isAtEnd() && peek().isLetter()) advance()
        val ident = src.substring(start, pos).lowercase()
        return when (ident) {
          "pi" -> PI
          "e" -> kotlin.math.E
          "sin", "cos", "tan", "log", "ln", "sqrt" -> {
            val arg =
              if (peek() == '(') {
                advance()
                val v = parseExpression()
                if (peek() == ')') advance() else throw IllegalArgumentException("Missing ')'")
                v
              } else {
                parseUnary()
              }
            when (ident) {
              "sin" -> sin(Math.toRadians(arg))
              "cos" -> cos(Math.toRadians(arg))
              "tan" -> tan(Math.toRadians(arg))
              "log" -> {
                if (arg <= 0.0) throw ArithmeticException("Domain error")
                log10(arg)
              }
              "ln" -> {
                if (arg <= 0.0) throw ArithmeticException("Domain error")
                ln(arg)
              }
              "sqrt" -> {
                if (arg < 0.0) throw ArithmeticException("Domain error")
                sqrt(arg)
              }
              else -> arg
            }
          }
          else -> throw IllegalArgumentException("Unknown function $ident")
        }
      }
      throw IllegalArgumentException("Unexpected character '${peek()}'")
    }
  }
}

private enum class CalcKeyKind { NUMBER, FUNCTION, OPERATOR, ACTION, EQUALS }

private data class CalcKey(
  val label: String,
  val token: String = label,
  val kind: CalcKeyKind = CalcKeyKind.NUMBER,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScientificCalculatorSheet(onDismiss: () -> Unit) {
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  var expression by remember { mutableStateOf("") }
  var displayResult by remember { mutableStateOf("0") }
  var lastAns by remember { mutableDoubleStateOf(0.0) }

  val rows = remember {
    listOf(
      listOf(
        CalcKey("sin", "sin(", CalcKeyKind.FUNCTION),
        CalcKey("cos", "cos(", CalcKeyKind.FUNCTION),
        CalcKey("tan", "tan(", CalcKeyKind.FUNCTION),
        CalcKey("log", "log(", CalcKeyKind.FUNCTION),
        CalcKey("ln", "ln(", CalcKeyKind.FUNCTION),
      ),
      listOf(
        CalcKey("√", "√(", CalcKeyKind.FUNCTION),
        CalcKey("x²", "^2", CalcKeyKind.FUNCTION),
        CalcKey("^", "^", CalcKeyKind.OPERATOR),
        CalcKey("(", "(", CalcKeyKind.FUNCTION),
        CalcKey(")", ")", CalcKeyKind.FUNCTION),
      ),
      listOf(
        CalcKey("7"),
        CalcKey("8"),
        CalcKey("9"),
        CalcKey("DEL", kind = CalcKeyKind.ACTION),
        CalcKey("AC", kind = CalcKeyKind.ACTION),
      ),
      listOf(
        CalcKey("4"),
        CalcKey("5"),
        CalcKey("6"),
        CalcKey("×", "×", CalcKeyKind.OPERATOR),
        CalcKey("÷", "÷", CalcKeyKind.OPERATOR),
      ),
      listOf(
        CalcKey("1"),
        CalcKey("2"),
        CalcKey("3"),
        CalcKey("+", "+", CalcKeyKind.OPERATOR),
        CalcKey("−", "−", CalcKeyKind.OPERATOR),
      ),
      listOf(
        CalcKey("0"),
        CalcKey("."),
        CalcKey("π", "π", CalcKeyKind.FUNCTION),
        CalcKey("Ans", "Ans", CalcKeyKind.FUNCTION),
        CalcKey("=", kind = CalcKeyKind.EQUALS),
      ),
    )
  }

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
  ) {
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .navigationBarsPadding()
          .padding(horizontal = 16.dp, vertical = 8.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(
          text = "JAMB & WAEC Scientific Calculator (DEG)",
          style = MaterialTheme.typography.titleSmall,
          color = MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.Bold,
        )
        Text(
          text = "Ans = ${ScientificExpressionEvaluator.formatResult(lastAns)}",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
      }

      Column(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.background)
            .border(1.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
            .padding(14.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(4.dp),
      ) {
        Text(
          text = expression.ifBlank { "0" },
          style = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.Serif),
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis,
          textAlign = TextAlign.End,
          modifier = Modifier.fillMaxWidth(),
        )
        Text(
          text = displayResult,
          style =
            MaterialTheme.typography.headlineMedium.copy(
              fontFeatureSettings = "tnum",
              fontWeight = FontWeight.Bold,
            ),
          color = MaterialTheme.colorScheme.onSurface,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.testTag("calculator_display_result"),
        )
      }

      rows.forEach { rowKeys ->
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
          rowKeys.forEach { key ->
            val bg =
              when (key.kind) {
                CalcKeyKind.EQUALS -> MaterialTheme.colorScheme.primary
                CalcKeyKind.ACTION -> MaterialTheme.colorScheme.errorContainer
                CalcKeyKind.OPERATOR -> MaterialTheme.colorScheme.secondaryContainer
                CalcKeyKind.FUNCTION -> MaterialTheme.colorScheme.surfaceVariant
                CalcKeyKind.NUMBER -> MaterialTheme.colorScheme.background
              }
            val fg =
              when (key.kind) {
                CalcKeyKind.EQUALS -> MaterialTheme.colorScheme.onPrimary
                CalcKeyKind.ACTION -> MaterialTheme.colorScheme.onErrorContainer
                CalcKeyKind.OPERATOR -> MaterialTheme.colorScheme.onSecondaryContainer
                CalcKeyKind.FUNCTION -> MaterialTheme.colorScheme.onSurfaceVariant
                CalcKeyKind.NUMBER -> MaterialTheme.colorScheme.onSurface
              }

            Box(
              modifier =
                Modifier.weight(1f)
                  .height(48.dp)
                  .clip(RoundedCornerShape(10.dp))
                  .background(bg)
                  .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(10.dp))
                  .clickable {
                    when (key.label) {
                      "AC" -> {
                        expression = ""
                        displayResult = "0"
                      }
                      "DEL" -> {
                        if (expression.isNotEmpty()) {
                          expression = expression.dropLast(1)
                          val live = ScientificExpressionEvaluator.evaluate(expression, lastAns)
                          if (live.isSuccess) {
                            displayResult = ScientificExpressionEvaluator.formatResult(live.getOrThrow())
                          }
                        }
                      }
                      "=" -> {
                        val res = ScientificExpressionEvaluator.evaluate(expression, lastAns)
                        if (res.isSuccess) {
                          val value = res.getOrThrow()
                          lastAns = value
                          displayResult = ScientificExpressionEvaluator.formatResult(value)
                        } else {
                          displayResult = "Syntax Error"
                        }
                      }
                      else -> {
                        expression += key.token
                        val live = ScientificExpressionEvaluator.evaluate(expression, lastAns)
                        if (live.isSuccess) {
                          displayResult = ScientificExpressionEvaluator.formatResult(live.getOrThrow())
                        }
                      }
                    }
                  }
                  .testTag("calc_key_${key.label}"),
              contentAlignment = Alignment.Center,
            ) {
              Text(
                text = key.label,
                style = MaterialTheme.typography.titleSmall,
                color = fg,
                fontWeight = FontWeight.Bold,
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(6.dp))
    }
  }
}
