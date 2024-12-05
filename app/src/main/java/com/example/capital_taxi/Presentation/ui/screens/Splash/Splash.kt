package com.example.capital_taxi.Navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.example.capital_taxi.R

@Composable
fun SplashScreen(navController: NavHostController) {
    val mainComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.logo))
    val overlayComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.marker))

    val mainProgress by animateLottieCompositionAsState(
        composition = mainComposition,
        iterations = 1
        , speed = .8f
    )

    val overlayProgress by animateLottieCompositionAsState(
        composition = overlayComposition,
        iterations = LottieConstants.IterateForever // Repeat forever if needed
    )

    // Get screen dimensions
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Main Lottie Animation (background or base)
        LottieAnimation(
            composition = mainComposition,
            progress = { mainProgress },
            modifier = Modifier
                .size(screenWidth * 1.4f),
            contentScale = ContentScale.Crop
        )

        // Overlay Lottie Animation (foreground)
        LottieAnimation(
            composition = overlayComposition,
            progress = { overlayProgress },
            modifier = Modifier
                .size(screenWidth * 0.45f), // Smaller overlay
            contentScale = ContentScale.Crop
        )
    }

    // Navigate to the next screen when the main animation finishes
    LaunchedEffect(mainProgress) {
        if (mainProgress == 1f) {
            navController.navigate(Destination.StartScreen.route) {
                popUpTo("splash_screen_route") { inclusive = true }
            }
        }
    }
}
