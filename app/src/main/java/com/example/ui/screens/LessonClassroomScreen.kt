package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.CurriculumProgression
import com.example.data.curriculum.GuidedPracticeTask
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.LessonPillar
import com.example.data.curriculum.PracticeQuestion
import com.example.data.curriculum.QuestionShuffler
import com.example.data.curriculum.TopicMasteryProject
import com.example.data.local.FontSizeScale
import com.example.data.local.LineSpacingScale
import com.example.data.local.ThemeMode
import com.example.data.local.ThemePalette
import com.example.data.local.ThemePreferences
import com.example.data.model.LessonProgress
import com.example.ui.components.DisplaySettingsDialog
import com.example.ui.components.EquationDisplayCard
import com.example.ui.components.LessonSpeechFormatter
import com.example.ui.components.LessonTtsController
import com.example.ui.components.ScientificCalculatorSheet
import com.example.ui.components.ScientificText
import com.example.ui.components.StudioActionTile
import com.example.ui.components.TopicDiagramCard
import com.example.ui.components.TopRightMenuButton
import com.example.ui.components.rememberLessonTtsController
import com.example.ui.labs.PracticalLabWorkspace
import com.example.ui.theme.semanticColors

@Composable
fun LessonClassroomScreen(
  lesson: Lesson,
  progress: LessonProgress?,
  nextLesson: Lesson? = null,
  themePreferences: ThemePreferences = ThemePreferences(),
  dueReviewsCount: Int = 0,
  onSetThemeMode: (ThemeMode) -> Unit = {},
  onSetThemePalette: (ThemePalette) -> Unit = {},
  onSetHighContrastBoost: (Boolean) -> Unit = {},
  onSetUseDynamicColor: (Boolean) -> Unit = {},
  onSetFontSizeScale: (FontSizeScale) -> Unit = {},
  onSetLineSpacingScale: (LineSpacingScale) -> Unit = {},
  onSetBoldEquations: (Boolean) -> Unit = {},
  onOpenMenu: (() -> Unit)? = null,
  onBack: () -> Unit,
  onMarkPillarComplete: (LessonPillar, Long?) -> Unit,
  onSaveNote: (title: String, content: String) -> Unit,
  onOpenNextLesson: ((Lesson) -> Unit)? = null,
) {
  BackHandler(onBack = onBack)

  val completedSteps = progress?.completedSteps?.distinct()?.toSet() ?: emptySet()
  var selectedPillar by remember(lesson.id) {
    val firstIncomplete =
      LessonPillar.entries.firstOrNull {
        it.key !in completedSteps && CurriculumProgression.isPillarUnlocked(it, completedSteps)
      }
    mutableStateOf(firstIncomplete ?: LessonPillar.CONCEPT)
  }
  var showNoteDialog by remember { mutableStateOf(false) }
  var showDisplaySettings by remember { mutableStateOf(false) }
  var showCalculator by remember { mutableStateOf(false) }
  var lockNoticeMessage by remember { mutableStateOf<String?>(null) }
  val projectVerificationAnswers = remember(lesson.id) { mutableStateMapOf<String, Int>() }
  val verificationTasks = lesson.masteryProject?.verificationTasks.orEmpty()
  val isProjectVerified =
    verificationTasks.isEmpty() ||
      verificationTasks.all { projectVerificationAnswers[it.id] == it.correctIndex }

  val semantics = MaterialTheme.semanticColors
  val classroomRatio = completedSteps.size / LessonPillar.entries.size.toFloat()
  val ttsController = rememberLessonTtsController()

  LaunchedEffect(lesson.id, selectedPillar) {
    ttsController.stop()
  }

  Column(
    modifier =
      Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .statusBarsPadding()
        .navigationBarsPadding(),
  ) {
    // Studio Clean Classroom Top Header + Subtle Progress Rail
    Column(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)) {
      Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
      ) {
        IconButton(onClick = onBack, modifier = Modifier.testTag("classroom_back_button")) {
          Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back to syllabus",
            tint = MaterialTheme.colorScheme.onSurface,
          )
        }
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = lesson.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
          )
          Text(
            text = "${lesson.syllabusTag} • ${completedSteps.size}/${LessonPillar.entries.size} sections mastered",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
        }
        if (themePreferences.cbtShowCalculator) {
          IconButton(
            onClick = { showCalculator = true },
            modifier = Modifier.testTag("classroom_calculator_button"),
          ) {
            Icon(
              Icons.Filled.Calculate,
              contentDescription = "Scientific calculator",
              tint = MaterialTheme.colorScheme.primary,
            )
          }
        }
        IconButton(
          onClick = { showNoteDialog = true },
          modifier = Modifier.testTag("classroom_add_note_button"),
        ) {
          Icon(
            Icons.Filled.BookmarkAdd,
            contentDescription = "Save formula note",
            tint = MaterialTheme.colorScheme.primary,
          )
        }
        if (onOpenMenu != null) {
          TopRightMenuButton(
            badgeCount = dueReviewsCount,
            onClick = onOpenMenu,
            useOnBarColors = false,
          )
        } else {
          IconButton(
            onClick = { showDisplaySettings = true },
            modifier = Modifier.testTag("classroom_display_settings_button"),
          ) {
            Icon(
              Icons.Filled.TextFields,
              contentDescription = "Text size and display settings",
              tint = MaterialTheme.colorScheme.primary,
            )
          }
        }
      }
      LinearProgressIndicator(
        progress = { classroomRatio.coerceAtLeast(0.05f) },
        modifier = Modifier.fillMaxWidth().height(4.dp),
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.outlineVariant,
      )
    }

    if (!lockNoticeMessage.isNullOrBlank()) {
      Surface(
        color = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = Modifier.fillMaxWidth(),
      ) {
        Text(
          text = lockNoticeMessage.orEmpty(),
          style = MaterialTheme.typography.labelMedium,
          color = MaterialTheme.colorScheme.onTertiaryContainer,
          modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        )
      }
    }

    // Adaptive Coursera Split-Workspace (Tablet sidebar vs Handheld stepper rail)
    BoxWithConstraints(modifier = Modifier.weight(1f).fillMaxWidth()) {
      val isWide = maxWidth >= 720.dp

      if (isWide) {
        Row(modifier = Modifier.fillMaxSize()) {
          // Left Coursera Module Outline Sidebar
          Column(
            modifier =
              Modifier.width(268.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Text(
              text = "SECTIONS",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
            )
            LessonPillar.entries.forEach { pillar ->
              val isDone = pillar.key in completedSteps
              val isUnlocked = CurriculumProgression.isPillarUnlocked(pillar, completedSteps)
              val active = pillar == selectedPillar
              Row(
                modifier =
                  Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                      when {
                        active -> MaterialTheme.colorScheme.primaryContainer
                        !isUnlocked -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
                        else -> MaterialTheme.colorScheme.background
                      }
                    )
                    .border(
                      1.dp,
                      if (active) MaterialTheme.colorScheme.primary
                      else MaterialTheme.colorScheme.outlineVariant,
                      RoundedCornerShape(10.dp),
                    )
                    .clickable {
                      if (isUnlocked) {
                        lockNoticeMessage = null
                        selectedPillar = pillar
                      } else {
                        lockNoticeMessage =
                          "Complete ${LessonPillar.entries[(pillar.ordinal - 1).coerceAtLeast(0)].shortLabel} first to unlock ${pillar.shortLabel}."
                      }
                    }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
              ) {
                Icon(
                  imageVector =
                    when {
                      isDone -> Icons.Filled.CheckCircle
                      !isUnlocked -> Icons.Filled.Lock
                      else -> Icons.Filled.Check
                    },
                  contentDescription = null,
                  tint =
                    when {
                      isDone -> semantics.success
                      !isUnlocked -> MaterialTheme.colorScheme.onSurfaceVariant
                      active -> MaterialTheme.colorScheme.primary
                      else -> MaterialTheme.colorScheme.onSurfaceVariant
                    },
                  modifier = Modifier.size(18.dp),
                )
                Text(
                  text = pillar.fullLabel,
                  style = MaterialTheme.typography.labelLarge,
                  color =
                    when {
                      active -> MaterialTheme.colorScheme.onPrimaryContainer
                      !isUnlocked -> MaterialTheme.colorScheme.onSurfaceVariant
                      else -> MaterialTheme.colorScheme.onSurface
                    },
                  fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                )
              }
            }
          }

          LessonPillarBody(
            lesson = lesson,
            progress = progress,
            nextLesson = nextLesson,
            passThresholdPercent = themePreferences.masteryPassPercent.toLong(),
            selectedPillar = selectedPillar,
            projectVerificationAnswers = projectVerificationAnswers,
            isProjectVerified = isProjectVerified,
            ttsController = ttsController,
            scrollState = remember(lesson.id, selectedPillar) { ScrollState(0) },
            modifier = Modifier.weight(1f).fillMaxHeight(),
            onSaveNote = onSaveNote,
            onMarkExerciseComplete = { score -> onMarkPillarComplete(selectedPillar, score) },
            onOpenNextLesson = onOpenNextLesson,
          )
        }
      } else {
        Column(modifier = Modifier.fillMaxSize()) {
          // Scroll-driven Studio 3x2 Mode Grid: collapses cleanly on slide-up, restores on slide-down
          val bodyScrollState = remember(lesson.id, selectedPillar) { ScrollState(0) }
          var manualGridOverride by remember(lesson.id, selectedPillar) { mutableStateOf<Boolean?>(null) }
          val collapsedByScroll by remember(bodyScrollState) {
            derivedStateOf { bodyScrollState.value > 56 }
          }
          LaunchedEffect(bodyScrollState.value) {
            if (bodyScrollState.value <= 12) {
              manualGridOverride = null
            } else if (bodyScrollState.value > 84 && manualGridOverride == true) {
              manualGridOverride = null
            }
          }
          val showTileGrid = manualGridOverride ?: !collapsedByScroll

          Column(
            modifier =
              Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Text(
                text = selectedPillar.fullLabel,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
              )
              Row(
                modifier =
                  Modifier.clip(RoundedCornerShape(99.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(99.dp))
                    .clickable { manualGridOverride = !showTileGrid }
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
              ) {
                Text(
                  text = if (showTileGrid) "Compact" else "Modes",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.primary,
                  fontWeight = FontWeight.Bold,
                )
                Icon(
                  imageVector = if (showTileGrid) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                  contentDescription = "Toggle mode grid",
                  tint = MaterialTheme.colorScheme.primary,
                  modifier = Modifier.size(16.dp),
                )
              }
            }

            AnimatedVisibility(
              visible = showTileGrid,
              enter = fadeIn() + expandVertically(),
              exit = fadeOut() + shrinkVertically(),
            ) {
              Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                LessonPillar.entries.chunked(3).forEach { rowPillars ->
                  Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                  ) {
                    rowPillars.forEach { pillar ->
                      val active = pillar == selectedPillar
                      val isDone = pillar.key in completedSteps
                      val isUnlocked = CurriculumProgression.isPillarUnlocked(pillar, completedSteps)
                      val icon =
                        when (pillar) {
                          LessonPillar.CONCEPT -> Icons.AutoMirrored.Filled.MenuBook
                          LessonPillar.EXAMPLES -> Icons.Filled.Lightbulb
                          LessonPillar.SCENARIO -> Icons.Filled.Public
                          LessonPillar.LAB -> Icons.Filled.Science
                          LessonPillar.PROJECT -> Icons.Filled.Build
                          LessonPillar.EXERCISE -> Icons.AutoMirrored.Filled.Assignment
                        }
                      StudioActionTile(
                        label = pillar.shortLabel,
                        icon = icon,
                        selected = active,
                        completed = isDone,
                        locked = !isUnlocked,
                        modifier = Modifier.weight(1f).testTag("pillar_tab_${pillar.key}"),
                        onClick = {
                          if (isUnlocked) {
                            lockNoticeMessage = null
                            selectedPillar = pillar
                          } else {
                            lockNoticeMessage =
                              "Finish ${LessonPillar.entries[(pillar.ordinal - 1).coerceAtLeast(0)].shortLabel} first to unlock ${pillar.shortLabel}."
                          }
                        },
                      )
                    }
                  }
                }
              }
            }

            AnimatedVisibility(
              visible = !showTileGrid,
              enter = fadeIn() + expandVertically(),
              exit = fadeOut() + shrinkVertically(),
            ) {
              Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                LessonPillar.entries.forEach { pillar ->
                  val active = pillar == selectedPillar
                  val isDone = pillar.key in completedSteps
                  val isUnlocked = CurriculumProgression.isPillarUnlocked(pillar, completedSteps)
                  val bgColor =
                    when {
                      active -> MaterialTheme.colorScheme.primary
                      isDone -> semantics.successContainer
                      !isUnlocked -> MaterialTheme.colorScheme.surfaceVariant
                      else -> MaterialTheme.colorScheme.surface
                    }
                  val textColor =
                    when {
                      active -> MaterialTheme.colorScheme.onPrimary
                      isDone -> semantics.onSuccessContainer
                      !isUnlocked -> MaterialTheme.colorScheme.onSurfaceVariant
                      else -> MaterialTheme.colorScheme.onSurface
                    }

                  Surface(
                    color = bgColor,
                    shape = RoundedCornerShape(99.dp),
                    modifier =
                      Modifier.clip(RoundedCornerShape(99.dp))
                        .clickable {
                          if (isUnlocked) {
                            lockNoticeMessage = null
                            selectedPillar = pillar
                          } else {
                            lockNoticeMessage =
                              "Finish ${LessonPillar.entries[(pillar.ordinal - 1).coerceAtLeast(0)].shortLabel} first to unlock ${pillar.shortLabel}."
                          }
                        }
                        .testTag("pillar_tab_${pillar.key}"),
                  ) {
                    Row(
                      modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                      verticalAlignment = Alignment.CenterVertically,
                      horizontalArrangement = Arrangement.spacedBy(6.dp),
                    ) {
                      when {
                        isDone ->
                          Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Completed",
                            tint = if (active) MaterialTheme.colorScheme.onPrimary else semantics.success,
                            modifier = Modifier.size(15.dp),
                          )
                        !isUnlocked ->
                          Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = "Locked",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(14.dp),
                          )
                      }
                      Text(
                        text = pillar.shortLabel,
                        style = MaterialTheme.typography.labelMedium,
                        color = textColor,
                        maxLines = 1,
                        softWrap = false,
                        fontWeight = if (active || isDone) FontWeight.Bold else FontWeight.SemiBold,
                      )
                    }
                  }
                }
              }
            }
          }

          LessonPillarBody(
            lesson = lesson,
            progress = progress,
            nextLesson = nextLesson,
            passThresholdPercent = themePreferences.masteryPassPercent.toLong(),
            selectedPillar = selectedPillar,
            projectVerificationAnswers = projectVerificationAnswers,
            isProjectVerified = isProjectVerified,
            ttsController = ttsController,
            scrollState = bodyScrollState,
            modifier = Modifier.weight(1f).fillMaxWidth(),
            onSaveNote = onSaveNote,
            onMarkExerciseComplete = { score -> onMarkPillarComplete(selectedPillar, score) },
            onOpenNextLesson = onOpenNextLesson,
          )
        }
      }
    }

    // Sticky Classroom Footer Controls
    val isCurrentDone = selectedPillar.key in completedSteps
    Row(
      modifier =
        Modifier.fillMaxWidth()
          .background(MaterialTheme.colorScheme.surface)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant)
          .padding(horizontal = 12.dp, vertical = 10.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      OutlinedButton(
        onClick = {
          val prevIdx = (selectedPillar.ordinal - 1).coerceAtLeast(0)
          lockNoticeMessage = null
          selectedPillar = LessonPillar.entries[prevIdx]
        },
        enabled = selectedPillar.ordinal > 0,
        modifier = Modifier.height(48.dp),
        shape = RoundedCornerShape(10.dp),
      ) {
        Text("← Prev", maxLines = 1, softWrap = false)
      }

      if (selectedPillar != LessonPillar.EXERCISE) {
        val projectGateActive =
          selectedPillar == LessonPillar.PROJECT && !isCurrentDone && !isProjectVerified
        OutlinedButton(
          onClick = {
            if (projectGateActive) {
              lockNoticeMessage =
                "Pass all project verification checks above before marking complete."
            } else {
              lockNoticeMessage = null
              onMarkPillarComplete(selectedPillar, null)
            }
          },
          modifier = Modifier.weight(1f).height(48.dp).testTag("mark_pillar_complete"),
          shape = RoundedCornerShape(10.dp),
        ) {
          Text(
            text =
              when {
                isCurrentDone -> "Completed ✓"
                projectGateActive -> "Verify Checks"
                else -> "Complete"
              },
            maxLines = 1,
            softWrap = false,
          )
        }

        Button(
          onClick = {
            if (projectGateActive) {
              lockNoticeMessage =
                "Pass every project verification check above to unlock the Quiz."
            } else {
              lockNoticeMessage = null
              if (!isCurrentDone) onMarkPillarComplete(selectedPillar, null)
              val nextIdx = (selectedPillar.ordinal + 1).coerceAtMost(LessonPillar.entries.lastIndex)
              selectedPillar = LessonPillar.entries[nextIdx]
            }
          },
          modifier = Modifier.weight(1f).height(48.dp).testTag("next_pillar_button"),
          shape = RoundedCornerShape(10.dp),
          colors =
            ButtonDefaults.buttonColors(
              containerColor =
                if (projectGateActive) MaterialTheme.colorScheme.surfaceVariant
                else MaterialTheme.colorScheme.primary,
              contentColor =
                if (projectGateActive) MaterialTheme.colorScheme.onSurfaceVariant
                else MaterialTheme.colorScheme.onPrimary,
            ),
        ) {
          Text(
            text = if (projectGateActive) "Locked" else "Next →",
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            softWrap = false,
          )
        }
      } else {
        Spacer(modifier = Modifier.weight(1f))
      }
    }
  }

  if (showNoteDialog) {
    var title by remember { mutableStateOf("${lesson.title.take(28)} Formula") }
    var content by remember {
      mutableStateOf(
        lesson.concepts.firstOrNull()?.keyFormula ?: lesson.learningOutcomes.firstOrNull().orEmpty()
      )
    }
    AlertDialog(
      onDismissRequest = { showNoteDialog = false },
      title = { Text("Save to Formula Notebook") },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Note Heading") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
          )
          OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Key Formula / Concept") },
            minLines = 3,
            modifier = Modifier.fillMaxWidth(),
          )
        }
      },
      confirmButton = {
        Button(
          onClick = {
            onSaveNote(title, content)
            showNoteDialog = false
          }
        ) {
          Text("Save Note")
        }
      },
      dismissButton = {
        TextButton(onClick = { showNoteDialog = false }) { Text("Cancel") }
      },
    )
  }
  if (showDisplaySettings) {
    DisplaySettingsDialog(
      themePreferences = themePreferences,
      onSetThemeMode = onSetThemeMode,
      onSetUseDynamicColor = onSetUseDynamicColor,
      onSetFontSizeScale = onSetFontSizeScale,
      onSetThemePalette = onSetThemePalette,
      onSetHighContrastBoost = onSetHighContrastBoost,
      onSetLineSpacingScale = onSetLineSpacingScale,
      onSetBoldEquations = onSetBoldEquations,
      onDismiss = { showDisplaySettings = false },
    )
  }
  if (showCalculator) {
    ScientificCalculatorSheet(onDismiss = { showCalculator = false })
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun LessonPillarBody(
  lesson: Lesson,
  progress: LessonProgress?,
  nextLesson: Lesson?,
  passThresholdPercent: Long,
  selectedPillar: LessonPillar,
  projectVerificationAnswers: MutableMap<String, Int>,
  isProjectVerified: Boolean,
  ttsController: LessonTtsController,
  scrollState: ScrollState,
  onSaveNote: (title: String, content: String) -> Unit,
  onMarkExerciseComplete: (Long?) -> Unit,
  onOpenNextLesson: ((Lesson) -> Unit)?,
  modifier: Modifier = Modifier,
) {
  val semantics = MaterialTheme.semanticColors
  val sectionTtsKey = "pillar_${lesson.id}_${selectedPillar.key}"
  val isSectionSpeaking = ttsController.isSpeaking && ttsController.activeUtteranceKey == sectionTtsKey

  Column(
    modifier =
      modifier
        .verticalScroll(scrollState)
        .padding(horizontal = 16.dp, vertical = 14.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp),
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
        text = selectedPillar.fullLabel,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.weight(1f),
      )
      Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
      ) {
        if (ttsController.isSpeaking) {
          Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(99.dp),
            modifier =
              Modifier.clip(RoundedCornerShape(99.dp))
                .clickable { ttsController.cycleSpeed() }
                .testTag("tts_speed_button"),
          ) {
            Text(
              text = "${ttsController.speechRate}×",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            )
          }
        }
        Surface(
          color =
            if (isSectionSpeaking) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.primaryContainer,
          shape = RoundedCornerShape(99.dp),
          modifier =
            Modifier.clip(RoundedCornerShape(99.dp))
              .clickable {
                ttsController.toggleSpeak(
                  utteranceKey = sectionTtsKey,
                  rawText = LessonSpeechFormatter.buildPillarSpeechText(lesson, selectedPillar),
                )
              }
              .testTag("section_tts_button"),
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
          ) {
            Icon(
              imageVector = if (isSectionSpeaking) Icons.Filled.Stop else Icons.AutoMirrored.Filled.VolumeUp,
              contentDescription = if (isSectionSpeaking) "Stop audio" else "Listen to section",
              tint =
                if (isSectionSpeaking) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onPrimaryContainer,
              modifier = Modifier.size(16.dp),
            )
            Text(
              text = if (isSectionSpeaking) "Stop" else "Listen",
              style = MaterialTheme.typography.labelSmall,
              color =
                if (isSectionSpeaking) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onPrimaryContainer,
              fontWeight = FontWeight.Bold,
              maxLines = 1,
              softWrap = false,
            )
          }
        }
      }
    }

    when (selectedPillar) {
      LessonPillar.CONCEPT -> {
        if (lesson.prerequisites.isNotEmpty() || lesson.dependencySummary.isNotBlank()) {
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
          ) {
            Text(
              text = "PREREQUISITES",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.secondary,
              fontWeight = FontWeight.Bold,
            )
            if (lesson.prerequisites.isNotEmpty()) {
              ScientificText(
                text = "Foundations Needed: ${lesson.prerequisites.joinToString(" • ")}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
              )
            }
            if (lesson.dependencySummary.isNotBlank()) {
              ScientificText(
                text = lesson.dependencySummary,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }
          }
        }

        Column(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(14.dp))
              .background(MaterialTheme.colorScheme.primaryContainer)
              .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(14.dp))
              .padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
          Text(
            text = "LEARNING OUTCOMES",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
          )
          lesson.learningOutcomes.forEach { outcome ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
              Text("•", color = MaterialTheme.colorScheme.onPrimaryContainer, fontWeight = FontWeight.Bold)
              ScientificText(
                text = outcome,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
              )
            }
          }
        }

        if (lesson.vocabulary.isNotEmpty()) {
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(14.dp))
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Text(
              text = "KEY TERMS",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.Bold,
            )
            lesson.vocabulary.forEachIndexed { vIdx, entry ->
              Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(
                  text = entry.term,
                  style = MaterialTheme.typography.titleSmall,
                  color = MaterialTheme.colorScheme.onSurface,
                  fontWeight = FontWeight.Bold,
                )
                ScientificText(
                  text = "Core Meaning: ${entry.simpleMeaning}",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.secondary,
                  fontWeight = FontWeight.SemiBold,
                )
                ScientificText(
                  text = "Definition: ${entry.definition}",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurface,
                )
                ScientificText(
                  text = "Exam Usage: ${entry.examUsage}",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (vIdx < lesson.vocabulary.lastIndex) {
                  HorizontalDivider(
                    modifier = Modifier.padding(top = 6.dp),
                    color = MaterialTheme.colorScheme.outlineVariant,
                  )
                }
              }
            }
          }
        }

        TopicDiagramCard(lesson = lesson)

        lesson.concepts.forEachIndexed { idx, block ->
          val cleanHeading = block.heading.replace(Regex("""^\d+\.\s+"""), "").trim()
          val cardTtsKey = "concept_${lesson.id}_$idx"
          val isCardSpeaking = ttsController.isSpeaking && ttsController.activeUtteranceKey == cardTtsKey

          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(6.dp),
              ) {
                Text(
                  text = "TOPIC ${idx + 1}/${lesson.concepts.size}",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.primary,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                )
              }
              Surface(
                color =
                  if (isCardSpeaking) MaterialTheme.colorScheme.primary
                  else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                shape = RoundedCornerShape(99.dp),
                modifier =
                  Modifier.clip(RoundedCornerShape(99.dp))
                    .clickable {
                      ttsController.toggleSpeak(
                        utteranceKey = cardTtsKey,
                        rawText = LessonSpeechFormatter.buildConceptSpeechText(block.copy(heading = cleanHeading)),
                      )
                    }
                    .testTag("concept_tts_$idx"),
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                  Icon(
                    imageVector = if (isCardSpeaking) Icons.Filled.Stop else Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = if (isCardSpeaking) "Stop audio" else "Listen to this section",
                    tint =
                      if (isCardSpeaking) MaterialTheme.colorScheme.onPrimary
                      else MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(14.dp),
                  )
                  Text(
                    text = if (isCardSpeaking) "Stop" else "Listen",
                    style = MaterialTheme.typography.labelSmall,
                    color =
                      if (isCardSpeaking) MaterialTheme.colorScheme.onPrimary
                      else MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                  )
                }
              }
            }
            Text(
              text = cleanHeading,
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
            ScientificText(
              text = block.explanation,
              style = MaterialTheme.typography.bodyLarge,
              color = MaterialTheme.colorScheme.onSurface,
            )
            if (block.mechanismWhyHow.isNotBlank()) {
              Surface(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(10.dp),
                modifier =
                  Modifier.fillMaxWidth()
                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(10.dp)),
              ) {
                ScientificText(
                  text = "Why & How It Works: ${block.mechanismWhyHow}",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurface,
                  modifier = Modifier.padding(10.dp),
                )
              }
            }
            if (!block.keyFormula.isNullOrBlank()) {
              EquationDisplayCard(
                formula = block.keyFormula,
                badgeLabel = null,
                onSaveFormula = { savedFormula ->
                  onSaveNote("${lesson.title.take(26)} — ${block.heading.take(24)}", savedFormula)
                },
              )
            }
            if (block.examIntelligence.isNotBlank()) {
              Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(),
              ) {
                ScientificText(
                  text = "JAMB / WAEC / NECO Focus: ${block.examIntelligence}",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSecondaryContainer,
                  modifier = Modifier.padding(10.dp),
                )
              }
            }
            Row(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(12.dp))
                  .background(MaterialTheme.colorScheme.tertiaryContainer)
                  .border(1.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(12.dp))
                  .padding(12.dp),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              Icon(
                Icons.Filled.Lightbulb,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier.size(18.dp),
              )
              ScientificText(
                text = "Examiner Tip: ${block.examTip}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                fontWeight = FontWeight.Medium,
              )
            }
            if (block.recap.isNotBlank()) {
              ScientificText(
                text = "Checkpoint Recap: ${block.recap}",
                style = MaterialTheme.typography.labelMedium,
                color = semantics.success,
                fontWeight = FontWeight.SemiBold,
              )
            }
          }
        }
      }

      LessonPillar.EXAMPLES -> {
        lesson.workedExamples.forEachIndexed { exIdx, example ->
          var revealedCount by remember(lesson.id, exIdx) { mutableIntStateOf(0) }
          val totalSteps = example.steps.size
          val allRevealed = totalSteps == 0 || revealedCount >= totalSteps

          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            val exampleTtsKey = "example_${lesson.id}_$exIdx"
            val isExampleSpeaking =
              ttsController.isSpeaking && ttsController.activeUtteranceKey == exampleTtsKey

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(99.dp),
              ) {
                Text(
                  text = example.examSource,
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.onSecondaryContainer,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                )
              }
              Surface(
                color =
                  if (isExampleSpeaking) MaterialTheme.colorScheme.primary
                  else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                shape = RoundedCornerShape(99.dp),
                modifier =
                  Modifier.clip(RoundedCornerShape(99.dp))
                    .clickable {
                      ttsController.toggleSpeak(
                        utteranceKey = exampleTtsKey,
                        rawText = LessonSpeechFormatter.buildExampleSpeechText(example),
                      )
                    }
                    .testTag("example_tts_$exIdx"),
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                  Icon(
                    imageVector = if (isExampleSpeaking) Icons.Filled.Stop else Icons.AutoMirrored.Filled.VolumeUp,
                    contentDescription = if (isExampleSpeaking) "Stop audio" else "Listen to example",
                    tint =
                      if (isExampleSpeaking) MaterialTheme.colorScheme.onPrimary
                      else MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(14.dp),
                  )
                  Text(
                    text = if (isExampleSpeaking) "Stop" else "Listen",
                    style = MaterialTheme.typography.labelSmall,
                    color =
                      if (isExampleSpeaking) MaterialTheme.colorScheme.onPrimary
                      else MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                  )
                }
              }
            }
            Text(
              text = example.title,
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
            ScientificText(
              text = example.problemStatement,
              style = MaterialTheme.typography.bodyLarge,
              color = MaterialTheme.colorScheme.onSurface,
              fontWeight = FontWeight.SemiBold,
            )

            if (revealedCount == 0 && totalSteps > 0) {
              Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(),
              ) {
                Text(
                  text = "Try setting up your formula on paper first, then reveal steps one at a time.",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  modifier = Modifier.padding(10.dp),
                )
              }
            }

            if (revealedCount > 0) {
              Column(
                modifier =
                  Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
              ) {
                example.steps.take(revealedCount).forEach { step ->
                  ScientificText(
                    text = step,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                  )
                }
              }
            }

            if (totalSteps > 0) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                if (!allRevealed) {
                  OutlinedButton(
                    onClick = { revealedCount++ },
                    modifier = Modifier.weight(1f).height(48.dp).testTag("reveal_step_$exIdx"),
                    shape = RoundedCornerShape(10.dp),
                  ) {
                    Text("Step ${revealedCount + 1}/$totalSteps", maxLines = 1, softWrap = false)
                  }
                }
                Button(
                  onClick = { revealedCount = if (allRevealed) 0 else totalSteps },
                  modifier = Modifier.weight(1f).height(48.dp).testTag("reveal_all_steps_$exIdx"),
                  shape = RoundedCornerShape(10.dp),
                ) {
                  Text(
                    text = if (allRevealed) "Hide Solution" else "Reveal All",
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    softWrap = false,
                  )
                }
              }
            }

            if (allRevealed) {
              Surface(
                color = semantics.successContainer,
                shape = RoundedCornerShape(12.dp),
                modifier =
                  Modifier.fillMaxWidth()
                    .border(1.dp, semantics.success, RoundedCornerShape(12.dp)),
              ) {
                ScientificText(
                  text = "Final Answer: ${example.finalAnswer}",
                  style = MaterialTheme.typography.titleSmall,
                  color = semantics.onSuccessContainer,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(12.dp),
                )
              }
              Row(
                modifier =
                  Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.errorContainer)
                    .border(1.dp, MaterialTheme.colorScheme.error, RoundedCornerShape(12.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                Icon(
                  Icons.Filled.WarningAmber,
                  contentDescription = null,
                  tint = MaterialTheme.colorScheme.onErrorContainer,
                  modifier = Modifier.size(18.dp),
                )
                ScientificText(
                  text = "Common Trap: ${example.examinerTrap}",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onErrorContainer,
                  fontWeight = FontWeight.Medium,
                )
              }
            }
          }
        }

        val guided = lesson.guidedPractice
        if (guided != null) {
          GuidedPracticeCard(lessonId = lesson.id, task = guided)
        }
      }

      LessonPillar.SCENARIO -> {
        lesson.scenarios.forEach { scenario ->
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              Icon(
                Icons.Filled.Public,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
              )
              Text(
                text = scenario.industryDomain.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
              )
            }
            Text(
              text = scenario.title,
              style = MaterialTheme.typography.titleMedium,
              color = MaterialTheme.colorScheme.onSurface,
            )
            ScientificText(
              text = scenario.narrative,
              style = MaterialTheme.typography.bodyLarge,
              color = MaterialTheme.colorScheme.onSurface,
            )
            Surface(
              color = MaterialTheme.colorScheme.primaryContainer,
              shape = RoundedCornerShape(12.dp),
              modifier =
                Modifier.fillMaxWidth()
                  .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp)),
            ) {
              ScientificText(
                text = "Exam Connection: ${scenario.examConnection}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(12.dp),
              )
            }
          }
        }

        if (lesson.misconceptions.isNotEmpty()) {
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              Icon(
                Icons.Filled.Psychology,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
              )
              Text(
                text = "COMMON MISCONCEPTIONS",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold,
              )
            }
            lesson.misconceptions.forEach { misc ->
              Column(
                modifier =
                  Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
              ) {
                ScientificText(
                  text = "Wrong Belief: ${misc.wrongBelief}",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.error,
                  fontWeight = FontWeight.Bold,
                )
                ScientificText(
                  text = "Why Students Fall For It: ${misc.whyStudentsBelieveIt}",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                ScientificText(
                  text = "Correct Understanding: ${misc.correctUnderstanding}",
                  style = MaterialTheme.typography.bodySmall,
                  color = semantics.success,
                  fontWeight = FontWeight.SemiBold,
                )
                ScientificText(
                  text = "Avoidance Rule: ${misc.avoidanceRule}",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.primary,
                )
              }
            }
          }
        }

        val cheat = lesson.cheatSheet
        if (cheat != null) {
          Column(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.65f), RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Text(
                text = "SUMMARY & NOTES",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
              )
              OutlinedButton(
                onClick = {
                  val digest = buildString {
                    appendLine(cheat.overview)
                    if (cheat.symbolLegend.isNotEmpty()) {
                      appendLine()
                      appendLine(cheat.symbolLegend.joinToString("\n"))
                    }
                    if (cheat.memoryAid.isNotBlank()) {
                      appendLine()
                      append("Memory Aid: ${cheat.memoryAid}")
                    }
                  }
                  onSaveNote("${lesson.title.take(26)} — Summary", digest)
                },
                shape = RoundedCornerShape(99.dp),
                modifier = Modifier.height(36.dp).testTag("save_cheat_sheet_button"),
              ) {
                Icon(Icons.Filled.BookmarkAdd, contentDescription = null, modifier = Modifier.size(15.dp))
                Spacer(Modifier.width(4.dp))
                Text("Save", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, maxLines = 1)
              }
            }

            Surface(
              color = MaterialTheme.colorScheme.background,
              shape = RoundedCornerShape(12.dp),
              modifier =
                Modifier.fillMaxWidth()
                  .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp)),
            ) {
              ScientificText(
                text = cheat.overview,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(12.dp),
              )
            }

            if (cheat.symbolLegend.isNotEmpty()) {
              Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                  text = "SYMBOLS & CONSTANTS",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.secondary,
                  fontWeight = FontWeight.Bold,
                )
                cheat.symbolLegend.forEach { entry ->
                  Row(
                    modifier =
                      Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f))
                        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(10.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                  ) {
                    Text(
                      text = "•",
                      style = MaterialTheme.typography.titleSmall,
                      color = MaterialTheme.colorScheme.primary,
                      fontWeight = FontWeight.Bold,
                    )
                    ScientificText(
                      text = entry,
                      style = MaterialTheme.typography.bodySmall,
                      color = MaterialTheme.colorScheme.onSurface,
                    )
                  }
                }
              }
            }

            if (cheat.commonTraps.isNotEmpty()) {
              Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                  text = "EXAM TRAPS",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.error,
                  fontWeight = FontWeight.Bold,
                )
                cheat.commonTraps.forEach { trap ->
                  Row(
                    modifier =
                      Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.55f))
                        .border(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.45f), RoundedCornerShape(10.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                  ) {
                    Icon(
                      imageVector = Icons.Filled.WarningAmber,
                      contentDescription = null,
                      tint = MaterialTheme.colorScheme.error,
                      modifier = Modifier.size(16.dp),
                    )
                    ScientificText(
                      text = trap,
                      style = MaterialTheme.typography.bodySmall,
                      color = MaterialTheme.colorScheme.onErrorContainer,
                    )
                  }
                }
              }
            }

            Surface(
              color = MaterialTheme.colorScheme.tertiaryContainer,
              shape = RoundedCornerShape(12.dp),
              modifier =
                Modifier.fillMaxWidth()
                  .border(1.dp, MaterialTheme.colorScheme.tertiary.copy(alpha = 0.55f), RoundedCornerShape(12.dp)),
            ) {
              Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
              ) {
                Text(
                  text = "MEMORY ANCHOR",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.onTertiaryContainer,
                  fontWeight = FontWeight.Bold,
                )
                ScientificText(
                  text = cheat.memoryAid,
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onTertiaryContainer,
                  fontWeight = FontWeight.Medium,
                )
              }
            }

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
              Text(
                text = "SPACED REVIEW DAYS",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
              )
              FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
              ) {
                cheat.spacedRevisionDays.forEach { day ->
                  Surface(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(8.dp),
                    modifier =
                      Modifier.border(
                        1.dp,
                        MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f),
                        RoundedCornerShape(8.dp),
                      ),
                  ) {
                    Text(
                      text = "Day $day",
                      style = MaterialTheme.typography.labelSmall.copy(fontFeatureSettings = "tnum"),
                      color = MaterialTheme.colorScheme.onSecondaryContainer,
                      fontWeight = FontWeight.Bold,
                      modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    )
                  }
                }
              }
            }
          }
        }
      }

      LessonPillar.LAB -> {
        PracticalLabWorkspace(
          lessonId = lesson.id,
          labType = lesson.labType,
          labBrief = lesson.labBrief,
        )
      }

      LessonPillar.PROJECT -> {
        val project = lesson.masteryProject
        if (project != null) {
          TopicMasteryProjectCard(
            lessonId = lesson.id,
            project = project,
            selectedAnswers = projectVerificationAnswers,
            isProjectVerified = isProjectVerified,
          )
        } else {
          Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
          ) {
            Text(
              text = "Apply the core principles from ${lesson.title} to solve an everyday Nigerian engineering or civic case and verify every unit.",
              style = MaterialTheme.typography.bodyMedium,
              modifier = Modifier.padding(16.dp),
            )
          }
        }
      }

      LessonPillar.EXERCISE -> {
        InlineExerciseSection(
          lessonId = lesson.id,
          questions = lesson.exercises,
          isAlreadyMastered = progress?.isCompleted == true,
          passThresholdPercent = passThresholdPercent,
          nextLesson = nextLesson,
          onGraded = { pct -> onMarkExerciseComplete(pct) },
          onOpenNextLesson = onOpenNextLesson,
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))
  }
}

