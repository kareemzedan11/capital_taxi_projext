package com.example.capital_taxi.Presentation.ui.Driver.Screens.Home

import TopBar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app.ui.theme.CustomFontFamily
import com.example.app.ui.theme.responsiveTextSize
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.DriverArrivedCard
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.DriverNavigationDrawer
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.DriverTripAcceptedScreen
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.InfoRow
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.PassengerConnectionCard
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.RatingBar
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.RideInfoCard
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.RideRequestCard
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.StartTripScreen
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.TripArrivedCard
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.TripArrivedCard2
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.TripDetailsCard
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.TripInProgressCardSimplified
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.captainToPassengar
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.driverHomeScreenContent

import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components.MapSection
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components.confirmPickup
import com.example.capital_taxi.R
import drawerContent

import kotlinx.coroutines.launch
import passengerAcceptPrice

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun driverHomeScreen(navController: NavController) {
    var isStart by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)


    val gesturesEnabled = drawerState.isOpen

    Box(modifier = Modifier.fillMaxSize()) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    DriverNavigationDrawer(navController)
                }
            },
            gesturesEnabled = gesturesEnabled,
            modifier = Modifier.fillMaxSize()
        ) {


            driverHomeScreenContent(navController)

            Box(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                // Map Section and Animation
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    MapSection(navController = navController)
                  //   PassengerConnectionCard()
                    if (isStart) {
                       TripInProgressCardSimplified()
                        // DriverArrivedCard()
            //  RideInfoCard()
            //   captainToPassengar(navController)
//                        DriverTripAcceptedScreen(
//                            userName = "Jane Doe",
//                            userRating = 4.5f,
//                            pickupLocation = "123 Main St, Cairo",
//                            dropoffLocation = "456 Elm St, Alexandria",
//                            etaToPickup = "10 mins",
//                            distanceToPickup = "5 km",
//                            onNavigate = { /* Handle Navigation */ },
//                            onCallUser = { /* Handle Call User */ },
//                            onMessageUser = { /* Handle Message User */ },
//                            onCancelTrip = { /* Handle Cancel Trip */ }
//                        )
                        //  TripArrivedCard()
                        //TripDetailsCard(light = false)
                          //TripArrivedCard()
                        // TripArrivedCard2()
                       // DriverArrivedCard()

                    }


                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.TopStart)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TopBar(
                            onOpenDrawer = {
                                scope.launch {
                                    if (drawerState.isClosed) {
                                        drawerState.open()
                                    } else {
                                        drawerState.close()
                                    }
                                }
                            },
                            navController = navController
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                            onClick = {

                            },

                            modifier = Modifier
                                .wrapContentWidth()
                                .height(60.dp)
                                .padding(end = 80.dp)
                        ) {
                            Row(


                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {


                                Text(
                                    text = "0.00 EGB",
                                    fontSize = responsiveTextSize(
                                        fraction = 0.06f,
                                        minSize = 14.sp,
                                        maxSize = 18.sp
                                    ),


                                    fontFamily = CustomFontFamily,
                                )
                            }

                        }
                        Spacer(modifier = Modifier.weight(1f))

                    }
                }
            }



        if (!isStart) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(100.dp)
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),

                elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),

                ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal =
                            10.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Icon(

                        modifier = Modifier.size(26.dp),
                        painter = painterResource(R.drawable.note),
                        contentDescription = null,
                        tint = colorResource(R.color.Icons_color)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary_color)),
                        onClick = {
                            isStart = true
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .fillMaxHeight(.8f)
                    ) {
                        Text(text = "Start", fontSize = 18.sp, color = Color.Black)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(

                        tint = colorResource(R.color.Icons_color),
                        modifier = Modifier.size(26.dp),
                        painter = painterResource(R.drawable.tools), contentDescription = null
                    )
                }
            }
        } else if (isStart) {


        }}
    }
}



