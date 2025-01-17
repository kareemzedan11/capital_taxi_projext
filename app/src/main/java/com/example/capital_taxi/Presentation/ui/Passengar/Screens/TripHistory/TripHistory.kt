package com.example.capital_taxi.Presentation.ui.Passengar.Screens.TripHistory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.capital_taxi.Presentation.ui.Passengar.Components.RideHistoryList
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.TripHistory.Components.Header
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.TripHistory.Components.HistoryContent
import com.example.capital_taxi.R

@Composable
fun TripsHistoryScreen(navController: NavController) {
    TripsHistoryScreenContent(navController = navController)
}

@Composable
fun TripsHistoryScreenContent(navController: NavController) {
    val context = LocalContext.current
    val backgroundColor = Color(ContextCompat.getColor(context, R.color.primary_color))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Header(navController = navController, backgroundColor = backgroundColor)
        Spacer(modifier = Modifier.height(40.dp))
        HistoryContent()
    }
}




