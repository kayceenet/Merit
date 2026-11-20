package com.example.ui.components

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.semanticColors

/**
 * Horizontal multi-stop gradient card matching the Studio mockup hero progress banner
 * ("Keep improving! / Your current score" + circular white ring).
 */
@Composable
fun StudioGradientProgressBanner(
  title: String,
  subtitle: String,
  badgeText: String? = null,
  progressPercent: Int,
  modifier: Modifier = Modifier,
  onClick: (() -> Unit)? = null,
) {
  val semantics = MaterialTheme.semanticColors
  val clampedPct = progressPercent.coerceIn(0, 100)
  val gradient =
    Brush.horizontalGradient(
      colors =
        listOf(
          semantics.heroGradientStart,
          semantics.heroGradientMid,
          semantics.heroGradientEnd,
        )
    )

  Row(
    modifier =
      modifier
        .fillMaxWidth()
        .shadow(elevation = 6.dp, shape = RoundedCornerShape(22.dp), ambientColor = semantics.heroGradientStart.copy(alpha = 0.25f))
        .clip(RoundedCornerShape(22.dp))
        .background(gradient)
        .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
        .padding(horizontal = 20.dp, vertical = 18.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Column(
      modifier = Modifier.weight(1f).padding(end = 14.dp),
      verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
      if (!badgeText.isNullOrBlank()) {
        Surface(
          color = Color.White.copy(alpha = 0.22f),
          shape = RoundedCornerShape(99.dp),
        ) {
          Text(
            text = badgeText,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 3.dp),
          )
        }
      }
      Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
      )
      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodySmall,
        color = Color.White.copy(alpha = 0.90f),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
      )
    }

    CircularScoreRing(
      percent = clampedPct,
      size = 58.dp,
      strokeWidth = 4.5.dp,
      activeColor = semantics.coralAccent,
      trackColor = Color.White.copy(alpha = 0.30f),
      textColor = Color.White,
    )
  }
}

@Composable
fun CircularScoreRing(
  percent: Int,
  size: Dp = 56.dp,
  strokeWidth: Dp = 4.5.dp,
  activeColor: Color,
  trackColor: Color,
  textColor: Color,
  modifier: Modifier = Modifier,
) {
  val clamped = percent.coerceIn(0, 100)
  Box(modifier = modifier.size(size), contentAlignment = Alignment.Center) {
    Canvas(modifier = Modifier.matchParentSize()) {
      val strokePx = strokeWidth.toPx()
      val inset = strokePx / 2f
      val arcSize = Size(this.size.width - strokePx, this.size.height - strokePx)
      drawArc(
        color = trackColor,
        startAngle = 0f,
        sweepAngle = 360f,
        useCenter = false,
        topLeft = Offset(inset, inset),
        size = arcSize,
        style = Stroke(width = strokePx, cap = StrokeCap.Round),
      )
      if (clamped > 0) {
        drawArc(
          color = activeColor,
          startAngle = -90f,
          sweepAngle = 360f * (clamped / 100f),
          useCenter = false,
          topLeft = Offset(inset, inset),
          size = arcSize,
          style = Stroke(width = strokePx, cap = StrokeCap.Round),
        )
      }
    }
    Text(
      text = "$clamped%",
      style = MaterialTheme.typography.labelLarge.copy(fontFeatureSettings = "tnum"),
      color = textColor,
      fontWeight = FontWeight.Bold,
    )
  }
}

/**
 * 3x2 / tile-grid white elevated square-ish button tile matching the Unit 3 screen in the mockup.
 */
