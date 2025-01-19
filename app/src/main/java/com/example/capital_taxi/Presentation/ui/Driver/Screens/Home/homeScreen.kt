package com.example.capital_taxi.Presentation.ui.Driver.Screens.Home

import TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.InfoRow
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.RatingBar
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.RideInfoCard
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.RideRequestCard
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.TripArrivedCard
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.TripArrivedCard2
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.captainToPassengar
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.driverHomeScreenContent
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.tripDetailsCard
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
                    TripArrivedCard()
                    MapSection(navController = navController)
                    //  PassengerConnectionCard()
                    if (isStart) {
                        //   TripInProgressCardSimplified()
                        // DriverArrivedCard()
                        // RideInfoCard()
                        // StartTripScreen(onStartTrip = {})
                        //  TripArrivedCard()
                        //  tripDetailsCard(light = false)
                        // TripArrivedCard()
                        TripArrivedCard2()
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


        }
    }


}