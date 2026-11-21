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
import java.util.Locale
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.roundToLong
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

object ScientificExpressionEvaluator {

  fun evaluate(
    rawExpression: String,
    ansValue: Double = 0.0,
    isDegreeMode: Boolean = true,
  ): Result<Double> {
    if (rawExpression.isBlank()) return Result.failure(IllegalArgumentException("Empty expression"))
    val normalized =
      rawExpression
        .replace("sin⁻¹", "asin")
        .replace("cos⁻¹", "acos")
        .replace("tan⁻¹", "atan")
        .replace("×10^", "*10^")
        .replace("×", "*")
        .replace("÷", "/")
        .replace("−", "-")
        .replace("√", "sqrt")
        .replace("π", "pi")
        .replace("Ans", "($ansValue)")
        .replace(" ", "")
    return runCatching {
      val parser = Parser(normalized, isDegreeMode)
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
    if (kotlin.math.abs(value - roundedLong.toDouble()) < 1e-10 && kotlin.math.abs(value) < 1e14) {
      return roundedLong.toString()
    }
    return String.format(Locale.US, "%.8g", value).trimEnd('0').trimEnd('.')
  }

  private class Parser(
    private val src: String,
    private val isDegreeMode: Boolean,
  ) {
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
            x *= parsePower()
          }
          else -> return x
        }
      }
      return x
    }

    private fun parsePower(): Double {
      var base = parsePostfix()
      if (peek() == '^') {
        advance()
        val exp = parsePower()
        base = base.pow(exp)
      }
      return base
    }

    private fun parsePostfix(): Double {
      var x = parseUnary()
      while (peek() == '!') {
        advance()
        val n = x.roundToLong()
        if (x < 0.0 || kotlin.math.abs(x - n.toDouble()) > 1e-9 || n > 20L) {
          throw ArithmeticException("Factorial domain 0..20")
        }
        var acc = 1.0
        for (i in 2L..n) acc *= i.toDouble()
        x = acc
      }
      return x
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
          "sin", "cos", "tan", "asin", "acos", "atan", "log", "ln", "sqrt" -> {
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
              "sin" -> sin(if (isDegreeMode) Math.toRadians(arg) else arg)
              "cos" -> cos(if (isDegreeMode) Math.toRadians(arg) else arg)
              "tan" -> tan(if (isDegreeMode) Math.toRadians(arg) else arg)
              "asin" -> {
                if (arg < -1.0 || arg > 1.0) throw ArithmeticException("Domain [-1,1]")
                val rad = asin(arg)
                if (isDegreeMode) Math.toDegrees(rad) else rad
              }
              "acos" -> {
                if (arg < -1.0 || arg > 1.0) throw ArithmeticException("Domain [-1,1]")
                val rad = acos(arg)
                if (isDegreeMode) Math.toDegrees(rad) else rad
              }
              "atan" -> {
                val rad = atan(arg)
                if (isDegreeMode) Math.toDegrees(rad) else rad
              }
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
  var isDegreeMode by remember { mutableStateOf(true) }

  val rows = remember {
    listOf(
      listOf(
        CalcKey("sin⁻¹", "sin⁻¹(", CalcKeyKind.FUNCTION),
        CalcKey("cos⁻¹", "cos⁻¹(", CalcKeyKind.FUNCTION),
        CalcKey("tan⁻¹", "tan⁻¹(", CalcKeyKind.FUNCTION),
        CalcKey("n!", "!", CalcKeyKind.FUNCTION),
        CalcKey("×10^", "×10^", CalcKeyKind.FUNCTION),
      ),
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
      verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(
          text = "JAMB & WAEC Scientific Calculator",
          style = MaterialTheme.typography.titleSmall,
          color = MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.Bold,
        )
        Row(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Box(
            modifier =
              Modifier.clip(RoundedCornerShape(99.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(99.dp))
                .clickable {
                  isDegreeMode = !isDegreeMode
                  val live = ScientificExpressionEvaluator.evaluate(expression, lastAns, isDegreeMode)
                  if (live.isSuccess) {
                    displayResult = ScientificExpressionEvaluator.formatResult(live.getOrThrow())
                  }
                }
                .padding(horizontal = 10.dp, vertical = 4.dp)
                .testTag("calc_deg_rad_toggle"),
          ) {
            Text(
              text = if (isDegreeMode) "DEG" else "RAD",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.onPrimaryContainer,
              fontWeight = FontWeight.ExtraBold,
            )
          }
          Text(
            text = "Ans=${ScientificExpressionEvaluator.formatResult(lastAns)}",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
        }
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
                          val live = ScientificExpressionEvaluator.evaluate(expression, lastAns, isDegreeMode)
                          if (live.isSuccess) {
                            displayResult = ScientificExpressionEvaluator.formatResult(live.getOrThrow())
                          }
                        }
                      }
                      "=" -> {
                        val res = ScientificExpressionEvaluator.evaluate(expression, lastAns, isDegreeMode)
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
                        val live = ScientificExpressionEvaluator.evaluate(expression, lastAns, isDegreeMode)
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
                style = MaterialTheme.typography.labelLarge,
                color = fg,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(6.dp))
    }
  }
}
