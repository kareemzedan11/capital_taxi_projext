package com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components

import androidx.compose.runtime.Composable
import TopBar
import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R
import drawerContent
import kotlinx.coroutines.delay

@Composable
fun TripDetailsCard(light: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            // Blinking Light Animation
            var blinkState by remember { mutableStateOf(true) }
            LaunchedEffect(Unit) {
                while (true) {
                    blinkState = !blinkState
                    delay(700L)
                }
            }

            if (light) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(.7f)
                        .fillMaxWidth()
                        .background(
                            color = if (blinkState) colorResource(R.color.secondary_color) else Color.Transparent,
                            shape = RoundedCornerShape(16.dp)
                        )
                )
            }

            // Main Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Trip Type and Price
                Text(
                    text = "Comfort",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray
                )
                Text(
                    text = "129.90 EGP",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Comprehensive Graphical Service",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                // Progress Bar
                var progress by remember { mutableStateOf(1f) }
                LaunchedEffect(Unit) {
                    val totalDuration = 30000L
                    val frameDuration = 16L
                    while (progress > 0f) {
                        progress -= frameDuration.toFloat() / totalDuration
                        delay(frameDuration)
                    }
                    progress = 0f
                }
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = colorResource(R.color.primary_color),
                    trackColor = Color(0XFFF2F2F2)
                )

                // Rating
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(26.dp),
                        painter = painterResource(R.drawable.person),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "4.5",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }

                // Ride Details
                RidePointDetails(
                    Locationicon = R.drawable.circle,
                    Destinationicon = R.drawable.travel,
                    LocationText = "Cairo",
                    DestinationText = "Alex",
                    distance1 = "3m (1.2KM)",
                    distance2 = "45m (20KM)",
                    isDestance = true,
                    onClick = { }
                )

                // Accept Trip Button
                Button(
                    onClick = { /* Handle Accept Trip */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_color)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Accept Trip", color = Color.White, fontSize = 16.sp)
                }

                // Cancel Trip Button
                Button(
                    onClick = { /* Handle Cancel Trip */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    border = BorderStroke(1.dp, colorResource(R.color.primary_color)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Cancel Trip", color = colorResource(R.color.primary_color), fontSize = 16.sp)
                }

                // Divider
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                )

                // Scrollable Trip Options
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    repeat(7) {
                        Button(
                            onClick = { /* Handle Trip Option */ },
                            modifier = Modifier
                                .padding(horizontal = 5.dp),
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                            border = BorderStroke(1.dp, colorResource(R.color.primary_color)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "150 EGP",
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RidePointDetails(
    distance1: String? = null,
    distance2: String? = null,
    isDestance: Boolean,
    Locationicon: Int,
    Destinationicon: Int,
    onClick: () -> Unit,
    LocationText: String,
    DestinationText: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Location
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(26.dp),
                painter = painterResource(Locationicon),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                distance1?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Text(
                    text = LocationText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.Black
                )
            }
        }

        // Destination
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(26.dp),
                painter = painterResource(Destinationicon),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                distance2?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Text(
                    text = DestinationText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.Black
                )
            }
        }
    }
}