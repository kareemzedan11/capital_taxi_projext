//package com.example.capital_taxi.Presentation.ui.Passengar.Screens.HelpScreen
//
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.filled.KeyboardArrowRight
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun driverHelpDetailScreen(navController: NavController, topic: String) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = topic,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 20.sp,
//                        color = Color.Black
//                    )
//                },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "Back"
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.mediumTopAppBarColors(
//                    containerColor = Color.White
//                )
//            )
//        },
//        content = { paddingValues ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//                    .background(Color(0xFFF5F5F5))
//                    .padding(16.dp)
//            ) {
//                Text(
//                    text = "Details about $topic",
//                    fontWeight = FontWeight.Medium,
//                    fontSize = 18.sp,
//                    color = Color.Black
//                )
//
//                Spacer(modifier = Modifier.height(20.dp))
//
//                when (topic) {
//                    "Trips" -> TripHelpSectionList()
//                    "Payment and Billing" -> PaymentHelpSectionList()
//                    "Ride Safety" -> RideSafetySectionList()
//                    "Account" -> ManageAccountList()
//                    "Rate and Feedback" -> RateFeedbackList()
//                    else -> Text("Content coming soon...")
//                }
//
//                Spacer(modifier = Modifier.weight(1f))
//
//                // Adding the "Was this helpful?" text at the bottom
//                Text(
//                    text = "Was this helpful?",
//                    fontWeight = FontWeight.Medium,
//                    fontSize = 16.sp,
//                    color = Color.Black,
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    // Yes button
//                    Button(
//                        onClick = { /* Handle Yes click */ },
//                        shape = RoundedCornerShape(50),
//                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
//                    ) {
//                        Text("Yes", color = Color.White)
//                    }
//
//                    // No button
//                    Button(
//                        onClick = { /* Handle No click */ },
//                        shape = RoundedCornerShape(50),
//                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
//                    ) {
//                        Text("No", color = Color.White)
//                    }
//                }
//            }
//        }
//    )
//}
//
//
