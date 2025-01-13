package com.example.capital_taxi.Presentation.ui.Passengar.Screens.TripHistory


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.capital_taxi.Presentation.ui.Passengar.Components.RideHistoryList
import com.example.capital_taxi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripsHistoryScreen(navController: NavController) {
    Trips_History_Screen_Content(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Trips_History_Screen_Content(modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current // Access the context safely
    val backgroundColor = Color(ContextCompat.getColor(context, R.color.primary_color))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Yellow Header Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(backgroundColor),
        ) {
            // Icon aligned to the start
            Icon(
                modifier = Modifier
                    .padding(start = 16.dp, top = 60.dp) // Adjust padding from the start and top
                    .size(40.dp)
                    .align(Alignment.TopStart) // Align icon to the top-start of the Box
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { navController.popBackStack() },
                imageVector = Icons.Default.Close,
                contentDescription = "close", // Use a string resource for content description
                tint = Color.Black
            )

            // Text centered in the Box
            Text(
                text = stringResource(id = R.string.Trip_History), // Use the localized string
                modifier = Modifier
                    .align(Alignment.Center) // Align text in the center of the Box
                    .padding(top = 60.dp), // Add padding from the top
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center // Align content in the center of the Box
        ) {
            RideHistoryList()
        }
    }
}
