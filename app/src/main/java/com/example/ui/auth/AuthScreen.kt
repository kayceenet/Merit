package com.example.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import com.example.R
import com.example.ui.theme.DeepNavyBanner

@Composable
fun AuthScreen(
  onAuthSuccess: () -> Unit,
) {
  val context = LocalContext.current
  val coroutineScope = rememberCoroutineScope()
  val credentialManager = remember { CredentialManager.create(context) }
  var isLoading by remember { mutableStateOf(false) }
  var errorMessage by remember { mutableStateOf<String?>(null) }

  LaunchedEffect(Unit) {
    attemptAutoSignIn(
      context = context,
      credentialManager = credentialManager,
      onAuthSuccess = onAuthSuccess,
      onUnauthenticated = {},
      scope = coroutineScope,
    )
  }

  Box(
    modifier =
      Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .statusBarsPadding()
        .navigationBarsPadding(),
    contentAlignment = Alignment.Center,
  ) {
    Column(
      modifier =
        Modifier.widthIn(max = 560.dp)
          .fillMaxSize()
          .verticalScroll(rememberScrollState())
          .padding(horizontal = 20.dp, vertical = 20.dp),
      verticalArrangement = Arrangement.SpaceBetween,
    ) {
      Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
        // Brand Badge Row
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
          Box(
            modifier =
              Modifier.size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center,
          ) {
            Icon(
              imageVector = Icons.Filled.School,
              contentDescription = "MeritPrep emblem",
              tint = MaterialTheme.colorScheme.onPrimary,
              modifier = Modifier.size(22.dp),
            )
          }
          Column {
            Text(
              text = "MeritPrep",
              style = MaterialTheme.typography.titleLarge,
              color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
              text = "UTME & SSCE Academic Studio",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.secondary,
            )
          }
        }

        // Editorial Hero Card
        Box(
          modifier =
            Modifier.fillMaxWidth()
              .height(210.dp)
              .clip(RoundedCornerShape(24.dp))
              .background(DeepNavyBanner),
        ) {
          Image(
            painter = painterResource(id = R.drawable.img_hero_academy_1794740698817),
            contentDescription = "MeritPrep scientific laboratory academy illustration",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
          )
          Box(
            modifier =
              Modifier.fillMaxSize()
                .background(
                  Brush.horizontalGradient(
                    colors = listOf(Color(0xE6081533), Color(0x88081533), Color(0x22081533))
                  )
                )
          )
          Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Bottom,
          ) {
            Surface(
              color = MaterialTheme.colorScheme.tertiary,
              shape = RoundedCornerShape(99.dp),
            ) {
              Text(
                text = "JAMB 340+ • WAEC A1 SYSTEM",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
              text = "Master every syllabus concept with interactive labs.",
              style = MaterialTheme.typography.headlineMedium,
              color = Color.White,
            )
          }
        }

        // Four Pillars Preview
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          AuthFeatureRow(
            icon = Icons.Filled.CheckCircle,
            title = "5-Pillar Structured Curriculum",
            subtitle = "Concept derivations, worked past-paper steps, and examiner traps.",
          )
          AuthFeatureRow(
            icon = Icons.Filled.Science,
            title = "Live Practical Demonstrations",
            subtitle = "Interactive Projectile, Circuit, Titration, Calculus & Genetics labs.",
          )
          AuthFeatureRow(
            icon = Icons.Filled.Quiz,
            title = "Authentic JAMB CBT & WAEC Studio",
            subtitle = "Timed CBT navigator grid + WAEC M1/A1 marking scheme guides.",
          )
          AuthFeatureRow(
            icon = Icons.Filled.AutoGraph,
            title = "Cloud-Synced Mastery & Notes",
            subtitle = "Your scores, weak-topic diagnostics, and revision cards stay saved.",
          )
        }
      }

      Column(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
      ) {
        if (errorMessage != null) {
          Surface(
            color = MaterialTheme.colorScheme.errorContainer,
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxWidth(),
          ) {
            Text(
              text = errorMessage!!,
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onErrorContainer,
              modifier = Modifier.padding(12.dp),
            )
          }
        }

        Button(
          onClick = {
            errorMessage = null
            isLoading = true
            onGoogleSignInClicked(
              context = context,
              credentialManager = credentialManager,
              onAuthSuccess = {
                isLoading = false
                onAuthSuccess()
              },
              onAuthError = { msg ->
                isLoading = false
                errorMessage = msg
              },
              scope = coroutineScope,
              onAuthCancelled = { isLoading = false },
            )
          },
          enabled = !isLoading,
          modifier =
            Modifier.fillMaxWidth()
              .height(56.dp)
              .testTag("google_sign_in_button"),
          shape = RoundedCornerShape(16.dp),
          colors =
            ButtonDefaults.buttonColors(
              containerColor = MaterialTheme.colorScheme.primary,
              contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
        ) {
          if (isLoading) {
            CircularProgressIndicator(
              modifier = Modifier.size(22.dp),
              color = MaterialTheme.colorScheme.onPrimary,
              strokeWidth = 2.5.dp,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text("Signing in...", style = MaterialTheme.typography.titleSmall)
          } else {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.Login,
              contentDescription = null,
              modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text("Continue with Google", style = MaterialTheme.typography.titleMedium)
          }
        }

        Text(
          text = "Your study streaks, CBT attempts, and formula notebook sync privately to your account.",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontWeight = FontWeight.Normal,
        )
      }
    }
  }
}

@Composable
private fun AuthFeatureRow(
  icon: ImageVector,
  title: String,
  subtitle: String,
) {
  Row(
    modifier =
      Modifier.fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.surface)
        .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
        .padding(14.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(12.dp),
  ) {
    Box(
      modifier =
        Modifier.size(38.dp)
          .clip(CircleShape)
          .background(MaterialTheme.colorScheme.primaryContainer),
      contentAlignment = Alignment.Center,
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier.size(20.dp),
      )
    }
    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onSurface,
      )
      Text(
        text = subtitle,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }
  }
}
