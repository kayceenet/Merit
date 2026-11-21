package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.curriculum.CourseModule
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.CurriculumProgression
import com.example.data.curriculum.Lesson
import com.example.data.curriculum.LessonPillar
import com.example.data.curriculum.SubjectTrack
import com.example.data.local.FontSizeScale
import com.example.data.local.ThemeMode
import com.example.data.local.ThemePreferences
import com.example.data.model.ExamAttempt
import com.example.data.model.LessonProgress
import com.example.data.model.StudyNote
import com.example.data.model.UserProfile
import com.example.ui.components.StudioActionTile
import com.example.ui.components.StudioGradientProgressBanner
import com.example.ui.components.StudioSubjectGradientCard
import com.example.ui.components.TopRightMenuButton
import com.example.ui.theme.semanticColors
import kotlin.math.roundToInt

private enum class MaterialsSubTab(val label: String) {
  TRACKS("Your tracks"),
  STUDY_SETS("Study sets"),
  EXAM_PREP("Exam prep"),
}

private enum class TrackFilterCategory(val label: String) {
  ALL("All Subjects (7)"),
  STEM("Sciences & Math"),
  HUMANITIES("English & Social"),
}

@Composable
fun HomeDashboardScreen(
  profile: UserProfile?,
  structuredModules: List<CourseModule>,
  progressList: List<LessonProgress>,
  attempts: List<ExamAttempt>,
  notes: List<StudyNote> = emptyList(),
  isDarkTheme: Boolean,
  themePreferences: ThemePreferences = ThemePreferences(),
  placementPassedSubjects: Set<String> = emptySet(),
  todayXp: Long = 0L,
  dueReviewsCount: Int = 0,
  totalReviewCardsCount: Int = 0,
  onToggleDarkTheme: () -> Unit,
  onSetThemeMode: (ThemeMode) -> Unit = {},
  onSetUseDynamicColor: (Boolean) -> Unit = {},
  onSetFontSizeScale: (FontSizeScale) -> Unit = {},
  onOpenLesson: (Lesson) -> Unit,
  onOpenTrack: (SubjectTrack) -> Unit,
  onOpenExamLab: (String) -> Unit,
  onOpenUtmeCombo: () -> Unit = {},
  onOpenPaper2Lab: () -> Unit = {},
  onOpenNotebook: () -> Unit = {},
  onOpenSpacedReview: () -> Unit = {},
  onOpenMenu: () -> Unit = {},
  modifier: Modifier = Modifier,
) {
  val passThreshold = themePreferences.masteryPassPercent.toLong()
  val allLessons =
    remember(structuredModules) {
      val dbLessons = structuredModules.flatMap { it.lessons }
      if (dbLessons.isNotEmpty()) dbLessons else CurriculumCatalog.allLessons()
    }
  val progressMap = remember(progressList) { progressList.associateBy { it.lessonId } }
  val completedIds =
    remember(progressList) { progressList.filter { it.isCompleted }.map { it.lessonId }.toSet() }

  val nextLesson =
    remember(structuredModules, progressMap, allLessons, placementPassedSubjects, passThreshold) {
      val orderedByTracks =
        CurriculumCatalog.tracks.mapNotNull { track ->
          val trackLessons =
            structuredModules.filter { it.subjectId == track.id }.flatMap { it.lessons }.ifEmpty {
              track.allLessons
            }
          val candidate =
            CurriculumProgression.nextUnlockedLesson(
              orderedTrackLessons = trackLessons,
              progressMap = progressMap,
              placementPassedSubjects = placementPassedSubjects,
              passThresholdPercent = passThreshold,
            )
          if (candidate != null && progressMap[candidate.id]?.isCompleted != true) candidate else null
        }
      orderedByTracks.firstOrNull() ?: allLessons.first()
    }
  val nextLessonProgress = progressMap[nextLesson.id]
  val nextLessonPillarsDone = nextLessonProgress?.completedSteps?.distinct()?.size ?: 0
  val nextLessonPercent = ((nextLessonPillarsDone * 100f) / LessonPillar.entries.size.toFloat()).roundToInt()

  val anyConceptDone = progressList.any { LessonPillar.CONCEPT.key in it.completedSteps }
  val anyLabDone = progressList.any { LessonPillar.LAB.key in it.completedSteps }
  val anyExamPassed = attempts.any { it.percentage >= 70L }

  val avgAccuracy =
    if (attempts.isEmpty()) 0 else attempts.map { it.percentage.toInt() }.average().roundToInt()
  val projectedJamb =
    if (attempts.isEmpty()) 260 else (180 + (avgAccuracy * 2.2)).roundToInt().coerceIn(160, 400)
  val recentWeakTopic = attempts.firstOrNull()?.weakTopics?.firstOrNull()
  val recentWeakSubjectId =
    remember(attempts, recentWeakTopic, nextLesson.subjectId) {
      val fromAttempt =
        attempts.firstOrNull { !recentWeakTopic.isNullOrBlank() && recentWeakTopic in it.weakTopics }
          ?.subjectId
          ?.takeIf { it.isNotBlank() && it != "utme_combo" }
      val fromCatalog =
        if (!recentWeakTopic.isNullOrBlank()) {
          CurriculumCatalog.tracks.firstOrNull { track ->
            track.allLessons.any { it.title.contains(recentWeakTopic, ignoreCase = true) }
          }?.id
        } else null
      fromAttempt ?: fromCatalog ?: nextLesson.subjectId
    }
  val semantics = MaterialTheme.semanticColors

  var activeSubTab by remember { mutableStateOf(MaterialsSubTab.TRACKS) }
  var searchQuery by remember { mutableStateOf("") }
  var filterCategory by remember { mutableStateOf(TrackFilterCategory.ALL) }

  val trimmedQuery = searchQuery.trim()
  val matchingLessons =
    remember(trimmedQuery, allLessons) {
      if (trimmedQuery.length < 2) emptyList()
      else {
        allLessons.filter { lesson ->
          lesson.title.contains(trimmedQuery, ignoreCase = true) ||
            lesson.syllabusTag.contains(trimmedQuery, ignoreCase = true) ||
            lesson.concepts.any {
              it.heading.contains(trimmedQuery, ignoreCase = true) ||
                (it.keyFormula?.contains(trimmedQuery, ignoreCase = true) == true)
            }
        }.take(6)
      }
    }
  val matchingNotes =
    remember(trimmedQuery, notes) {
      if (trimmedQuery.length < 2) emptyList()
      else {
        notes.filter { note ->
          note.title.contains(trimmedQuery, ignoreCase = true) ||
            note.content.contains(trimmedQuery, ignoreCase = true)
        }.take(4)
      }
    }

  val filteredTracks =
    remember(trimmedQuery, filterCategory) {
      CurriculumCatalog.tracks.filter { track ->
        val matchesCategory =
          when (filterCategory) {
            TrackFilterCategory.ALL -> true
            TrackFilterCategory.STEM -> track.id in setOf("physics", "chemistry", "mathematics", "biology")
            TrackFilterCategory.HUMANITIES -> track.id in setOf("english", "economics", "government")
          }
        val matchesSearch =
          trimmedQuery.isBlank() ||
            track.title.contains(trimmedQuery, ignoreCase = true) ||
            track.shortName.contains(trimmedQuery, ignoreCase = true) ||
            track.tagline.contains(trimmedQuery, ignoreCase = true) ||
            track.allLessons.any { it.title.contains(trimmedQuery, ignoreCase = true) }
        matchesCategory && matchesSearch
      }
    }
  val trackPairs = remember(filteredTracks) { filteredTracks.chunked(2) }

  Box(
    modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
    contentAlignment = Alignment.TopCenter,
  ) {
    LazyColumn(
      modifier = Modifier.widthIn(max = 900.dp).fillMaxSize(),
      contentPadding = PaddingValues(bottom = 28.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      // Studio Clean Header + Underline Sub-Tabs matching Bottom-Center Mockup
      item {
        Column(
          modifier =
            Modifier.fillMaxWidth()
              .background(MaterialTheme.colorScheme.surface)
              .padding(top = 12.dp),
        ) {
          Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Row(
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Surface(
                color = semantics.softTintSurface,
                shape = RoundedCornerShape(99.dp),
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                  Icon(
                    imageVector = Icons.Filled.LocalFireDepartment,
                    contentDescription = "Streak",
                    tint = semantics.coralAccent,
                    modifier = Modifier.size(16.dp),
                  )
                  Text(
                    text = "${profile?.streakDays ?: 1}d",
                    style = MaterialTheme.typography.labelMedium.copy(fontFeatureSettings = "tnum"),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                  )
                }
              }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                text = "Materials",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
              )
              Text(
                text = "${profile?.displayName?.substringBefore(" ") ?: "Scholar"} • Today ${todayXp}/${themePreferences.dailyXpTarget} XP",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }

            Row(
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              verticalAlignment = Alignment.CenterVertically,
            ) {
              IconButton(
                onClick = onToggleDarkTheme,
                modifier =
                  Modifier.minimumInteractiveComponentSize()
                    .size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(semantics.softTintSurface)
                    .testTag("home_dark_theme_toggle"),
              ) {
                Icon(
                  imageVector = if (isDarkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                  contentDescription = if (isDarkTheme) "Switch to Light Theme" else "Switch to Dark Theme",
                  tint = MaterialTheme.colorScheme.primary,
                  modifier = Modifier.size(20.dp),
                )
              }
              TopRightMenuButton(
                badgeCount = dueReviewsCount,
                onClick = onOpenMenu,
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Underline Tab Strip: "Your tracks" | "Study sets" | "Exam prep"
          Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
          ) {
            MaterialsSubTab.entries.forEach { tab ->
              val selected = activeSubTab == tab
              Column(
                modifier =
                  Modifier.clickable { activeSubTab = tab }
                    .padding(bottom = 2.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                Text(
                  text = tab.label,
                  style = MaterialTheme.typography.titleSmall,
                  color =
                    if (selected) MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                  fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                )
                Box(
                  modifier =
                    Modifier.width(if (selected) 30.dp else 0.dp)
                      .height(3.dp)
                      .clip(CircleShape)
                      .background(
                        if (selected) MaterialTheme.colorScheme.primary
                        else Color.Transparent
                      )
                )
              }
            }
          }
        }
      }

      // Rounded Search Bar + Filter Button matching Mockup
      item {
        Row(
          modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
          horizontalArrangement = Arrangement.spacedBy(10.dp),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Row(
            modifier =
              Modifier.weight(1f)
                .height(48.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(semantics.softTintSurface)
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Icon(
              imageVector = Icons.Filled.Search,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.onSurfaceVariant,
              modifier = Modifier.size(20.dp),
            )
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
              if (searchQuery.isEmpty()) {
                Text(
                  text = "Search subjects, topics, formulas, notes...",
                  style = MaterialTheme.typography.bodyMedium,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
              }
              BasicTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                modifier = Modifier.fillMaxWidth(),
              )
            }
            if (searchQuery.isNotEmpty()) {
              IconButton(
                onClick = { searchQuery = "" },
                modifier = Modifier.size(32.dp),
              ) {
                Icon(
                  imageVector = Icons.Filled.Close,
                  contentDescription = "Clear search",
                  tint = MaterialTheme.colorScheme.onSurfaceVariant,
                  modifier = Modifier.size(16.dp),
                )
              }
            }
          }

          IconButton(
            onClick = {
              val values = TrackFilterCategory.entries
              filterCategory = values[(filterCategory.ordinal + 1) % values.size]
            },
            modifier =
              Modifier.minimumInteractiveComponentSize()
                .size(48.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                  if (filterCategory != TrackFilterCategory.ALL) MaterialTheme.colorScheme.primaryContainer
                  else semantics.softTintSurface
                ),
          ) {
            Icon(
              imageVector = Icons.Filled.Tune,
              contentDescription = "Filter category (${filterCategory.label})",
              tint =
                if (filterCategory != TrackFilterCategory.ALL) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.size(20.dp),
            )
          }
        }
      }

      if (trimmedQuery.length >= 2 && (matchingLessons.isNotEmpty() || matchingNotes.isNotEmpty())) {
        item {
          Column(
            modifier =
              Modifier.padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Text(
              text = "DIRECT SEARCH MATCHES (${matchingLessons.size + matchingNotes.size})",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.Bold,
            )
            matchingLessons.forEach { hit ->
              Row(
                modifier =
                  Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(semantics.softTintSurface)
                    .clickable { onOpenLesson(hit) }
                    .padding(horizontal = 12.dp, vertical = 9.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
              ) {
                Column(modifier = Modifier.weight(1f)) {
                  Text(
                    text = hit.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                  )
                  Text(
                    text = "${hit.subjectId.replaceFirstChar { it.uppercase() }} • ${hit.syllabusTag}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                  )
                }
                Icon(
                  imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                  contentDescription = "Open ${hit.title}",
                  tint = MaterialTheme.colorScheme.primary,
                  modifier = Modifier.size(16.dp),
                )
              }
            }
            matchingNotes.forEach { note ->
              Row(
                modifier =
                  Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.55f))
                    .clickable(onClick = onOpenNotebook)
                    .padding(horizontal = 12.dp, vertical = 9.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
              ) {
                Column(modifier = Modifier.weight(1f)) {
                  Text(
                    text = "Note: ${note.title}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                  )
                  Text(
                    text = note.content,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                  )
                }
                Icon(
                  imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                  contentDescription = "Open Notebook",
                  tint = MaterialTheme.colorScheme.secondary,
                  modifier = Modifier.size(16.dp),
                )
              }
            }
          }
        }
      }

      when (activeSubTab) {
        MaterialsSubTab.TRACKS -> {
          // Resume Current Lesson Gradient Banner
          item {
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
              StudioGradientProgressBanner(
                badgeText = "CONTINUE • ${nextLesson.syllabusTag}",
                title = nextLesson.title,
                subtitle = "$nextLessonPillarsDone/${LessonPillar.entries.size} study stages done • Tap to resume",
                progressPercent = nextLessonPercent.coerceAtLeast(10),
                modifier = Modifier.testTag("resume_lesson_button"),
                onClick = { onOpenLesson(nextLesson) },
              )
            }
          }

          if (filterCategory != TrackFilterCategory.ALL) {
            item {
              Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                Surface(
                  color = MaterialTheme.colorScheme.primaryContainer,
                  shape = RoundedCornerShape(99.dp),
                ) {
                  Text(
                    text = "Showing: ${filterCategory.label}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                  )
                }
              }
            }
          }

          // 2-Column Vibrant Gradient Subject Cards Grid matching Mockup Bottom-Center
          items(trackPairs, key = { pair -> pair.joinToString("_") { it.id } }) { pair ->
            Row(
              modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
              horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
              pair.forEach { track ->
                val dbTrackLessons =
                  structuredModules.filter { it.subjectId == track.id }.flatMap { it.lessons }
                val trackLessons = if (dbTrackLessons.isNotEmpty()) dbTrackLessons else track.allLessons
                val doneCount = trackLessons.count { it.id in completedIds }
                val bankSize = CurriculumCatalog.pastQuestionsFor(track.id, "ALL").size
                val (codeBadge, subtitle, gradient) = subjectGradientStyle(track.id)

                StudioSubjectGradientCard(
                  codeBadge = codeBadge,
                  subtitle = subtitle,
                  title = track.shortName,
                  lessonsCount = trackLessons.size,
                  questionsCount = bankSize,
                  completedLessons = doneCount,
                  gradientColors = gradient,
                  modifier = Modifier.weight(1f).testTag("track_card_${track.id}"),
                  onClick = { onOpenTrack(track) },
                )
              }
              if (pair.size == 1) {
                Spacer(modifier = Modifier.weight(1f))
              }
            }
          }

          // Active Recall Banner (when cards exist)
          if (dueReviewsCount > 0 || totalReviewCardsCount > 0) {
            item {
              Row(
                modifier =
                  Modifier.padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .shadow(3.dp, RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .border(
                      1.dp,
                      if (dueReviewsCount > 0) semantics.coralAccent.copy(alpha = 0.65f)
                      else MaterialTheme.colorScheme.outlineVariant,
                      RoundedCornerShape(20.dp),
                    )
                    .clickable(onClick = onOpenSpacedReview)
                    .padding(16.dp)
                    .testTag("home_spaced_review_banner"),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
              ) {
                Box(
                  modifier =
                    Modifier.size(44.dp)
                      .clip(RoundedCornerShape(14.dp))
                      .background(
                        if (dueReviewsCount > 0) semantics.coralAccent.copy(alpha = 0.14f)
                        else MaterialTheme.colorScheme.primaryContainer
                      ),
                  contentAlignment = Alignment.Center,
                ) {
                  Icon(
                    imageVector = Icons.Filled.Psychology,
                    contentDescription = null,
                    tint = if (dueReviewsCount > 0) semantics.coralAccent else MaterialTheme.colorScheme.primary,
                  )
                }
                Column(modifier = Modifier.weight(1f)) {
                  Text(
                    text =
                      if (dueReviewsCount > 0) "Daily Active Recall: $dueReviewsCount Card(s) Due"
                      else "Recall Deck Caught Up ($totalReviewCardsCount cards)",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                  )
                  Text(
                    text = "SM-2 Day 1/3/7/14/30 flashcards for long-term retention",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                  )
                }
                Icon(
                  imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                  contentDescription = "Open Recall Deck",
                  tint = MaterialTheme.colorScheme.primary,
                )
              }
            }
          }

          // Clean White Daily Learning Plan Card
          item {
            Column(
              modifier =
                Modifier.padding(horizontal = 16.dp)
                  .fillMaxWidth()
                  .shadow(3.dp, RoundedCornerShape(20.dp))
                  .clip(RoundedCornerShape(20.dp))
                  .background(MaterialTheme.colorScheme.surface)
                  .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(20.dp))
                  .padding(16.dp),
              verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
              ) {
                Text(
                  text = "Daily Learning Plan",
                  style = MaterialTheme.typography.titleMedium,
                  color = MaterialTheme.colorScheme.onSurface,
                  fontWeight = FontWeight.Bold,
                )
                val doneTasks = listOf(anyConceptDone, anyLabDone, anyExamPassed).count { it }
                Surface(
                  color = MaterialTheme.colorScheme.primaryContainer,
                  shape = RoundedCornerShape(99.dp),
                ) {
                  Text(
                    text = "$doneTasks / 3 GOALS",
                    style = MaterialTheme.typography.labelSmall.copy(fontFeatureSettings = "tnum"),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                  )
                }
              }

              StudyChecklistItem(
                title = "Build Zero-to-One Foundations",
                subtitle = "Score ${passThreshold}%+ in ${nextLesson.title}",
                isDone = anyConceptDone,
                onClick = { onOpenLesson(nextLesson) },
              )
              StudyChecklistItem(
                title = "Explore 1 Interactive Virtual Lab",
                subtitle = nextLesson.labType.title,
                isDone = anyLabDone,
                onClick = { onOpenLesson(nextLesson) },
              )
              StudyChecklistItem(
                title = "Pass 1 Timed Exam Drill (70%+)",
                subtitle = "JAMB UTME, WAEC WASSCE & NECO SSCE",
                isDone = anyExamPassed,
                onClick = { onOpenExamLab(nextLesson.subjectId) },
              )
            }
          }
        }

        MaterialsSubTab.STUDY_SETS -> {
          item {
            Column(
              modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
              verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
              Text(
                text = "Active Study Sets & Tools",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
              )
              Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                StudioActionTile(
                  label = if (dueReviewsCount > 0) "Recall ($dueReviewsCount)" else "Flashcards",
                  icon = Icons.Filled.Psychology,
                  iconTint = semantics.coralAccent,
                  modifier = Modifier.weight(1f),
                  onClick = onOpenSpacedReview,
                )
                StudioActionTile(
                  label = "Notebook",
                  icon = Icons.AutoMirrored.Filled.MenuBook,
                  iconTint = MaterialTheme.colorScheme.primary,
                  modifier = Modifier.weight(1f),
                  onClick = onOpenNotebook,
                )
                StudioActionTile(
                  label = "Next Lesson",
                  icon = Icons.Filled.PlayArrow,
                  iconTint = semantics.indigoAccent,
                  modifier = Modifier.weight(1f),
                  onClick = { onOpenLesson(nextLesson) },
                )
              }
            }
          }

          items(filteredTracks, key = { "set_${it.id}" }) { track ->
            val lessons = track.allLessons
            val doneCount = lessons.count { it.id in completedIds }
            Row(
              modifier =
                Modifier.padding(horizontal = 16.dp)
                  .fillMaxWidth()
                  .clip(RoundedCornerShape(18.dp))
                  .background(MaterialTheme.colorScheme.surface)
                  .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(18.dp))
                  .clickable { onOpenTrack(track) }
                  .padding(16.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(
                  text = "${track.shortName} • ${track.modules.size} Modules",
                  style = MaterialTheme.typography.titleSmall,
                  color = MaterialTheme.colorScheme.onSurface,
                  fontWeight = FontWeight.Bold,
                )
                Text(
                  text = track.tagline,
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis,
                )
              }
              Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(99.dp),
              ) {
                Text(
                  text = "$doneCount/${lessons.size}",
                  style = MaterialTheme.typography.labelSmall.copy(fontFeatureSettings = "tnum"),
                  color = MaterialTheme.colorScheme.onPrimaryContainer,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                )
              }
            }
          }
        }

        MaterialsSubTab.EXAM_PREP -> {
          item {
            Column(
              modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
              verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
              StudioGradientProgressBanner(
                badgeText = "UTME READINESS PROJECTION",
                title = "$projectedJamb / 400 Score",
                subtitle =
                  if (attempts.isEmpty()) "Take your 1st mock to calibrate projected UTME aggregate"
                  else "$avgAccuracy% accuracy across ${attempts.size} logged mocks",
                progressPercent = ((projectedJamb * 100f) / 400f).roundToInt(),
                onClick = onOpenUtmeCombo,
              )

              Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                StudioActionTile(
                  label = "180-Q UTME",
                  icon = Icons.Filled.School,
                  iconTint = semantics.indigoAccent,
                  modifier = Modifier.weight(1f),
                  onClick = onOpenUtmeCombo,
                )
                StudioActionTile(
                  label = "Subject CBT",
                  icon = Icons.Filled.Quiz,
                  iconTint = MaterialTheme.colorScheme.primary,
                  modifier = Modifier.weight(1f),
                  onClick = { onOpenExamLab(nextLesson.subjectId) },
                )
                StudioActionTile(
                  label = "Paper 2 Lab",
                  icon = Icons.AutoMirrored.Filled.Assignment,
                  iconTint = semantics.coralAccent,
                  modifier = Modifier.weight(1f),
                  onClick = onOpenPaper2Lab,
                )
              }

              if (!recentWeakTopic.isNullOrBlank()) {
                Row(
                  modifier =
                    Modifier.fillMaxWidth()
                      .clip(RoundedCornerShape(18.dp))
                      .background(MaterialTheme.colorScheme.surface)
                      .border(1.dp, semantics.coralAccent.copy(alpha = 0.5f), RoundedCornerShape(18.dp))
                      .clickable { onOpenExamLab(recentWeakSubjectId) }
                      .padding(14.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                  Icon(Icons.Filled.Bolt, contentDescription = null, tint = semantics.coralAccent)
                  Column(modifier = Modifier.weight(1f)) {
                    Text(
                      text = "Recommended Weak-Topic Drill",
                      style = MaterialTheme.typography.titleSmall,
                      color = MaterialTheme.colorScheme.onSurface,
                      fontWeight = FontWeight.Bold,
                    )
                    Text(
                      text = "Target recent weak topic: $recentWeakTopic",
                      style = MaterialTheme.typography.bodySmall,
                      color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                  }
                  Icon(
                    Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
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

@Composable
private fun subjectGradientStyle(subjectId: String): Triple<String, String, List<Color>> {
  val semantics = MaterialTheme.semanticColors
  val primary = MaterialTheme.colorScheme.primary
  val secondary = MaterialTheme.colorScheme.secondary
  val tertiary = MaterialTheme.colorScheme.tertiary
  return when (subjectId) {
    "physics" -> Triple("PHY • SS1–SS3", "JAMB • WAEC", listOf(semantics.heroGradientStart, primary))
    "chemistry" -> Triple("CHM • SS1–SS3", "JAMB • WAEC", listOf(semantics.classroomBar, semantics.indigoAccent))
    "mathematics" -> Triple("MTH • CORE", "UTME • WASSCE", listOf(semantics.periwinkleAccent, semantics.heroGradientMid))
    "english" -> Triple("ENG • COMPULSORY", "60 UTME Qs", listOf(semantics.coralAccent, tertiary))
    "biology" -> Triple("BIO • SS1–SS3", "Life Sciences", listOf(semantics.success, secondary))
    "economics" -> Triple("ECO • SS1–SS3", "Micro & Macro", listOf(primary, semantics.indigoAccent))
    else -> Triple("GOV • SS1–SS3", "Civic & Polity", listOf(semantics.heroGradientMid, semantics.classroomBar))
  }
}

@Composable
private fun StudyChecklistItem(
  title: String,
  subtitle: String,
  isDone: Boolean,
  onClick: () -> Unit,
) {
  val semantics = MaterialTheme.semanticColors
  Row(
    modifier =
      Modifier.fillMaxWidth()
        .clip(RoundedCornerShape(14.dp))
        .background(
          if (isDone) semantics.successContainer
          else semantics.softTintSurface
        )
        .clickable(onClick = onClick)
        .padding(horizontal = 12.dp, vertical = 10.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    Icon(
      imageVector = if (isDone) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
      contentDescription = if (isDone) "Completed" else "Pending",
      tint = if (isDone) semantics.success else MaterialTheme.colorScheme.primary,
      modifier = Modifier.size(20.dp),
    )
    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = if (isDone) semantics.onSuccessContainer else MaterialTheme.colorScheme.onSurface,
      )
      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodySmall,
        color = if (isDone) semantics.onSuccessContainer else MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )
    }
    Icon(
      imageVector = Icons.AutoMirrored.Filled.ArrowForward,
      contentDescription = "Open",
      tint = if (isDone) semantics.onSuccessContainer else MaterialTheme.colorScheme.primary,
      modifier = Modifier.size(16.dp),
    )
  }
}
