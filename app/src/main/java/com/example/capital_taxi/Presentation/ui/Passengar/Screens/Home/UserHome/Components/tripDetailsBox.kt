package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R


@Composable
fun tripDetailsBox(navController:NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.55f)
            .background(Color.Transparent)
            .border(
                width = 2.dp, // Border thickness
                color = Color.White, // Border color
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            ),
    ) {
        // Inner Box with white background
        Box(
            modifier = Modifier
                .fillMaxSize()

                .clip(
                    RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp
                    )
                ) // Match inner shape to border
                .background(Color.White)
                .padding(2.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Top,

                ) {

                Spacer(modifier = Modifier.padding(top = 10.dp))
                Box(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxHeight(0.5f)
                        .fillMaxWidth()
                        .background(Color.White)
                        .shadow(elevation = 3.dp, spotColor = Color.White)

                        .border(
                            width = 2.dp, // Border thickness
                            color = Color.Black.copy(alpha = 0.2f), // Border color
                            shape = RoundedCornerShape(30.dp)
                        )
                        .padding(2.dp),
                    contentAlignment = Alignment.TopStart
                ) {


                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Top
                    ) {


                        Column {

                            Box(
                                modifier = Modifier
                                    .background(Color.White)
                                    .shadow(elevation = 4.dp)
                                    .padding(25.dp)
                            ) {
                                Column {
                                    Text(
                                        "Car Number ",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        color = Color.Black.copy(alpha = .3f)
                                    )
                                    Spacer(modifier = Modifier.padding(10.dp))

                                    Text(
                                        "Car Type",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                    )

                                }


                            }
                            Spacer(modifier = Modifier.weight(1f))

                            driverDetails()

                        }


                        Spacer(modifier = Modifier.weight(1f))

                        Column {
                            Image(
                                modifier = Modifier.size(120.dp).padding(end =5.dp ),
                                painter = painterResource(R.drawable.greencarlogo),
                                contentDescription = "car image"
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            callAndChat(navController)
                        }
                    }
                }
            }
        }
    }
}


