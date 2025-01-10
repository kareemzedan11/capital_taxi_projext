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
import androidx.compose.ui.res.stringResource
import com.example.capital_taxi.R

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
                            contentDescription =null
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
                    text = stringResource(id = R.string.Details_about, topic),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                when (topic) {
                    stringResource(id = R.string.Trips) -> TripHelpSectionList()
                    stringResource(id = R.string.Payment_and_Billing) -> PaymentHelpSectionList()
                    stringResource(id = R.string.Ride_Safety) -> RideSafetySectionList()
                    stringResource(id = R.string.Account) -> ManageAccountList()
                    stringResource(id = R.string.Rate_and_Feedback) -> RateFeedbackList()
                    else -> Text(stringResource(id = R.string.Content_coming_soon))
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = stringResource(id = R.string.Was_this_helpful),
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
                    Button(
                        onClick = { /* Handle Yes click */ },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Text(stringResource(id = R.string.Yes), color = Color.White)
                    }

                    Button(
                        onClick = { /* Handle No click */ },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
                    ) {
                        Text(stringResource(id = R.string.No), color = Color.White)
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
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HelpSection(stringResource(id = R.string.Trip_Routes_and_Navigation))
            HelpSection(stringResource(id = R.string.Trip_Scheduling_and_Cancellations))
            HelpSection(stringResource(id = R.string.Trip_Fare_Estimation))
            HelpSection(stringResource(id = R.string.Trip_Issues_and_Disputes))
            HelpSection(stringResource(id = R.string.Tracking_Your_Trip))
            HelpSection(stringResource(id = R.string.Ride_Comfort_and_Preferences))
            HelpSection(stringResource(id = R.string.Safety_During_Trips))
            HelpSection(stringResource(id = R.string.What_to_Do_if_Your_Driver_is_Lost))
        }
    }
}

@Composable
fun HelpSection(text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 3.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
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
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HelpSection(stringResource(id = R.string.How_to_Add_a_Payment_Method))
            HelpSection(stringResource(id = R.string.How_to_Remove_a_Payment_Method))
            HelpSection(stringResource(id = R.string.How_to_Update_Your_Payment_Details))
            HelpSection(stringResource(id = R.string.Checking_Your_Payment_History))
            HelpSection(stringResource(id = R.string.Understanding_Payment_Receipts))
            HelpSection(stringResource(id = R.string.What_to_Do_If_Your_Payment_Fails))
            HelpSection(stringResource(id = R.string.Payment_Refunds_and_Disputes))
        }
    }
}

@Composable
fun RideSafetySectionList() {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HelpSection(stringResource(id = R.string.In_Ride_Safety))
            HelpSection(stringResource(id = R.string.Accident_Reporting))
            HelpSection(stringResource(id = R.string.Lost_Items))
            HelpSection(stringResource(id = R.string.Safety_Measures_and_Policies))
            HelpSection(stringResource(id = R.string.Data_Privacy_and_Security))
        }
    }
}

@Composable
fun ManageAccountList() {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HelpSection(stringResource(id = R.string.Updating_Personal_Information))
            HelpSection(stringResource(id = R.string.Password_Management))
            HelpSection(stringResource(id = R.string.Notification_Preferences))
            HelpSection(stringResource(id = R.string.Linked_Accounts))
            HelpSection(stringResource(id = R.string.Language_and_Region_Settings))
            HelpSection(stringResource(id = R.string.Account_Deactivation_or_Deletion))
        }
    }
}

@Composable
fun RateFeedbackList() {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HelpSection(stringResource(id = R.string.How_to_Rate_Your_Ride))
            HelpSection(stringResource(id = R.string.Leaving_Comments))
            HelpSection(stringResource(id = R.string.Driver_Compliments))
            HelpSection(stringResource(id = R.string.Reporting_Issues))
            HelpSection(stringResource(id = R.string.Anonymous_Feedback))
            HelpSection(stringResource(id = R.string.Customer_Support_Follow_Up))
        }
    }
}
