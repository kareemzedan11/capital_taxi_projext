package com.example.capital_taxi.Presentation.ui.Driver.Screens.Home

import TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.DriverNavigationDrawer
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
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberBottomSheetScaffoldState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )


    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight().padding(bottom = 20.dp),
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false },


        ) {

        }
    }
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
            confirmPickup()

            BottomSheetScaffold(
                scaffoldState = bottomSheetState,
                sheetPeekHeight = 300.dp,
                sheetContent = {
                    // Bottom Sheet Content
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        driverHomeScreenContent(navController)
                    }
                }
            ) { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                ) {
                    // Map Section and Animation
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                         MapSection()
                        tripDetailsCard(light = false)

                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .align(Alignment.TopStart)
                    ) {
                        Row(horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically) {
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
                                   showBottomSheet = true
                               },

                               modifier = Modifier.wrapContentWidth()
                                   .height(60.dp)
                                   .padding(end = 80.dp)
                           ) {
                              Row(


                                  horizontalArrangement = Arrangement.Center,
                                  verticalAlignment = Alignment.CenterVertically) {

                                  Icon(

                                       contentDescription = null,
                                      imageVector = Icons.Default.ArrowDropDown
                                      , tint = Color.White
                                  )
                                  Spacer(modifier = Modifier.width(4.dp))

                                  Text(text = "0.00 EGB", fontSize = 18.sp)
                              }

                           }
                            Spacer(modifier = Modifier.weight(1f))

                       }
                    }
                }
            }
        }

        captainToPassengar(navController)
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.White)
//                .height(100.dp)
//                .align(Alignment.BottomCenter)
//                .padding(16.dp),
//
//            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
//
//            ) {
//
//            Row(  modifier = Modifier.fillMaxSize().padding(horizontal =
//            10.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//
//
//                Icon(
//
//                    modifier = Modifier.size(26.dp),
//                    painter = painterResource(R.drawable.note), contentDescription = null
//                    , tint = Color.Unspecified
//                )
//
//                Spacer(modifier = Modifier.weight(1f))
//
//                Button(
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF46C96B)),
//                    onClick = {
//                        // Handle button click here
//                    },
//                    modifier = Modifier.fillMaxWidth(0.4f).fillMaxHeight(.8f)
//                ) {
//                    Text(text = "Start", fontSize = 18.sp)
//                }
//                Spacer(modifier = Modifier.weight(1f))
//                Icon(
//                    modifier = Modifier.size(26.dp),
//                    painter = painterResource(R.drawable.tools), contentDescription = null
//                )
//            }
//
//        }
    }
}
