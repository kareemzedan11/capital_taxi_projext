package com.example.capital_taxi.Presentation.ui.Passengar.Screens.HelpScreen

import androidx.compose.animation.animateContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.capital_taxi.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.Help),

                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .background(Color(0xFFF5F5F5))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.Start
            ) {

                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.help))
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )

                Row(
                    modifier = Modifier
                        .background(colorResource(R.color.secondary_color)) // Background with transparency
                        .border(2.dp, Color.Black, RoundedCornerShape(8.dp)) // Border with corner radius

                ,
                verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start) {

               Text(
                   modifier = Modifier.padding(start = 5.dp),
                   text = stringResource(R.string.How_can_we_help_you),

                   fontWeight = FontWeight.Medium,
                   fontSize = 18.sp,
                   color = Color.Black
               )
               Box(
                   modifier = Modifier
                       .fillMaxWidth()
                       .fillMaxHeight(0.15f)
                       .background(Color.Transparent)
               ) {
                   LottieAnimation(
                       composition = composition,
                       progress = { progress },
                       modifier = Modifier
                           .fillMaxWidth()
                           .size(105.dp)

                   )
               }
           }

                Text(
                    text = stringResource(R.string.Select_Topic),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                // Add the search button here
                Button(
                    onClick = { /* Implement your search action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .padding(vertical = 8.dp)
                        .align(Alignment.Start),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.DarkGray,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = stringResource(R.string.search_for_Topics),

                            color = Color.DarkGray
                        )
                    }
                }


                val helpTopics = listOf(
                    stringResource(R.string.Trips),
                    stringResource(R.string.Payment_and_Billing),
                    stringResource(R.string.Ride_Safety),
                    stringResource(R.string.Account),
                    stringResource(R.string.Rate_and_Feedback),
                )

                helpTopics.forEach { topic ->
                    HelpTopicItem(
                        topic = topic,
                        onClick = {
                            navController.navigate("help_detail/$topic")
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun HelpTopicItem(topic: String, onClick: () -> Unit) {
   Box(modifier = Modifier.background(Color.White)){
       Card(
           modifier = Modifier
               .fillMaxWidth()
               .clickable(onClick = onClick)
               .animateContentSize(),
           colors = CardDefaults.cardColors(
               containerColor = Color.White
           ),
           elevation = CardDefaults.cardElevation(6.dp),
           shape = RoundedCornerShape(16.dp)
       ) {
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(20.dp),
               verticalAlignment = Alignment.CenterVertically
           ) {
               Text(
                   text = topic,
                   fontWeight = FontWeight.Bold,
                   fontSize = 18.sp,
                   color = Color.Black,
                   modifier = Modifier.weight(1f)
               )
               Icon(
                   imageVector = Icons.Default.ArrowForward,
                   contentDescription = "Go to Details",
                   tint = Color.Gray,
                   modifier = Modifier.size(24.dp)
               )
           }
       }
   }
}
