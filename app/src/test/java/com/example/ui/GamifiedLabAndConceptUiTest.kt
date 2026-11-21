package com.example.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import com.example.data.curriculum.CurriculumCatalog
import com.example.data.curriculum.PracticalLabType
import com.example.data.curriculum.VerifiedResourceCatalog
import com.example.ui.components.ConceptSectionAnimationCard
import com.example.ui.labs.GamifiedVirtualLab
import com.example.ui.labs.PracticalLabWorkspace
import com.example.ui.theme.MeritPrepTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class GamifiedLabAndConceptUiTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun `practical lab switches between bench, gamified challenge, and verified external portals`() {
    val sampleLesson = CurriculumCatalog.tracks.first().allLessons.first()
    val resources = VerifiedResourceCatalog.forLesson(sampleLesson)

    composeTestRule.setContent {
      MeritPrepTheme(darkTheme = false, dynamicColor = false) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
          PracticalLabWorkspace(
            lessonId = sampleLesson.id,
            labType = PracticalLabType.PROJECTILE_KINEMATICS,
            labBrief = sampleLesson.labBrief,
            externalResources = resources,
          )
        }
      }
    }

    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithTag("lab_mode_bench").assertIsDisplayed()
    composeTestRule.onNodeWithTag("lab_mode_gamified").assertIsDisplayed().performClick()
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithTag("gamified_lab_hint_toggle").assertIsDisplayed()

    composeTestRule.onNodeWithTag("lab_mode_external").assertIsDisplayed().performClick()
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithTag("external_resource_open_0").assertIsDisplayed()
  }

  @Test
  fun `gamified lab submits trial and reveals next round progression`() {
    composeTestRule.setContent {
      MeritPrepTheme(darkTheme = false, dynamicColor = false) {
        Column(
          modifier =
            Modifier.testTag("gamified_scroll")
              .verticalScroll(rememberScrollState()),
        ) {
          GamifiedVirtualLab(
            lessonId = "phys_04_projectiles",
            labType = PracticalLabType.PROJECTILE_KINEMATICS,
          )
        }
      }
    }

    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithTag("gamified_scroll").performTouchInput { swipeUp() }
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithTag("gamified_lab_submit").assertIsDisplayed().performClick()
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithTag("gamified_scroll").performTouchInput { swipeUp() }
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithTag("gamified_lab_next").assertIsDisplayed()
  }

  @Test
  fun `concept section animation card renders without crash`() {
    composeTestRule.setContent {
      MeritPrepTheme(darkTheme = false, dynamicColor = false) {
        ConceptSectionAnimationCard(
          lessonId = "phys_01_units",
          sectionIndex = 0,
          heading = "Micrometer Screw Gauge & Vernier Caliper",
          labType = PracticalLabType.MEASUREMENT_VECTOR_LAB,
        )
      }
    }
    composeTestRule.waitForIdle()
  }
}
