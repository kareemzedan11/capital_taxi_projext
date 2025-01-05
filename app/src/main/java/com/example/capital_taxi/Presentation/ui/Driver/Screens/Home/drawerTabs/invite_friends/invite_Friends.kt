package com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.drawerTabs.invite_friends

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.capital_taxi.R

@Composable
fun InviteFriendsPage(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Invite Friends",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = { navController.popBackStack() }) {
                        Box(
                            modifier = Modifier

                                .size(36.dp)
                                .background(Color.Transparent)
                                .border(4.dp, color = Color.Black, RoundedCornerShape(30.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            androidx.compose.material3.Icon(
                                modifier = Modifier.size(26.dp),
                                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                                contentDescription = "Back",
                                tint = Color.Black
                            )
                        }
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .background(Color.White)
            ) {
                // Lottie Animation
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.invite))
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .background(Color.LightGray)
                ) {
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .background(Color.White)
                ) {
                    Column {
                        // Tabs
                        var selectedTabIndex by remember { mutableStateOf(0) }
                        val tabTitles = listOf("Offers", "Invitations")

                        TabRow(selectedTabIndex = selectedTabIndex) {
                            tabTitles.forEachIndexed { index, title ->
                                Tab(
                                    modifier = Modifier.background(colorResource(R.color.primary_color)),
                                    selectedContentColor = Color.Black,
                                    unselectedContentColor = Color.White,
                                    selected = selectedTabIndex == index,
                                    onClick = { selectedTabIndex = index },

                                    text = { Text(title, fontWeight = FontWeight.Bold) }
                                )
                            }
                        }

                        // Content based on selected tab
                        when (selectedTabIndex) {
                            0 -> {
                                Text(
                                    text = "You haven't any Offers yet.",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    style = MaterialTheme.typography.body1,
                                    color = Color.Gray
                                )

                            }
                            1 -> {
                                Text(
                                    text = "You haven't sent any invitations yet.",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    style = MaterialTheme.typography.body1,
                                    color = Color.Gray
                                )
                            }
                        }

                        // Invite Button
                        Button(
                            onClick = { /* Handle invite */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 24.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
                        ) {
                            Text("Invite", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun OfferCard(title: String, description: String, conditions: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 4.dp,
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = conditions,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )
        }
    }
}
