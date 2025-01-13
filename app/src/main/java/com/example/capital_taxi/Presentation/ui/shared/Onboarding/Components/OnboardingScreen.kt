package com.example.capital_taxi.Presentation.ui.shared.Onboarding.Components

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

// val pagerState = rememberPagerState()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 16.dp),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        // Pager Content
//        Box(
//            modifier = Modifier
//                .weight(6f)
//                .fillMaxWidth()
//                .background(Color.White)
//        ) {
//            Column {
//                HorizontalPager(
//                    count = 3,
//                    state = pagerState,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .weight(1f)
//                ) { page ->
//                    OnboardingScreen(page = page)
//                }
//
//                HorizontalPagerIndicator(
//                    pagerState = pagerState,
//                    modifier = Modifier
//                        .align(Alignment.CenterHorizontally)
//                        .padding(vertical = 8.dp),
//                    activeColor = Color.Blue,
//                    inactiveColor = Color.Gray
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(24.dp)) // Space between pager and buttons
//
//        // Google and Phone Sign-In Buttons
//        GoogleAndPhone(
//            onSignInClick = onSignInClick,
//            navController = navController,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        )
//
//        Spacer(modifier = Modifier.height(32.dp)) // Ensure space at the bottom of the screen
//    }