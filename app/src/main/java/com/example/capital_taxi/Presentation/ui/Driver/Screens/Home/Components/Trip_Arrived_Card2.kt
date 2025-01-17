package com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R

@Composable
fun TripArrivedCard2() {
    var rating by remember { mutableStateOf(0f) } // Store the rating value
    var comment by remember { mutableStateOf("") } // Store the comment
    var showDialog by remember { mutableStateOf(false) } // Show dialog after submission

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            // Header Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Trip Arrived",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color =  colorResource(R.color.primary_color)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Destination: ",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Abbas El-Akkad Strt",
                        fontSize = 14.sp
                    )
                }

                Text(
                    text = "Arrived",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color.Gray, thickness = 1.dp)

            Spacer(modifier = Modifier.height(8.dp))

            // Fare and Distance Section (optional)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Distance:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "5.2 km", // Optional
                        fontSize = 14.sp
                    )
                }
                Column {
                    Text(
                        text = "Fare:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "30.00 EGP",
                        fontSize = 14.sp,
                        color = Color(0xFF4CAF50)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Rating Section - Driver Rating User
            Text(
                text = "Rate the User:",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))

            // Rating Bar (Stars)
            RatingBar(rating = rating, onRatingChanged = { newRating -> rating = newRating })

            Spacer(modifier = Modifier.height(16.dp))

            // Comment Section
            Text(
                text = "Leave a comment (optional):",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))

            TextField(
                value = comment,
                onValueChange = { comment = it },
                label = { Text("Your comment...") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Submit Button (Gray until rating and/or comment provided)
            Button(
                onClick = {
                    showDialog = true // Show dialog after submission
                },
                enabled = rating > 0f || comment.isNotEmpty(), // Button enabled if rating or comment provided
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (rating > 0f || comment.isNotEmpty())  colorResource(R.color.primary_color) else Color.Gray
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Submit Rating", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Go to Home Button
            Button(
                onClick = { /* Handle Go to Home action */ },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary_color)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Go to Home", color = Color.Black)
            }
        }
    }

    // Show Dialog when rating is submitted
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Rating Submitted") },
            text = { Text("Your rating and comment have been submitted.") },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}