@Composable
fun StudioActionTile(
  label: String,
  icon: ImageVector,
  selected: Boolean = false,
  completed: Boolean = false,
  locked: Boolean = false,
  iconTint: Color = MaterialTheme.colorScheme.primary,
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  val semantics = MaterialTheme.semanticColors
  val containerBg =
    when {
      selected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
      locked -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f)
      else -> MaterialTheme.colorScheme.surface
    }
  val borderColor =
    when {
      selected -> MaterialTheme.colorScheme.primary
      completed -> semantics.success.copy(alpha = 0.65f)
      else -> MaterialTheme.colorScheme.outlineVariant
    }

  Box(
    modifier =
      modifier
        .shadow(
          elevation = if (locked) 0.dp else 3.dp,
          shape = RoundedCornerShape(18.dp),
          ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
        )
        .clip(RoundedCornerShape(18.dp))
        .background(containerBg)
        .border(if (selected) 1.5.dp else 1.dp, borderColor, RoundedCornerShape(18.dp))
        .clickable(onClick = onClick)
        .padding(horizontal = 8.dp, vertical = 14.dp),
  ) {
    if (completed || locked) {
      Icon(
        imageVector = if (completed) Icons.Filled.CheckCircle else Icons.Filled.Lock,
        contentDescription = if (completed) "Completed" else "Locked",
        tint = if (completed) semantics.success else MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.align(Alignment.TopEnd).size(15.dp),
      )
    }

    Column(
      modifier = Modifier.fillMaxWidth().align(Alignment.Center),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      Icon(
        imageVector = icon,
        contentDescription = label,
        tint = if (locked) MaterialTheme.colorScheme.onSurfaceVariant else iconTint,
        modifier = Modifier.size(26.dp),
      )
      Text(
        text = label,
        style = MaterialTheme.typography.labelMedium,
        color =
          if (locked) MaterialTheme.colorScheme.onSurfaceVariant
          else MaterialTheme.colorScheme.onSurface,
        fontWeight = if (selected) FontWeight.Bold else FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )
    }
  }
}

/**
 * Vibrant 2-column gradient subject card matching the "Materials" screen in the mockup.
 */
@Composable
fun StudioSubjectGradientCard(
  codeBadge: String,
  subtitle: String,
  title: String,
  lessonsCount: Int,
  questionsCount: Int,
  completedLessons: Int,
  gradientColors: List<Color>,
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  val masteryRatio = if (lessonsCount == 0) 0f else (completedLessons.toFloat() / lessonsCount.toFloat()).coerceIn(0f, 1f)
  Column(
    modifier =
      modifier
        .fillMaxWidth()
        .shadow(
          elevation = 6.dp,
          shape = RoundedCornerShape(22.dp),
          ambientColor = gradientColors.first().copy(alpha = 0.28f),
        )
        .clip(RoundedCornerShape(22.dp))
        .background(Brush.verticalGradient(gradientColors))
        .clickable(onClick = onClick)
        .padding(horizontal = 14.dp, vertical = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    Surface(
      color = Color.White.copy(alpha = 0.24f),
      shape = RoundedCornerShape(99.dp),
    ) {
      Text(
        text = codeBadge,
        style = MaterialTheme.typography.labelSmall,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
      )
    }

    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
      Text(
        text = subtitle,
        style = MaterialTheme.typography.labelSmall,
        color = Color.White.copy(alpha = 0.80f),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )
      Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )
    }

    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
      CardCountPillRow(countText = "$lessonsCount", label = "Lessons")
      CardCountPillRow(countText = "$questionsCount", label = "Practice Qs")
    }

    LinearProgressIndicator(
      progress = { masteryRatio.coerceAtLeast(0.04f) },
      modifier = Modifier.fillMaxWidth().height(5.dp).clip(CircleShape),
      color = Color.White,
      trackColor = Color.White.copy(alpha = 0.25f),
    )
  }
}

@Composable
private fun CardCountPillRow(countText: String, label: String) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    Box(
      modifier =
        Modifier.size(24.dp)
          .clip(CircleShape)
          .background(Color.White.copy(alpha = 0.24f)),
      contentAlignment = Alignment.Center,
    ) {
      Text(
        text = countText,
        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontFeatureSettings = "tnum"),
        color = Color.White,
        fontWeight = FontWeight.Bold,
      )
    }
    Text(
      text = label,
      style = MaterialTheme.typography.labelSmall,
      color = Color.White.copy(alpha = 0.92f),
      fontWeight = FontWeight.Medium,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis,
    )
  }
}