@Composable
private fun GuidedPracticeCard(lessonId: String, task: GuidedPracticeTask) {
  var revealedHints by remember(lessonId) { mutableIntStateOf(0) }
  var showSolution by remember(lessonId) { mutableStateOf(false) }
  val semantics = MaterialTheme.semanticColors

  Column(
    modifier =
      Modifier.fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.5.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
        .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(99.dp)) {
        Text(
          text = "GUIDED PRACTICE • ${task.difficulty.uppercase()}",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onPrimaryContainer,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
        )
      }
      Text(
        text = "~${task.estimatedMinutes} min",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }

    ScientificText(
      text = task.prompt,
      style = MaterialTheme.typography.bodyLarge,
      color = MaterialTheme.colorScheme.onSurface,
      fontWeight = FontWeight.SemiBold,
    )

    task.hints.take(revealedHints).forEachIndexed { idx, hint ->
      Surface(
        color = MaterialTheme.colorScheme.tertiaryContainer,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(),
      ) {
        ScientificText(
          text = "Hint ${idx + 1}: $hint",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onTertiaryContainer,
          modifier = Modifier.padding(10.dp),
        )
      }
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      if (revealedHints < task.hints.size) {
        OutlinedButton(
          onClick = { revealedHints++ },
          modifier = Modifier.weight(1f).height(48.dp),
          shape = RoundedCornerShape(10.dp),
        ) {
          Text("Hint ${revealedHints + 1}/${task.hints.size}", maxLines = 1, softWrap = false)
        }
      }
      Button(
        onClick = { showSolution = !showSolution },
        modifier = Modifier.weight(1f).height(48.dp),
        shape = RoundedCornerShape(10.dp),
      ) {
        Text(
          text = if (showSolution) "Hide Solution" else "Full Solution",
          maxLines = 1,
          softWrap = false,
        )
      }
    }

    if (showSolution) {
      Surface(
        color = semantics.successContainer,
        shape = RoundedCornerShape(12.dp),
        modifier =
          Modifier.fillMaxWidth()
            .border(1.dp, semantics.success, RoundedCornerShape(12.dp)),
      ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
          ScientificText(
            text = "Expected Strategy: ${task.expectedApproach}",
            style = MaterialTheme.typography.labelMedium,
            color = semantics.onSuccessContainer,
            fontWeight = FontWeight.Bold,
          )
          ScientificText(
            text = task.completeSolution,
            style = MaterialTheme.typography.bodySmall,
            color = semantics.onSuccessContainer,
          )
        }
      }
    }
  }
}

