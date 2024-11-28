package com.example.capital_taxi.Navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHost
import androidx.navigation.NavHostController


import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Onboarding.Components.OnboardingScreen
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Onboarding.OnboardingPager

import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Start.StartScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destination.SplashScreen.route) {

        composable(Destination.SplashScreen.route) { SplashScreen(navController) }
        composable(Destination.StartScreen.route) { StartScreen(navController) }
        composable(Destination.OnboardingPager.route) { OnboardingPager(navController) }

    }
}
