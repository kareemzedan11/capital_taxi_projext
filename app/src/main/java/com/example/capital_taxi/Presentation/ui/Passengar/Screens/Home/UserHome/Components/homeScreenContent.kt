package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalDrawer
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column(modifier = Modifier.fillMaxSize()) {

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    drawerContent(navController)
                }
            },
            gesturesEnabled = gesturesEnabled,
            modifier = Modifier.weight(1f)
        ) {
            BottomSheetScaffold(
                scaffoldState = bottomSheetState,
                sheetPeekHeight = 500.dp,
                sheetContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        // pickAndGoDesign(navController)
                        // ServiceAvailability(navController)
                        //   EnableLocationServices()
                        //  RideDetailsScreen(navController)
                        // searchAboutADriver()

                        //  confirmPickup()

                        //searchAboutADriver()
                        // TripDetailsLiveTracker()
                        //  TripRatingDialog()
                    }
                }
            ) { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    MapSection()
//
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.TopStart)
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
                        DraggableIcon(navController = navController)
                    }
                }
            }
        }
// if (drawerState.isClosed){
//
//     Card(
//         modifier = Modifier
//             .fillMaxWidth()
//             .padding(16.dp),
//         elevation = CardDefaults.elevatedCardElevation(10.dp),
//         shape = RoundedCornerShape(16.dp)
//     ) {
//         Box(
//             modifier = Modifier
//                 .fillMaxWidth()
//                 .background(Color.Transparent)
//                 .height(80.dp),
//             contentAlignment = Alignment.Center
//         ) {
//             Button(
//                 onClick = { /* TODO */ },
//                 colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF46C96B)),
//                 modifier = Modifier
//                     .width(200.dp)
//                     .height(50.dp),
//                 contentPadding = PaddingValues(0.dp)
//             ) {
//                 Text(text = "Find a driver", color = Color.Black, fontSize = 16.sp)
//             }
//         }
//     }
// }


    }
}