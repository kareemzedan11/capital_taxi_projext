package com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.drawerTabs.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.capital_taxi.R

@Composable
fun notification(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Notification",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = { navController.popBackStack() }) {
                        Box(
                            modifier = Modifier

                                .size(36.dp)
                                .background(Color.Transparent)
                                .border(4.dp, color = Color.Black, RoundedCornerShape(30.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            androidx.compose.material3.Icon(
                                modifier = Modifier.size(26.dp),
                                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                                contentDescription = "Back",
                                tint = Color.Black
                            )
                        }
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .background(Color.White),

            ) {
                // Lottie Animation
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.notification))
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .background(Color.LightGray)
                ) {
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .background(Color.White)
                ) {
                    Column (
                        modifier = Modifier.fillMaxWidth()
                            .height(200.dp)
                            .padding(vertical = 16.dp),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally) {




                    Text(
                        "No Notification Available Now",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }}
            }})}

