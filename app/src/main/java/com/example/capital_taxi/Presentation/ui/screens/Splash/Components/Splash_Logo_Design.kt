package com.example.capital_taxi.Presentation.ui.screens.Splash.Components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashAnimation() {
    val text = "Capital Taxi"
    val scales = remember { text.map { Animatable(0f) } } // Letters start collapsed vertically

    LaunchedEffect(Unit) {
        for (index in text.indices) {
            scales[index].animateTo(
                targetValue = 1f, // Expand vertically
                animationSpec = tween(durationMillis = 300)
            )
            delay(150) // Delay before animating the next letter
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            text.forEachIndexed { index, char ->
                Text(
                    text = char.toString(),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .graphicsLayer(
                            scaleY = scales[index].value, // Scale vertically
                            scaleX = 1f // Keep horizontal scale normal
                        )
                        .size(width = 30.dp, height = 50.dp) // Size of the letter
                )
            }
        }
    }
}
