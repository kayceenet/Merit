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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.CloudSync
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.local.ThemePreferences
import com.example.data.model.UserProfile
import com.example.data.remote.GoogleUserSnapshot
import com.example.ui.theme.semanticColors

@Composable
fun TopRightMenuButton(
  onClick: () -> Unit,
  badgeCount: Int = 0,
  useOnBarColors: Boolean = false,
  modifier: Modifier = Modifier,
) {
  val semantics = MaterialTheme.semanticColors
  val containerBg =
    if (useOnBarColors) Color.White.copy(alpha = 0.14f)
    else MaterialTheme.colorScheme.surfaceVariant
  val borderColor =
    if (useOnBarColors) Color.White.copy(alpha = 0.28f)
    else MaterialTheme.colorScheme.outlineVariant
  val iconTint =
    if (useOnBarColors) semantics.onClassroomBar
    else MaterialTheme.colorScheme.onSurface

  Box(modifier = modifier) {
    IconButton(
      onClick = onClick,
      modifier =
        Modifier.minimumInteractiveComponentSize()
          .size(48.dp)
          .clip(CircleShape)
          .background(containerBg)
          .border(1.dp, borderColor, CircleShape)
          .testTag("top_right_menu_button"),
    ) {
      Icon(
        imageVector = Icons.Filled.Menu,
        contentDescription = "Open Academy Menu & Settings",
        tint = iconTint,
        modifier = Modifier.size(22.dp),
      )
    }
    if (badgeCount > 0) {
      Surface(
        color = MaterialTheme.colorScheme.error,
        shape = CircleShape,
        modifier = Modifier.align(Alignment.TopEnd).padding(2.dp),
      ) {
        Text(
          text = if (badgeCount > 9) "9+" else "$badgeCount",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onError,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp),
        )
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcademyMenuSheet(
  profile: UserProfile?,
  themePreferences: ThemePreferences,
  isDarkTheme: Boolean,
  dueReviewsCount: Int,
  totalReviewCardsCount: Int,
  placementPassedCount: Int,
  signedInUser: GoogleUserSnapshot?,
  isCloudSyncing: Boolean,
  onToggleDarkTheme: () -> Unit,
  onOpenSettingsPortal: () -> Unit,
  onOpenSpacedReview: () -> Unit,
  onOpenUtmeCombo: () -> Unit,
  onOpenPaper2Lab: () -> Unit,
  onOpenPlacementCheck: () -> Unit,
  onOpenNotebook: () -> Unit,
  onOpenAnalytics: () -> Unit,
  onExportJsonBackup: () -> Unit,
  onImportJsonBackup: () -> Unit,
  onGoogleSignInAndSync: () -> Unit,
  onGoogleSignOut: () -> Unit,
  onDismiss: () -> Unit,
) {
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  val semantics = MaterialTheme.semanticColors
  val dailyTarget = themePreferences.dailyXpTarget.coerceAtLeast(50)
  val totalXp = (profile?.totalXp ?: 0L).toInt()
  val dailyProgressRatio = ((totalXp % dailyTarget).toFloat() / dailyTarget.toFloat()).coerceIn(0.08f, 1f)

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
  ) {
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .verticalScroll(rememberScrollState())
          .navigationBarsPadding()
          .padding(horizontal = 16.dp, vertical = 8.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      // Academy Scholar Summary Header + Quick Theme Flip
      Row(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(semantics.classroomBar)
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text(
            text = profile?.displayName ?: "MeritPrep Scholar",
            style = MaterialTheme.typography.titleMedium,
            color = semantics.onClassroomBar,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
          )
          Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(3.dp)) {
              Icon(
                Icons.Filled.LocalFireDepartment,
                contentDescription = null,
                tint = Color(0xFFFBBF24),
                modifier = Modifier.size(15.dp),
              )
              Text(
                text = "${profile?.streakDays ?: 1}d streak",
                style = MaterialTheme.typography.labelSmall,
                color = semantics.classroomSubtext,
                fontWeight = FontWeight.Bold,
              )
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(3.dp)) {
              Icon(
                Icons.Filled.Bolt,
                contentDescription = null,
                tint = semantics.classroomAccent,
                modifier = Modifier.size(15.dp),
              )
              Text(
                text = "$totalXp XP • Daily goal ${dailyTarget} XP",
                style = MaterialTheme.typography.labelSmall,
                color = semantics.classroomSubtext,
                fontWeight = FontWeight.SemiBold,
              )
            }
          }
          LinearProgressIndicator(
            progress = { dailyProgressRatio },
            modifier = Modifier.fillMaxWidth().padding(top = 2.dp).height(5.dp).clip(CircleShape),
            color = semantics.classroomAccent,
            trackColor = Color.White.copy(alpha = 0.2f),
          )
        }
        Spacer(modifier = Modifier.width(10.dp))
        IconButton(
          onClick = onToggleDarkTheme,
          modifier =
            Modifier.size(48.dp)
              .clip(CircleShape)
              .background(Color.White.copy(alpha = 0.14f))
              .testTag("menu_quick_theme_toggle"),
        ) {
          Icon(
            imageVector = if (isDarkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode,
            contentDescription = if (isDarkTheme) "Switch to Light Mode" else "Switch to Dark Mode",
            tint = semantics.onClassroomBar,
          )
        }
      }

      Text(
        text = "ACADEMY MENU & STUDY TOOLS",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 4.dp),
      )

      MenuActionTile(
        icon = Icons.Filled.Settings,
        title = "Settings Portal",
        subtitle = "${themePreferences.themePalette.label} • ${themePreferences.fontSizeScale.label} Text • ${themePreferences.masteryPassPercent}% Pass Gate",
        badgeText = "CUSTOMIZE",
        testTag = "menu_item_settings",
        onClick = {
          onDismiss()
          onOpenSettingsPortal()
        },
      )

      MenuActionTile(
        icon = Icons.Filled.Psychology,
        title = "Today's Spaced Recall Deck",
        subtitle =
          if (dueReviewsCount > 0) "$dueReviewsCount card(s) scheduled for active recall today"
          else if (totalReviewCardsCount > 0) "All $totalReviewCardsCount recall cards reviewed — ahead of schedule"
          else "Master lessons or take mocks to auto-build your SM-2 recall deck",
        badgeText = if (dueReviewsCount > 0) "$dueReviewsCount DUE" else "${totalReviewCardsCount}",
        highlightBadge = dueReviewsCount > 0,
        testTag = "menu_item_spaced_review",
        onClick = {
          onDismiss()
          onOpenSpacedReview()
        },
      )

      MenuActionTile(
        icon = Icons.Filled.Quiz,
        title = "4-Subject UTME Combo (/400)",
        subtitle = "Use of English + 3 Electives with live tab switching & 400-mark score",
        badgeText = "UTME",
        testTag = "menu_item_utme_combo",
        onClick = {
          onDismiss()
          onOpenUtmeCombo()
        },
      )

      MenuActionTile(
        icon = Icons.AutoMirrored.Filled.Assignment,
        title = "WAEC & NECO Paper 2 Theory Lab",
        subtitle = "Written theory & Alternative-to-Practical prompts with M1/A1 rubrics",
        badgeText = "THEORY",
        testTag = "menu_item_paper2",
        onClick = {
          onDismiss()
          onOpenPaper2Lab()
        },
      )

      MenuActionTile(
        icon = Icons.Filled.School,
        title = "SS3 Diagnostic Placement Check",
        subtitle = "Score 75%+ to fast-unlock the foundational 60% of any subject track",
        badgeText = "$placementPassedCount/${CurriculumCatalog.tracks.size}",
        testTag = "menu_item_placement",
        onClick = {
          onDismiss()
          onOpenPlacementCheck()
        },
      )

      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        MenuCompactChip(
          icon = Icons.AutoMirrored.Filled.MenuBook,
          label = "Formula Notebook",
          modifier = Modifier.weight(1f).testTag("menu_item_notebook"),
          onClick = {
            onDismiss()
            onOpenNotebook()
          },
        )
        MenuCompactChip(
          icon = Icons.Filled.Analytics,
          label = "Performance & XP",
          modifier = Modifier.weight(1f).testTag("menu_item_analytics"),
          onClick = {
            onDismiss()
            onOpenAnalytics()
          },
        )
      }

      HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

      Text(
        text = "PORTABLE BACKUP & OPTIONAL CLOUD SYNC",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.secondary,
        fontWeight = FontWeight.Bold,
      )

      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedButton(
          onClick = {
            onDismiss()
            onExportJsonBackup()
          },
          modifier = Modifier.weight(1f).height(48.dp).testTag("menu_export_json"),
          shape = RoundedCornerShape(12.dp),
        ) {
          Icon(Icons.Filled.Upload, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(Modifier.width(6.dp))
          Text("Export JSON", maxLines = 1)
        }
        OutlinedButton(
          onClick = {
            onDismiss()
            onImportJsonBackup()
          },
          modifier = Modifier.weight(1f).height(48.dp).testTag("menu_import_json"),
          shape = RoundedCornerShape(12.dp),
        ) {
          Icon(Icons.Filled.Download, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(Modifier.width(6.dp))
          Text("Restore JSON", maxLines = 1)
        }
      }

      Row(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f))
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
            .clickable(enabled = !isCloudSyncing) { onGoogleSignInAndSync() }
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .testTag("menu_cloud_sync"),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
      ) {
        if (isCloudSyncing) {
          CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
        } else {
          Icon(
            imageVector = if (signedInUser != null) Icons.Filled.CloudDone else Icons.Filled.CloudSync,
            contentDescription = "Google Cloud Sync",
            tint = if (signedInUser != null) semantics.success else MaterialTheme.colorScheme.primary,
          )
        }
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text =
              if (signedInUser != null) "Synced: ${signedInUser.displayName}"
              else "Google Sign-In & Cloud Backup",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
          )
          Text(
            text =
              if (signedInUser != null) signedInUser.email.orEmpty().ifBlank { "Tap to sync progress now" }
              else "Optional — app remains 100% functional offline",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
          )
        }
        if (signedInUser != null) {
          TextButton(onClick = onGoogleSignOut) {
            Text("Sign Out")
          }
        } else {
          Icon(Icons.Filled.Tune, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
        }
      }

      Spacer(modifier = Modifier.height(6.dp))
    }
  }
}

