package com.example.capital_taxi.Navigation

sealed class Destination(val route: String) {
    object SplashScreen : Destination("splashScreen")
    object StartScreen : Destination("startScreen")
    object OnboardingPager : Destination("OnboardingPager")

}