/**
 * White rounded metric card with thin bottom colored bar matching the mockup's "Round results" tiles.
 */
@Composable
fun StudioMetricTile(
  count: Int,
  label: String,
  barColor: Color,
  fillRatio: Float,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier =
      modifier
        .shadow(
          elevation = 4.dp,
          shape = RoundedCornerShape(18.dp),
          ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
        )
        .clip(RoundedCornerShape(18.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
        .padding(horizontal = 12.dp, vertical = 14.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(4.dp),
  ) {
    Text(
      text = "$count",
      style = MaterialTheme.typography.headlineSmall.copy(fontFeatureSettings = "tnum"),
      color = MaterialTheme.colorScheme.onSurface,
      fontWeight = FontWeight.Bold,
    )
    Text(
      text = label,
      style = MaterialTheme.typography.labelMedium,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
    Spacer(modifier = Modifier.height(4.dp))
    LinearProgressIndicator(
      progress = { fillRatio.coerceIn(0.06f, 1f) },
      modifier = Modifier.fillMaxWidth(0.76f).height(4.dp).clip(CircleShape),
      color = barColor,
      trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
  }
}

/**
 * Circular progress gauge with vector Canvas Rocket launching up-right and large percentage,
 * inspired directly by the Round Results screen in the mockup.
 */
@Composable
fun RocketScoreHero(
  percentage: Int,
  displayText: String,
  modifier: Modifier = Modifier,
) {
  val semantics = MaterialTheme.semanticColors
  val primaryBlue = MaterialTheme.colorScheme.primary
  val indigoDark = semantics.indigoAccent
  val coralTint = semantics.coralAccent
  val trackColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.7f)

  Box(modifier = modifier.size(220.dp), contentAlignment = Alignment.Center) {
    Canvas(modifier = Modifier.matchParentSize()) {
      val w = size.width
      val h = size.height
      val strokePx = 7.dp.toPx()
      val pad = strokePx
      val arcSize = Size(w - pad * 2f, h - pad * 2f)

      // Open-bottom circular track (starts at 130 deg, sweeps 280 deg leaving bottom gap for percentage)
      drawArc(
        color = trackColor,
        startAngle = 132f,
        sweepAngle = 276f,
        useCenter = false,
        topLeft = Offset(pad, pad),
        size = arcSize,
        style = Stroke(width = strokePx, cap = StrokeCap.Round),
      )

      val pctSweep = 276f * (percentage.coerceIn(0, 100) / 100f)
      if (pctSweep > 0f) {
        drawArc(
          brush = Brush.sweepGradient(listOf(primaryBlue, indigoDark, primaryBlue)),
          startAngle = 132f,
          sweepAngle = pctSweep,
          useCenter = false,
          topLeft = Offset(pad, pad),
          size = arcSize,
          style = Stroke(width = strokePx, cap = StrokeCap.Round),
        )
      }

      val center = Offset(w / 2f, h / 2f - 8.dp.toPx())
      // Soft halo disk
      drawCircle(
        color = primaryBlue.copy(alpha = 0.08f),
        radius = w * 0.27f,
        center = center,
      )

      // Soft clouds under rocket
      val cloudColor = Color.White.copy(alpha = 0.88f)
      drawRoundRect(
        color = cloudColor,
        topLeft = Offset(center.x - 42.dp.toPx(), center.y + 18.dp.toPx()),
        size = Size(34.dp.toPx(), 12.dp.toPx()),
        cornerRadius = CornerRadius(20f, 20f),
      )
      drawCircle(color = cloudColor, radius = 9.dp.toPx(), center = Offset(center.x - 28.dp.toPx(), center.y + 18.dp.toPx()))
      drawRoundRect(
        color = cloudColor,
        topLeft = Offset(center.x + 12.dp.toPx(), center.y + 22.dp.toPx()),
        size = Size(32.dp.toPx(), 11.dp.toPx()),
        cornerRadius = CornerRadius(20f, 20f),
      )
      drawCircle(color = cloudColor, radius = 8.dp.toPx(), center = Offset(center.x + 26.dp.toPx(), center.y + 22.dp.toPx()))

      // Sparkle dots
      val sparkles = listOf(
        Offset(center.x - 42.dp.toPx(), center.y - 26.dp.toPx()) to 3.dp.toPx(),
        Offset(center.x + 40.dp.toPx(), center.y - 16.dp.toPx()) to 2.8.dp.toPx(),
        Offset(center.x - 20.dp.toPx(), center.y - 44.dp.toPx()) to 2.2.dp.toPx(),
        Offset(center.x + 34.dp.toPx(), center.y + 8.dp.toPx()) to 2.2.dp.toPx(),
      )
      sparkles.forEachIndexed { idx, (pt, rad) ->
        drawCircle(
          color = if (idx % 2 == 0) indigoDark else primaryBlue.copy(alpha = 0.7f),
          radius = rad,
          center = pt,
        )
      }

      // Rocket oriented 42 deg up-right
      rotate(degrees = 42f, pivot = center) {
        val bodyW = 28.dp.toPx()
        val bodyH = 54.dp.toPx()

        // Exhaust plume
        val flame = Path().apply {
          moveTo(center.x - 7.dp.toPx(), center.y + bodyH * 0.42f)
          lineTo(center.x, center.y + bodyH * 0.78f)
          lineTo(center.x + 7.dp.toPx(), center.y + bodyH * 0.42f)
          close()
        }
        drawPath(flame, color = coralTint)

        // Left & right tail fins
        val leftFin = Path().apply {
          moveTo(center.x - bodyW * 0.42f, center.y + 8.dp.toPx())
          lineTo(center.x - bodyW * 0.92f, center.y + 26.dp.toPx())
          lineTo(center.x - bodyW * 0.35f, center.y + 22.dp.toPx())
          close()
        }
        val rightFin = Path().apply {
          moveTo(center.x + bodyW * 0.42f, center.y + 8.dp.toPx())
          lineTo(center.x + bodyW * 0.92f, center.y + 26.dp.toPx())
          lineTo(center.x + bodyW * 0.35f, center.y + 22.dp.toPx())
          close()
        }
        drawPath(leftFin, color = indigoDark)
        drawPath(rightFin, color = indigoDark)

        // Rocket capsule body
        val bodyPath = Path().apply {
          moveTo(center.x, center.y - bodyH * 0.56f)
          cubicTo(
            center.x + bodyW * 0.65f, center.y - bodyH * 0.25f,
            center.x + bodyW * 0.58f, center.y + bodyH * 0.22f,
            center.x + bodyW * 0.36f, center.y + bodyH * 0.42f,
          )
          lineTo(center.x - bodyW * 0.36f, center.y + bodyH * 0.42f)
          cubicTo(
            center.x - bodyW * 0.58f, center.y + bodyH * 0.22f,
            center.x - bodyW * 0.65f, center.y - bodyH * 0.25f,
            center.x, center.y - bodyH * 0.56f,
          )
          close()
        }
        drawPath(bodyPath, brush = Brush.verticalGradient(listOf(primaryBlue, indigoDark)))

        // Porthole window
        drawCircle(
          color = Color.White,
          radius = 7.dp.toPx(),
          center = Offset(center.x, center.y - 4.dp.toPx()),
        )
        drawCircle(
          color = primaryBlue.copy(alpha = 0.25f),
          radius = 4.5.dp.toPx(),
          center = Offset(center.x, center.y - 4.dp.toPx()),
        )
      }
    }

    Text(
      text = displayText,
      style = MaterialTheme.typography.headlineMedium.copy(fontFeatureSettings = "tnum"),
      color = MaterialTheme.colorScheme.primary,
      fontWeight = FontWeight.Bold,
      modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp),
    )
  }
}
