package com.example.capital_taxi.Presentation.ui.shared.Onboarding.Components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination

import com.example.capital_taxi.R

@Composable
fun SelectTheMode2(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val boxHeight = screenHeight * 0.4f // Adjust box height relative to screen size

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image with overlay
        Image(
            painter = painterResource(R.drawable.stop),
            contentDescription = null,

            modifier = Modifier.fillMaxWidth().fillMaxHeight(.7f)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.7f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            // Welcome Text
            Column(
                modifier = Modifier.padding(
                    top = 40.dp,
                    start = 20.dp
                ), // Adjust for better positioning
                verticalArrangement = Arrangement.spacedBy(8.dp) // Space between text lines
            ) {

            }
            // Curved White Card for Language Selection
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(boxHeight) // Dynamically adjust the box height
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center, // Center the content
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Select Mode",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ModeOption("Driver Mode",
                        Color(0xFF2196F3).copy(alpha = 0.3f),
                        onClick = {navController.navigate(Destination.driverLogin.route)}
                    ) // Light Blue background
                    Spacer(modifier = Modifier.height(8.dp))
                    ModeOption(
                        "Passenger Mode",

                        Color(0xFF4CAF50).copy(alpha = 0.3f),
                        onClick = {navController.navigate(Destination.UserLogin.route  )}

                    ) // Light Green background
                }
            }
        }
    }
}

@Composable
fun ModeOption(language: String, backgroundColor: Color, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
            .background(
                backgroundColor,
                RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
          ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = language, fontWeight = FontWeight.Medium, fontSize = 16.sp)
    }
}
