package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R

@Composable
fun StarRating(rating: Float, onRatingSelected: (Float) -> Unit) {
    Row(horizontalArrangement = Arrangement.Center) {
        for (i in 1..5) {
            // Calculate the star state: full, half, or empty
            val isFullStar = rating >= i
            val isHalfStar = rating in (i - 0.5)..i.toDouble()

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        val newRating = if (rating >= i) i - 0.5f else i.toFloat()
                        onRatingSelected(newRating)
                    }
            ) {
                if (isFullStar) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_star_rate_24), // Full star icon
                        contentDescription = "Full Star",
                        tint = Color(0XFFFDCD10),
                        modifier = Modifier.fillMaxSize()
                    )
                } else if (isHalfStar) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_star_half_24), // Half star icon
                        contentDescription = "Half Star",
                        tint = Color(0XFFFDCD10),
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.baseline_star_24), // Empty star icon
                        contentDescription = "Empty Star",
                        tint = Color.Gray,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun TripRatingDialog() {
    var selectedRating by remember { mutableStateOf(0f) }
    var comments by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "How is your trip?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(3.dp))

        Text(
            text = "Please rate your driver",
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))

        // Star Rating with half-star functionality
        StarRating(rating = selectedRating) { newRating ->
            selectedRating = newRating
        }

        Spacer(modifier = Modifier.padding(30.dp))



        TextField(
            value = comments,
            onValueChange = {comments=it},
            placeholder = { Text("Additional comments...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.padding(25.dp))

        Button(
            onClick = { /* Handle rating confirmation */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF46C96B)),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Confirm", color = Color.White, fontSize = 16.sp)
        }
    }
}
