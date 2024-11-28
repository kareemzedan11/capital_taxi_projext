package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Onboarding.Components

import androidx.compose.runtime.Composable
import com.example.capital_taxi.R


@Composable
fun OnboardingScreen(page: Int) {
    when (page) {
        0 -> OnboardingContent(
            imageRes = R.drawable.onboardinglogo3,
            title = "Fast and Reliable Service",
            description = "Get to your destination quickly with professional drivers."
        )

        1 -> OnboardingContent(
            imageRes = R.drawable.onboardinglogo2,
            title = "Seamless Booking Experience",
            description = "Book a ride in just a few taps "
        )

        2 -> OnboardingContent(
            imageRes = R.drawable.onboardinglogo1,
            title = "Affordable Rides for Everyone",
            description = "Enjoy competitive pricing without compromising on quality "
        )
    }
}
