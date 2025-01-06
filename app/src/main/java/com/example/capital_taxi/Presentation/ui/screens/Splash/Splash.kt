package com.example.capital_taxi.Navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import com.example.capital_taxi.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    var progress by remember { mutableStateOf(0f) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    // Animate logo size with scale based on progress
    val logoScale by animateFloatAsState(
        targetValue = if (progress < 1f) 1f else 0.8f,
        animationSpec = tween(durationMillis = 500)
    )

    // Animate logo visibility (fade in gradually)
    val logoAlpha by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 100)
    )

    LaunchedEffect(Unit) {
        while (progress < 1f) {
            delay(20)
            progress += 0.005f
        }

        navController.navigate(Destination.StartScreen.route) {
            popUpTo(Destination.SplashScreen.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        colorResource(R.color.primary_color).copy(alpha = 0.7f),
                        colorResource(R.color.primary_color).copy(alpha = 0.3f),
                        colorResource(R.color.primary_color).copy(alpha = 0.7f),
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))


            Icon(
                modifier = Modifier
                    .size(320.dp)
                    .graphicsLayer(
                        scaleX = logoScale, // Apply scaling animation on X-axis
                        scaleY = logoScale, // Apply scaling animation on Y-axis
                        alpha = logoAlpha // Apply fade-in animation
                    ),
                painter = painterResource(R.drawable.logo), contentDescription = "Logo"
            )

            Spacer(modifier = Modifier.weight(1f))

            // Progress Bar Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp)
                    .height(10.dp),
                elevation = CardDefaults.elevatedCardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.White
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .background(Color.White)
                ) {
                    // Progress bar
                    LinearProgressIndicator(
                        progress = progress,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp),
                        color = androidx.compose.ui.graphics.lerp(
                            start = colorResource(R.color.primary_color),
                            stop = colorResource(R.color.primary_color),
                            fraction = progress
                        )
                    )

                    // Car Icon
                    Icon(
                        painter = painterResource(R.drawable.splashcar),
                        contentDescription = "Car Icon",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(80.dp)
                            .offset(x = (progress * (screenWidth - 128.dp)) - 32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
