package com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R

@Composable
fun RideInfoCard() {
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
                        text = "From :",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Lotfy lapip strt",
                        fontSize = 14.sp
                    )
                }

                Text(
                    text = "1 sec ago",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Destination Section
            Text(
                text = "To :",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = "Abbas El-Akkad Strt",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color.Gray, thickness = 1.dp)

            Spacer(modifier = Modifier.height(8.dp))

            // Price Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Price :",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "30.00 EGP",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = colorResource(R.color.primary_color)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    IconButton(
                        onClick = { /* Handle Chat Action */ }, modifier = Modifier
                            .clip(
                                CircleShape
                            )
                            .background(Color.Blue)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_chat_24),
                            contentDescription = "Chat Icon",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))

                    IconButton(
                        onClick = { /* Handle Call Action */ },
                        modifier = Modifier
                            .clip(
                                CircleShape
                            )
                            .background(colorResource(R.color.primary_color))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_call_24),
                            contentDescription = "Call Icon",
                            tint = Color.White
                        )
                    }
                }

                Row {
                    Button(
                        onClick = { /* Handle I am there action */ },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary_color)),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(text = "I am there", color = Color.Black)
                    }

                    Button(
                        onClick = { /* Handle Cancel action */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
                    ) {
                        Text(text = "Cancel ride", color = Color.White)
                    }
                }
            }
        }
    }
}
@Composable
fun DriverArrivedCard() {
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
                        text = "Arrived At:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Abbas El-Akkad Strt",
                        fontSize = 14.sp
                    )
                }

                Text(
                    text = "Just now",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color.Gray, thickness = 1.dp)

            Spacer(modifier = Modifier.height(8.dp))

            // Driver Info Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your Passenger:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "John Doe",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF4CAF50)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /* Handle Start Ride action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(text = "Start Ride", color = Color.White)
                }

                Button(
                    onClick = { /* Handle Cancel Ride action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
                ) {
                    Text(text = "Cancel Ride", color = Color.White)
                }
            }
        }
    }
}
