package com.example.capital_taxi.Presentation.ui.Passengar.Screens.HelpScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpDetailScreen(navController: NavController, topic: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = topic,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF5F5F5))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Details about $topic",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                when (topic) {
                    "Trips" -> TripHelpSectionList()
                    "Payment and Billing" -> PaymentHelpSectionList()
                    "Ride Safety" -> RideSafetySectionList()
                    "Account" -> ManageAccountList()
                    "Rate and Feedback" -> RateFeedbackList()
                    else -> Text("Content coming soon...")
                }

                Spacer(modifier = Modifier.weight(1f))

                // Adding the "Was this helpful?" text at the bottom
                Text(
                    text = "Was this helpful?",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Yes button
                    Button(
                        onClick = { /* Handle Yes click */ },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Text("Yes", color = Color.White)
                    }

                    // No button
                    Button(
                        onClick = { /* Handle No click */ },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
                    ) {
                        Text("No", color = Color.White)
                    }
                }
            }
        }
    )
}




@Composable
fun TripHelpSectionList() {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxWidth() // Ensure the column fills the width
                .padding(16.dp), // Add padding for better visual spacing
            verticalArrangement = Arrangement.spacedBy(12.dp) // Add space between items
        ) {
            HelpSection("Trip Routes and Navigation")
            HelpSection("Trip Scheduling and Cancellations")
            HelpSection("Trip Fare Estimation")
            HelpSection("Trip Issues and Disputes")
            HelpSection("Tracking Your Trip")
            HelpSection("Ride Comfort and Preferences")
            HelpSection("Safety During Trips")
            HelpSection("What to Do if Your Driver is Lost")
        }
    }
}



@Composable
fun HelpSection(text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical =  10.dp, horizontal = 5.dp)
            .background(Color.White)
            , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 3.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text, fontSize = 18.sp, fontWeight = FontWeight.Bold)
Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Go to Details",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier.fillMaxWidth(.8f)
        )
    }
}


@Composable
fun PaymentHelpSectionList() {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxWidth() // Ensure the column fills the width
                .padding(16.dp), // Add padding for better visual spacing
            verticalArrangement = Arrangement.spacedBy(12.dp) // Add space between items
        ) {
            HelpSection("How to Add a Payment Method")
            HelpSection("How to Remove a Payment Method")
            HelpSection("How to Update Your Payment Details")
            HelpSection("Checking Your Payment History")
            HelpSection("Understanding Payment Receipts")
            HelpSection("What to Do If Your Payment Fails")
            HelpSection("Payment Refunds and Disputes")
        }
    }
}


@Composable
fun RideSafetySectionList() {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxWidth() // Ensure the column fills the width
                .padding(16.dp), // Add padding for better visual spacing
            verticalArrangement = Arrangement.spacedBy(12.dp) // Add space between items
        ) {
            HelpSection("In-Ride Safety")
            HelpSection("Accident Reporting")
            HelpSection("Lost Items")
            HelpSection("Safety Measures and Policies")
            HelpSection("Data Privacy and Security")

        }
    }
}

@Composable
fun ManageAccountList() {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxWidth() // Ensure the column fills the width
                .padding(16.dp), // Add padding for better visual spacing
            verticalArrangement = Arrangement.spacedBy(12.dp) // Add space between items
        ) {
            HelpSection("Updating Personal Information")
            HelpSection("Password Management")
            HelpSection("Notification Preferences")
            HelpSection("Linked Accounts")
            HelpSection("Language and Region Settings")
            HelpSection("Account Deactivation or Deletion")


        }
    }
}


@Composable
fun RateFeedbackList() {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxWidth() // Ensure the column fills the width
                .padding(16.dp), // Add padding for better visual spacing
            verticalArrangement = Arrangement.spacedBy(12.dp) // Add space between items
        ) {
            HelpSection("How to Rate Your Ride")
            HelpSection("Leaving Comments")
            HelpSection("Driver Compliments")
            HelpSection("Reporting Issues")
            HelpSection("Anonymous Feedback")
            HelpSection("Customer Support Follow-Up")


        }
    }
}