@Composable
private fun TopicMasteryProjectCard(
  lessonId: String,
  project: TopicMasteryProject,
  selectedAnswers: MutableMap<String, Int>,
  isProjectVerified: Boolean,
) {
  var showModel by remember(lessonId) { mutableStateOf(false) }
  val semantics = MaterialTheme.semanticColors
  val tasks = project.verificationTasks
  val passedCount = tasks.count { selectedAnswers[it.id] == it.correctIndex }

  Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.surface)
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
          .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Icon(
          Icons.Filled.Build,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.primary,
          modifier = Modifier.size(18.dp),
        )
        Text(
          text = "APPLIED PROJECT • ${project.format.uppercase()}",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.Bold,
        )
      }
      Text(
        text = project.title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
      )
      ScientificText(
        text = project.nigerianScenario,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface,
      )
      Text(
        text = "WORKFLOW",
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.secondary,
        fontWeight = FontWeight.Bold,
      )
      project.stages.forEachIndexed { idx, stage ->
        Row(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(10.dp))
              .background(MaterialTheme.colorScheme.background)
              .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(10.dp))
              .padding(10.dp),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          Text(
            text = "${idx + 1}.",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
          )
          ScientificText(
            text = stage,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
          )
        }
      }
    }

    // Interactive App-Graded Project Verification
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .background(MaterialTheme.colorScheme.surface)
          .border(
            1.5.dp,
            if (isProjectVerified) semantics.success else MaterialTheme.colorScheme.primary,
            RoundedCornerShape(16.dp),
          )
          .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Row(
          modifier = Modifier.weight(1f),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          Icon(
            imageVector = Icons.Filled.VerifiedUser,
            contentDescription = null,
            tint = if (isProjectVerified) semantics.success else MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(18.dp),
          )
          Text(
            text = "PROJECT CHECKS",
            style = MaterialTheme.typography.labelSmall,
            color = if (isProjectVerified) semantics.success else MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
          )
        }
        Surface(
          color =
            if (isProjectVerified) semantics.successContainer
            else MaterialTheme.colorScheme.primaryContainer,
          shape = RoundedCornerShape(99.dp),
        ) {
          Text(
            text = "$passedCount/${tasks.size} PASSED",
            style = MaterialTheme.typography.labelSmall.copy(fontFeatureSettings = "tnum"),
            color =
              if (isProjectVerified) semantics.onSuccessContainer
              else MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
          )
        }
      }

      Text(
        text =
          if (isProjectVerified) {
            "All checks passed. Review the model solution below or advance to the Mastery Quiz."
          } else {
            "Complete each checkpoint below to validate your project reasoning."
          },
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )

      tasks.forEachIndexed { idx, task ->
        val picked = selectedAnswers[task.id]
        val isCorrect = picked == task.correctIndex
        Column(
          modifier =
            Modifier.fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
              .background(MaterialTheme.colorScheme.background)
              .border(
                1.dp,
                when {
                  picked == null -> MaterialTheme.colorScheme.outlineVariant
                  isCorrect -> semantics.success
                  else -> MaterialTheme.colorScheme.error
                },
                RoundedCornerShape(12.dp),
              )
              .padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          Text(
            text = "CHECK ${idx + 1} • ${task.criterionTitle.uppercase()}",
            style = MaterialTheme.typography.labelSmall,
            color = if (isCorrect) semantics.success else MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold,
          )
          ScientificText(
            text = task.prompt,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold,
          )
          task.options.forEachIndexed { optIdx, optText ->
            val selected = picked == optIdx
            val optBg =
              when {
                selected && optIdx == task.correctIndex -> semantics.successContainer
                selected -> MaterialTheme.colorScheme.errorContainer
                else -> MaterialTheme.colorScheme.surface
              }
            val optBorder =
              when {
                selected && optIdx == task.correctIndex -> semantics.success
                selected -> MaterialTheme.colorScheme.error
                else -> MaterialTheme.colorScheme.outlineVariant
              }
            val optFg =
              when {
                selected && optIdx == task.correctIndex -> semantics.onSuccessContainer
                selected -> MaterialTheme.colorScheme.onErrorContainer
                else -> MaterialTheme.colorScheme.onSurface
              }
            Row(
              modifier =
                Modifier.fillMaxWidth()
                  .clip(RoundedCornerShape(10.dp))
                  .background(optBg)
                  .border(1.2.dp, optBorder, RoundedCornerShape(10.dp))
                  .clickable { selectedAnswers[task.id] = optIdx }
                  .padding(horizontal = 12.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
              Text(
                text = "${'A' + optIdx}.",
                style = MaterialTheme.typography.labelLarge,
                color = optFg,
                fontWeight = FontWeight.Bold,
              )
              ScientificText(
                text = optText,
                style = MaterialTheme.typography.bodySmall,
                color = optFg,
                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
              )
            }
          }

          if (picked != null) {
            Surface(
              color =
                if (isCorrect) semantics.successContainer
                else MaterialTheme.colorScheme.errorContainer,
              shape = RoundedCornerShape(8.dp),
              modifier = Modifier.fillMaxWidth(),
            ) {
              ScientificText(
                text =
                  if (isCorrect) {
                    val cleaned =
                      task.feedback
                        .replace(
                          Regex("""^(?:(?:Verified|Confirmed|Passed|Correct)\s*:\s*)+""", RegexOption.IGNORE_CASE),
                          "",
                        )
                        .trim()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                    "Verified: $cleaned"
                  } else {
                    "Needs Revision: Re-check the project principles and select the scientifically accurate choice."
                  },
                style = MaterialTheme.typography.bodySmall,
                color =
                  if (isCorrect) semantics.onSuccessContainer
                  else MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.padding(10.dp),
              )
            }
          }
        }
      }

      if (project.rubricCriteria.isNotEmpty()) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text(
            text = "RUBRIC STANDARDS",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Bold,
          )
          project.rubricCriteria.forEach { criterion ->
            Row(
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              verticalAlignment = Alignment.Top,
            ) {
              Icon(
                imageVector = if (isProjectVerified) Icons.Filled.CheckCircle else Icons.Filled.Check,
                contentDescription = null,
                tint = if (isProjectVerified) semantics.success else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(15.dp),
              )
              ScientificText(
                text = criterion,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }
          }
        }
      }

      OutlinedButton(
        onClick = { if (isProjectVerified) showModel = !showModel },
        enabled = isProjectVerified,
        modifier = Modifier.fillMaxWidth().height(48.dp).testTag("toggle_project_model_solution"),
        shape = RoundedCornerShape(10.dp),
      ) {
        Text(
          text =
            when {
              !isProjectVerified -> "Pass ${tasks.size} Checks to Unlock Solution"
              showModel -> "Hide Model Solution"
              else -> "Show Model Solution"
            },
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
        )
      }
      if (showModel && isProjectVerified) {
        Surface(
          color = semantics.successContainer,
          shape = RoundedCornerShape(12.dp),
          modifier =
            Modifier.fillMaxWidth()
              .border(1.dp, semantics.success, RoundedCornerShape(12.dp)),
        ) {
          ScientificText(
            text = project.modelResponse,
            style = MaterialTheme.typography.bodySmall,
            color = semantics.onSuccessContainer,
            modifier = Modifier.padding(12.dp),
          )
        }
      }
    }
  }
}

