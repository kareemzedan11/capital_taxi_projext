package com.example.capital_taxi.Presentation.ui.Driver.Screens.Home

import TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.DriverNavigationDrawer
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.RideRequestCard
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
                    // TripArrivedCard()
                    MapSection(navController = navController)
                    //  PassengerConnectionCard()
                    if (isStart) {
                      tripDetailsCard(light = false)
                        //TripArrivedCard()
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


                                Text(text = "0.00 EGB", fontSize = 18.sp)
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
            captainToPassengar(navController)

        }
    }


}
@Composable
fun PassengerConnectionCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Connecting to your Passenger",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        // Linear Progress Indicator
        LinearProgressIndicator(
            color = Color.Blue,

            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .padding(bottom = 16.dp)
        )
        // Information Rows
        InfoRow(
            icon = android.R.drawable.ic_menu_mylocation,
            label = "Pickup Point",
            value = "New Jersey, Delaware 2673"
        )
        InfoRow(
            icon = android.R.drawable.ic_menu_directions,
            label = "Drop-off point",
            value = "Nezer Building, Addibas 3476"
        )
        InfoRow(
            icon = android.R.drawable.ic_menu_recent_history,
            label = "Drive time",
            value = "20mins"
        )
        InfoRow(icon = android.R.drawable.ic_menu_compass, label = "Distance", value = "24km")
        InfoRow(
            icon = android.R.drawable.ic_menu_myplaces,
            label = "Number of Persons",
            value = "3"
        )
        InfoRow(icon = android.R.drawable.ic_menu_manage, label = "Payment Type", value = "Cash")


    }
}


@Composable
fun TripArrivedCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        // Header
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = "YOU ARRIVED",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Trip Duration
        Text(
            text = "20 min",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Pickup and Drop-off Points
        InfoRow(icon = android.R.drawable.ic_menu_mylocation, label = "New Jersey, Delaware 2673")
        InfoRow(
            icon = android.R.drawable.ic_menu_directions,
            label = "Nezer Building, Addibas 3476"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Trip Review Link
        Text(
            text = "See Trip Review",
            color = colorResource(R.color.primary_color),
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        androidx.compose.material.Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .height(50.dp),
            colors = androidx.compose.material.ButtonDefaults.buttonColors(
                backgroundColor = colorResource(R.color.primary_color)
            )
        ) {
            androidx.compose.material.Text(
                "End Trip",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun InfoRow(icon: Int, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
fun InfoRow(icon: Int, label: String, value: String) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(7.dp))

            Text(text = label, color = Color.Gray, fontSize = 14.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = value, fontWeight = FontWeight.Bold, fontSize = 14.sp)


        }
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
        )
    }
}