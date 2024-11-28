package com.example.capital_taxi.Navigation


import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.animation.core.Animatable
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Splash.Components.SplashLogoDesign

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000)
        )
        delay(3000)
        navController.navigate(Destination.StartScreen.route) {
            popUpTo(Destination.SplashScreen.route) { inclusive = true }
        }
    }

    SplashLogoDesign(scale)
}

