package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ModalDrawer
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.capital_taxi.R
import drawerContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreenContent(navController: NavController) {
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberBottomSheetScaffoldState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val gesturesEnabled = drawerState.isOpen

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                drawerContent(navController)
            }
        },
        gesturesEnabled = gesturesEnabled,
        modifier = Modifier.fillMaxSize()
    ) {
        confirmPickup()

        BottomSheetScaffold(
            scaffoldState = bottomSheetState,
            sheetPeekHeight = 500.dp,

            sheetContent = {
                // Bottom Sheet Content
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {

                    PickupWithDropOffButtons(navController)

                    // searchAboutADriver()
                     //EnableLocationServices()
                     //   confirmPickup()
                    //  RideDetailsScreen(navController)
                     //  TripDetailsLiveTracker()
                     // ServiceAvailability(navController)
                }
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {


                     MapSection()


//                    val composition by rememberLottieComposition(
//                        spec = LottieCompositionSpec.RawRes(R.raw.searching)
//                    )
//                    val progress2 by animateLottieCompositionAsState(
//                        composition = composition,
//                        iterations = LottieConstants.IterateForever
//                    )
//
//                    Box(modifier = Modifier.fillMaxWidth(0.7f), contentAlignment = Alignment.Center) {
//                        LottieAnimation(
//                            composition = composition,
//                            progress = progress2,
//                            modifier = Modifier.fillMaxWidth()
//                        )
//                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.TopStart)
                ) {
                    TopBar(
                        onOpenDrawer = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open() // Open drawer when button clicked
                                } else {
                                    drawerState.close() // Close the drawer
                                }
                            }
                        },
                        navController = navController
                    )

                       DraggableIcon(navController = navController)


                }
            }
        }
    }
}

