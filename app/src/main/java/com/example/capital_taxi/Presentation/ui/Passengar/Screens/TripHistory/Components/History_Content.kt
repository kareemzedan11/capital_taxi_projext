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
import com.example.capital_taxi.Presentation.ui.Passengar.Components.RideHistoryList

@Composable
fun HistoryContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        RideHistoryList()
    }
}