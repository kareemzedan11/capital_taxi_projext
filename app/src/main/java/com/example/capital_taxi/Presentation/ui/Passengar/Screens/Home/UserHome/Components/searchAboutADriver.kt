package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.capital_taxi.R
import kotlinx.coroutines.delay


@Composable
fun searchAboutADriver() {
    Column  {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.06f)
                .background(Color(0XFFF2F2F2)),
            contentAlignment = Alignment.Center
        ) {
            driverViewing()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)


                .background(Color.White)
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    )
                    .background(Color.White)
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .padding(top = 10.dp),
                        thickness = 6.dp,
                        color = Color(0XFFF2F2F2)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "Ride requested. Please wait for the driver.",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(4.dp))


                    // Linear progress indicator with 60-second animation
                    var progress by remember { mutableStateOf(0f) }
                    LaunchedEffect(Unit) {
                        val totalDuration = 60000L // 60 seconds in milliseconds
                        val frameDuration = 16L // Roughly 60 FPS
                        while (progress < 1f) {
                            progress += frameDuration.toFloat() / totalDuration
                            delay(frameDuration)
                        }
                        progress = 1f
                    }

                    LinearProgressIndicator(
                        progress = progress,
                        modifier = Modifier
                            .fillMaxWidth()
                        ,
                        color = colorResource(R.color.primary_color), // Customize color if needed
                        Color(0XFFF2F2F2)
                    )

                    Spacer(modifier = Modifier.padding(top = 10.dp))

                    Box(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Drop off time : 3:45am",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W600
                        )
                    }

                    val composition by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(R.raw.loadinganimation)
                    )
                    val progress2 by animateLottieCompositionAsState(
                        composition = composition,
                        iterations = LottieConstants.IterateForever
                    )
                    Box(modifier = Modifier.fillMaxWidth(.4f)) {
                        LottieAnimation(
                            composition = composition,
                            progress = progress2,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}