//
//@Composable
//fun UserTripAcceptedScreen(
//    driverName: String,
//    driverRating: Float,
//    vehicleModel: String,
//    licensePlate: String,
//    pickupLocation: String,
//    dropoffLocation: String,
//    eta: String,
//    onCallDriver: () -> Unit,
//    onMessageDriver: () -> Unit,
//    onCancelTrip: () -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        // Driver and Vehicle Details
//        Card(
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(16.dp),
//            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                // Driver Details
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    // Driver Photo
//                    Box(
//                        modifier = Modifier
//                            .size(64.dp)
//                            .clip(CircleShape)
//                            .background(Color.LightGray),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Icon(
//                            painter = painterResource(R.drawable.person),
//                            contentDescription = "Driver Photo",
//                            modifier = Modifier.size(32.dp),
//                            tint = Color.White
//                        )
//                    }
//
//                    // Driver Name and Rating
//                    Column {
//                        Text(
//                            text = driverName,
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color.Black
//                        )
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.spacedBy(4.dp)
//                        ) {
//                            Icon(
//                                painter = painterResource(R.drawable.baseline_star_24),
//                                contentDescription = "Rating",
//                                modifier = Modifier.size(16.dp),
//                                tint = Color.Yellow
//                            )
//                            Text(
//                                text = driverRating.toString(),
//                                fontSize = 14.sp,
//                                color = Color.Gray
//                            )
//                        }
//                    }
//                }
//
//                // Vehicle Details
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(R.drawable.uber
//                        ),
//                        contentDescription = "Vehicle",
//                        modifier = Modifier.size(32.dp),
//                        tint = Color.Black
//                    )
//                    Column {
//                        Text(
//                            text = vehicleModel,
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Medium,
//                            color = Color.Black
//                        )
//                        Text(
//                            text = licensePlate,
//                            fontSize = 14.sp,
//                            color = Color.Gray
//                        )
//                    }
//                }
//            }
//        }
//
//        // Trip Details
//        Card(
//            modifier = Modifier.fillMaxWidth(),
//            shape = RoundedCornerShape(16.dp),
//            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                // Pickup Location
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(R.drawable.circle),
//                        contentDescription = "Pickup",
//                        modifier = Modifier.size(24.dp),
//                        tint = colorResource(R.color.primary_color)
//                    )
//                    Text(
//                        text = pickupLocation,
//                        fontSize = 16.sp,
//                        color = Color.Black
//                    )
//                }
//
//                // Dropoff Location
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(R.drawable.travel),
//                        contentDescription = "Dropoff",
//                        modifier = Modifier.size(24.dp),
//                        tint = colorResource(R.color.primary_color)
//                    )
//                    Text(
//                        text = dropoffLocation,
//                        fontSize = 16.sp,
//                        color = Color.Black
//                    )
//                }
//
//                // ETA
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(R.drawable.clock),
//                        contentDescription = "ETA",
//                        modifier = Modifier.size(24.dp),
//                        tint = colorResource(R.color.primary_color)
//                    )
//                    Text(
//                        text = "ETA: $eta",
//                        fontSize = 16.sp,
//                        color = Color.Black
//                    )
//                }
//            }
//        }
//
//        // Call and Message Buttons
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            Button(
//                onClick = onCallDriver,
//                modifier = Modifier
//                    .weight(1f)
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_color)),
//                shape = RoundedCornerShape(12.dp)
//            ) {
//                Text(text = "Call Driver", color = Color.White, fontSize = 16.sp)
//            }
//
//            Button(
//                onClick = onMessageDriver,
//                modifier = Modifier
//                    .weight(1f)
//                    .height(50.dp),
//                colors = ButtonDefaults.buttonColors(Color.Transparent),
//                border = BorderStroke(1.dp, colorResource(R.color.primary_color)),
//                shape = RoundedCornerShape(12.dp)
//            ) {
//                Text(text = "Message", color = colorResource(R.color.primary_color), fontSize = 16.sp)
//            }
//        }
//
//        // Cancel Trip Button
//        Button(
//            onClick = onCancelTrip,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp),
//            colors = ButtonDefaults.buttonColors(Color.Red),
//            shape = RoundedCornerShape(12.dp)
//        ) {
//            Text(text = "Cancel Trip", color = Color.White, fontSize = 16.sp)
//        }
//    }
//}
//UserTripAcceptedScreen(
//driverName = "John Doe",
//driverRating = 4.7f,
//vehicleModel = "Toyota Corolla",
//licensePlate = "ABC-1234",
//pickupLocation = "123 Main St, Cairo",
//dropoffLocation = "456 Elm St, Alexandria",
//eta = "10 mins",
//onCallDriver = { /* Handle Call Driver */ },
//onMessageDriver = { /* Handle Message Driver */ },
//onCancelTrip = { /* Handle Cancel Trip */ }
//)