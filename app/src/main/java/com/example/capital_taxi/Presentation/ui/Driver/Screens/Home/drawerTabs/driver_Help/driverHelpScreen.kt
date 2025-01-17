package com.example.capital_taxi.Presentation.ui.Driver.Screens.HelpScreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.res.stringResource
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.capital_taxi.Presentation.Common.Search_Button
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.Help_Screen_Header

import com.example.capital_taxi.Presentation.ui.Passengar.Screens.HelpScreen.Components.HelpTopicItem
import com.example.capital_taxi.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverHelpScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Text(
                        text = stringResource(R.string.Help),

                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color.Transparent)
                                .border(4.dp, color = Color.Black, RoundedCornerShape(30.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(26.dp),
                                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                                contentDescription = stringResource(R.string.back),
                                tint = Color.Black
                            )
                        }
                    }
                },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .background(Color(0xFFF5F5F5))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Help_Screen_Header()

                // Add the search button here
                Box(modifier = Modifier     .align(Alignment.Start)) {
                    Search_Button()
                }
                val helpTopics = listOf(
                    stringResource(R.string.Trips),
                    stringResource(R.string.Payment_and_Billing),
                    stringResource(R.string.Ride_Safety),
                    stringResource(R.string.Account),
                    stringResource(R.string.Rate_and_Feedback)
                )

                helpTopics.forEach { topic ->
                    HelpTopicItem(
                        topic = topic,
                        onClick = {
                            navController.navigate("driver_help_detail/${Uri.encode(topic)}")
                        }
                    )
                }
            }
        }
    )
}

