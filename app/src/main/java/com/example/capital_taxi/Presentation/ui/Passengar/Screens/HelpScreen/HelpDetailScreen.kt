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
                    "Managing Your Profile" -> ManageProfileDetails()
                    "Promotions and Discounts" -> PromotionsDetails()
                    "Tracking and Notifications" -> TrackingDetails()
                    "Ride History" -> RideHistoryDetails()
                    "Customer Support" -> CustomerSupportDetails()
                    "Rate and Feedback" -> RateFeedbackDetails()
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
            HelpSection("Always check the driver's identity before getting in.")
            HelpSection("Always wear your seatbelt during the ride.")
            HelpSection("Share your ride details with a friend for safety.")
            HelpSection("Make sure you know the route before starting the ride.")
            HelpSection("Avoid getting off in dark or isolated areas.")

        }
    }
}

@Composable
fun ManageProfileDetails() {
    Text(
        text = "1. Update your profile picture and information.\n" +
                "2. Change your email or phone number.\n" +
                "3. Manage your notifications settings.",
        fontSize = 14.sp,
        color = Color.Black
    )
}

@Composable
fun PromotionsDetails() {
    Text(
        text = "1. Check the Promotions tab for available discounts.\n" +
                "2. Apply a promo code during booking to get a discount.",
        fontSize = 14.sp,
        color = Color.Black
    )
}

@Composable
fun TrackingDetails() {
    Text(
        text = "1. Use the app to track your ride in real-time.\n" +
                "2. Receive notifications for ride status updates.",
        fontSize = 14.sp,
        color = Color.Black
    )
}

@Composable
fun RideHistoryDetails() {
    Text(
        text = "1. View your past ride details in the 'History' tab.\n" +
                "2. Access receipts and payment information.",
        fontSize = 14.sp,
        color = Color.Black
    )
}

@Composable
fun CustomerSupportDetails() {
    Text(
        text = "1. Contact customer support via the app's 'Help' section.\n" +
                "2. Use live chat or call for assistance.",
        fontSize = 14.sp,
        color = Color.Black
    )
}

@Composable
fun RateFeedbackDetails() {
    Text(
        text = "1. Rate your ride experience.\n" +
                "2. Provide feedback to improve service quality.",
        fontSize = 14.sp,
        color = Color.Black
    )
}