@Composable
private fun InlineExerciseSection(
  lessonId: String,
  questions: List<PracticeQuestion>,
  isAlreadyMastered: Boolean,
  passThresholdPercent: Long,
  nextLesson: Lesson?,
  onGraded: (Long) -> Unit,
  onOpenNextLesson: ((Lesson) -> Unit)?,
) {
  val selectedAnswers = remember(lessonId) { mutableStateMapOf<String, Int>() }
  val hintShown = remember(lessonId) { mutableStateMapOf<String, Boolean>() }
  var submitted by remember(lessonId) { mutableStateOf(false) }
  var attemptCount by remember(lessonId) { mutableIntStateOf(0) }
  var retryNonce by remember(lessonId) { mutableIntStateOf(0) }
  var seenIds by remember(lessonId) { mutableStateOf(emptySet<String>()) }
  var lastRoundIds by remember(lessonId) { mutableStateOf(emptySet<String>()) }
  val batchSize = minOf(5, questions.size).coerceAtLeast(1)
  val activeQuestions =
    remember(lessonId, retryNonce, questions) {
      QuestionShuffler.prepareFreshQuestionSet(
        pool = questions,
        count = batchSize,
        previouslySeenIds = seenIds,
        lastRoundIds = lastRoundIds,
      )
    }
  val questionWeights =
    remember(activeQuestions.size) {
      when (activeQuestions.size) {
        5 -> listOf(15, 20, 20, 20, 25)
        4 -> listOf(25, 25, 25, 25)
        3 -> listOf(30, 35, 35)
        2 -> listOf(50, 50)
        else -> List(activeQuestions.size) { 100 / activeQuestions.size.coerceAtLeast(1) }
      }
    }
  val semantics = MaterialTheme.semanticColors

  Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
    Surface(
      color = MaterialTheme.colorScheme.surfaceVariant,
      shape = RoundedCornerShape(10.dp),
      modifier = Modifier.fillMaxWidth(),
    ) {
      Text(
        text =
          "Attempt #${attemptCount + 1} • 100-Mark Mastery Assessment (${activeQuestions.size} Qs from ${questions.size}-item Bank • Pass ≥${passThresholdPercent}%)",
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
      )
    }

    activeQuestions.forEachIndexed { idx, q ->
      val chosen = selectedAnswers[q.id]
      val marksForQ = questionWeights.getOrElse(idx) { 20 }
      Column(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
      ) {
        Text(
          text = "Q${idx + 1} • ${q.boardStyleLabel} • ${q.cognitiveLevel} ($marksForQ marks)",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.Bold,
        )
        if (!q.passageText.isNullOrBlank()) {
          Surface(
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(),
          ) {
            ScientificText(
              text = q.passageText,
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSecondaryContainer,
              modifier = Modifier.padding(10.dp),
            )
          }
        }
        ScientificText(
          text = q.question,
          style = MaterialTheme.typography.titleMedium,
          color = MaterialTheme.colorScheme.onSurface,
        )

        q.options.forEachIndexed { optIdx, optText ->
          val isSelected = chosen == optIdx
          val borderColor =
            when {
              submitted && optIdx == q.correctIndex -> semantics.success
              submitted && isSelected && optIdx != q.correctIndex -> MaterialTheme.colorScheme.error
              isSelected -> MaterialTheme.colorScheme.primary
              else -> MaterialTheme.colorScheme.outline
            }
          val bgColor =
            when {
              submitted && optIdx == q.correctIndex -> semantics.successContainer
              submitted && isSelected && optIdx != q.correctIndex -> MaterialTheme.colorScheme.errorContainer
              isSelected -> MaterialTheme.colorScheme.primaryContainer
              else -> MaterialTheme.colorScheme.background
            }
          val optionTextColor =
            when {
              submitted && optIdx == q.correctIndex -> semantics.onSuccessContainer
              submitted && isSelected && optIdx != q.correctIndex -> MaterialTheme.colorScheme.onErrorContainer
              isSelected -> MaterialTheme.colorScheme.onPrimaryContainer
              else -> MaterialTheme.colorScheme.onSurface
            }

          Row(
            modifier =
              Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(bgColor)
                .border(1.5.dp, borderColor, RoundedCornerShape(12.dp))
                .clickable(enabled = !submitted) { selectedAnswers[q.id] = optIdx }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Box(
              modifier =
                Modifier.size(28.dp)
                  .clip(CircleShape)
                  .background(
                    if (isSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surfaceVariant
                  ),
              contentAlignment = Alignment.Center,
            ) {
              Text(
                text = ('A' + optIdx).toString(),
                style = MaterialTheme.typography.labelMedium,
                color =
                  if (isSelected) MaterialTheme.colorScheme.onPrimary
                  else MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
              )
            }
            ScientificText(
              text = optText,
              style = MaterialTheme.typography.bodyMedium,
              color = optionTextColor,
              fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            )
          }
        }

        if (!submitted) {
          TextButton(onClick = { hintShown[q.id] = !(hintShown[q.id] ?: false) }) {
            Text(if (hintShown[q.id] == true) "Hide Hint" else "Show Academic Hint")
          }
          if (hintShown[q.id] == true) {
            Surface(
              color = MaterialTheme.colorScheme.tertiaryContainer,
              shape = RoundedCornerShape(10.dp),
              modifier = Modifier.fillMaxWidth(),
            ) {
              ScientificText(
                text = "Hint: ${q.hint}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier.padding(10.dp),
              )
            }
          }
        } else {
          Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
          ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(
                text = "Step-by-Step Solution:",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
              )
              ScientificText(
                text = q.stepByStepSolution,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
              )
              if (!q.waecMarkingScheme.isNullOrBlank()) {
                ScientificText(
                  text = "Examiner Marking Scheme: ${q.waecMarkingScheme}",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.primary,
                  fontWeight = FontWeight.Bold,
                )
              }
            }
          }
        }
      }
    }

    if (!submitted) {
      Button(
        onClick = {
          submitted = true
          attemptCount++
          val roundIds = activeQuestions.map { it.id }.toSet()
          val updatedSeen = seenIds + roundIds
          seenIds = if (updatedSeen.size >= questions.size) roundIds else updatedSeen
          lastRoundIds = roundIds
          val totalMarks = questionWeights.sum().coerceAtLeast(1)
          val earnedMarks =
            activeQuestions.mapIndexed { idx, q ->
              if (selectedAnswers[q.id] == q.correctIndex) questionWeights.getOrElse(idx) { 20 } else 0
            }.sum()
          val pct = ((earnedMarks * 100L) / totalMarks).coerceIn(0L, 100L)
          onGraded(pct)
        },
        enabled = selectedAnswers.size == activeQuestions.size,
        modifier = Modifier.fillMaxWidth().height(52.dp).testTag("submit_inline_exercise"),
        shape = RoundedCornerShape(12.dp),
      ) {
        Text("Submit Assessment (≥${passThresholdPercent}% Required)", fontWeight = FontWeight.Bold)
      }
    } else {
      val correct = activeQuestions.count { selectedAnswers[it.id] == it.correctIndex }
      val totalMarks = questionWeights.sum().coerceAtLeast(1)
      val earnedMarks =
        activeQuestions.mapIndexed { idx, q ->
          if (selectedAnswers[q.id] == q.correctIndex) questionWeights.getOrElse(idx) { 20 } else 0
        }.sum()
      val pct = ((earnedMarks * 100L) / totalMarks).coerceIn(0L, 100L)
      val band = CurriculumProgression.bandForPercentage(pct)
      val passed = pct >= passThresholdPercent || isAlreadyMastered
      val missedQuestions = activeQuestions.filter { selectedAnswers[it.id] != it.correctIndex }

      Column(
        modifier =
          Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(if (passed) semantics.successContainer else MaterialTheme.colorScheme.errorContainer)
            .border(
              1.5.dp,
              if (passed) semantics.success else MaterialTheme.colorScheme.error,
              RoundedCornerShape(16.dp),
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
      ) {
        Text(
          text =
            if (passed) {
              "${band.label} • $earnedMarks/$totalMarks marks ($correct/${activeQuestions.size} Qs • ${pct}%)"
            } else {
              "${band.label} • $earnedMarks/$totalMarks marks (${pct}% — Need ${passThresholdPercent}%)"
            },
          style = MaterialTheme.typography.titleMedium,
          color = if (passed) semantics.onSuccessContainer else MaterialTheme.colorScheme.onErrorContainer,
          fontWeight = FontWeight.Bold,
        )
        Text(
          text =
            if (passed) {
              if (nextLesson != null) "Mastery threshold met! Next lesson unlocked: \"${nextLesson.title}\""
              else "Final lesson in this track mastered! You are ready for timed Exam Lab mocks."
            } else {
              "${band.summary} Tap Retry for a fresh shuffled paper."
            },
          style = MaterialTheme.typography.bodySmall,
          color = if (passed) semantics.onSuccessContainer else MaterialTheme.colorScheme.onErrorContainer,
        )

        if (!passed && missedQuestions.isNotEmpty()) {
          Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(),
          ) {
            Column(
              modifier = Modifier.padding(12.dp),
              verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
              Text(
                text = "TARGETED DIAGNOSTIC REMEDIATION",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold,
              )
              missedQuestions.forEach { mq ->
                ScientificText(
                  text = "• ${mq.topic}: ${mq.remediationTip}",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurface,
                )
              }
            }
          }
        }

        if (passed && nextLesson != null && onOpenNextLesson != null) {
          Button(
            onClick = { onOpenNextLesson(nextLesson) },
            modifier = Modifier.fillMaxWidth().height(48.dp).testTag("continue_next_lesson_button"),
            shape = RoundedCornerShape(10.dp),
            colors =
              ButtonDefaults.buttonColors(
                containerColor = semantics.success,
                contentColor = semantics.onSuccess,
              ),
          ) {
            Text("Continue to Next Lesson", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(6.dp))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
          }
          OutlinedButton(
            onClick = {
              selectedAnswers.clear()
              hintShown.clear()
              submitted = false
              retryNonce++
            },
            modifier = Modifier.fillMaxWidth().height(48.dp).testTag("retry_inline_exercise"),
            shape = RoundedCornerShape(10.dp),
          ) {
            Icon(Icons.Filled.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Practice ${activeQuestions.size} Fresh Questions", fontWeight = FontWeight.SemiBold)
          }
        } else {
          Button(
            onClick = {
              selectedAnswers.clear()
              hintShown.clear()
              submitted = false
              retryNonce++
            },
            modifier = Modifier.fillMaxWidth().height(48.dp).testTag("retry_inline_exercise"),
            shape = RoundedCornerShape(10.dp),
          ) {
            Icon(Icons.Filled.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text =
                if (passed) "Practice ${activeQuestions.size} Fresh Questions"
                else "Retry Fresh Mastery Paper (Attempt #${attemptCount + 1})",
              fontWeight = FontWeight.Bold,
            )
          }
        }
      }
    }
  }
}
