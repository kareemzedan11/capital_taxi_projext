package com.example.capital_taxi.Presentation.ui.Passengar.Screens.TripHistory.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components.RideHistoryList

@Composable
fun HistoryContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        RideHistoryList(token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY3YWIxNWE3ZWEzMjJhMzg0OTIzODMxOSIsInJvbGUiOiJ1c2VyIiwiaWF0IjoxNzM5MzE0MzYyLCJleHAiOjE3Mzk0MDA3NjJ9.vh-jxlfJ2YlEaUD2XgPln2zHg0AJcRAUrwCpSTnCCvw")
    }
}