@Composable
private fun MenuActionTile(
  icon: ImageVector,
  title: String,
  subtitle: String,
  badgeText: String,
  highlightBadge: Boolean = false,
  testTag: String,
  onClick: () -> Unit,
) {
  Row(
    modifier =
      Modifier.fillMaxWidth()
        .minimumInteractiveComponentSize()
        .clip(RoundedCornerShape(14.dp))
        .background(MaterialTheme.colorScheme.background)
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
        .clickable(onClick = onClick)
        .padding(horizontal = 12.dp, vertical = 10.dp)
        .testTag(testTag),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(12.dp),
  ) {
    Box(
      modifier =
        Modifier.size(40.dp)
          .clip(RoundedCornerShape(10.dp))
          .background(MaterialTheme.colorScheme.primaryContainer),
      contentAlignment = Alignment.Center,
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier.size(20.dp),
      )
    }
    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
      )
      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
      )
    }
    Surface(
      color =
        if (highlightBadge) MaterialTheme.colorScheme.errorContainer
        else MaterialTheme.colorScheme.secondaryContainer,
      shape = RoundedCornerShape(99.dp),
    ) {
      Text(
        text = badgeText,
        style = MaterialTheme.typography.labelSmall,
        color =
          if (highlightBadge) MaterialTheme.colorScheme.onErrorContainer
          else MaterialTheme.colorScheme.onSecondaryContainer,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
      )
    }
  }
}

@Composable
private fun MenuCompactChip(
  icon: ImageVector,
  label: String,
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  Row(
    modifier =
      modifier
        .height(48.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f))
        .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
        .clickable(onClick = onClick)
        .padding(horizontal = 12.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center,
  ) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = MaterialTheme.colorScheme.onPrimaryContainer,
      modifier = Modifier.size(18.dp),
    )
    Spacer(modifier = Modifier.width(6.dp))
    Text(
      text = label,
      style = MaterialTheme.typography.labelMedium,
      color = MaterialTheme.colorScheme.onPrimaryContainer,
      fontWeight = FontWeight.Bold,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis,
    )
  }
}
