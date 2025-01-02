package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberBottomSheetState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
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
import androidx.compose.material.rememberBottomSheetScaffoldState

import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun homeScreenContent(navController: NavController) {
    val scope = rememberCoroutineScope()

    // BottomSheetScaffoldState
    val bottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    // DrawerState
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val gesturesEnabled = drawerState.isOpen
    val context = LocalContext.current

    // Ensure the state is maintained when navigating back
    LaunchedEffect(context) {
        bottomSheetState.bottomSheetState.targetValue
    }

    // Main Container
    Box(modifier = Modifier.fillMaxSize()) {
        // Drawer
        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = gesturesEnabled,
            drawerContent = {
                ModalDrawerSheet {
                    drawerContent(navController)
                }
            }
        ) {
            BottomSheetScaffold(
                scaffoldState = bottomSheetState,
                sheetPeekHeight = 500.dp,
                content = { padding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        MapSection()

                        // TopBar and DraggableIcon
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
                },
                sheetContent = {


                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                            .border(
                                width = 2.dp,              // Thickness of the border
                                color = Color.Gray,        // Color of the border
                                shape = RoundedCornerShape(16.dp) // Rounded corners
                            )
                    ) {
                        pickAndGoDesign(navController)
                    }


                }
            )


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter) // Ensures it's at the bottom
                        .padding(16.dp),
                    elevation = CardDefaults.elevatedCardElevation(10.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            Arrangement.Start,
                            Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(26.dp),
                                painter = painterResource(R.drawable.dollar),
                                tint = Color.Unspecified,

                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            Button(
                                onClick = { /* TODO */ },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(
                                        R.color.primary_color
                                    )
                                ),
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(50.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {

                                Text(text = "Find a driver", color = Color.Black, fontSize = 16.sp)


                            }
                            Spacer(modifier = Modifier.weight(1f))

                            Icon(
                                modifier = Modifier.size(26.dp),
                                painter = painterResource(R.drawable.tools),
                                tint = Color.Black,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}

