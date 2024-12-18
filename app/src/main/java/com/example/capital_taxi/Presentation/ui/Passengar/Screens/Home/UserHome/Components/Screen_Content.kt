package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components


import IntercityCard
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material.Icon
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R
import kotlinx.coroutines.launch


@Composable
fun ScreenContent(navController: NavController) {


    Column(
        modifier = Modifier.fillMaxSize()

    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()


        ) {
            DraggableBottomSheet()
        }


    }
}



@Composable
fun BottomSheetContent() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Top indicator
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(5.dp)
                    .background(Color.Gray, CircleShape)
            )
        }


        Box(
            modifier = Modifier
                .fillMaxSize()

                .clip(
                    RoundedCornerShape(
                        topStart = 30.dp, topEnd = 30.dp
                    )
                ) // Match inner shape to border
                .background(Color.White)
                .padding(2.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Top,

                ) {
                Spacer(modifier = Modifier.padding(top = 10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text("Meet after", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .background(
                                Color(0XFF46C96B),

                                )
                            .width(80.dp), contentAlignment = Alignment.Center
                    ) {
                        Text("16\nm", fontSize = 32.sp, fontWeight = FontWeight.Bold)

                    }
                }
                Spacer(modifier = Modifier.padding(top = 10.dp))
                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp)


                Spacer(modifier = Modifier.padding(top = 16.dp))
                Box(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxHeight(0.3f)
                        .fillMaxWidth()
                        .background(Color.White)
                        .shadow(elevation = 3.dp, spotColor = Color.White)

                        .border(
                            width = 2.dp, // Border thickness
                            color = Color.Black.copy(alpha = 0.2f), // Border color
                            shape = RoundedCornerShape(30.dp)
                        )
                        .padding(2.dp), contentAlignment = Alignment.TopStart
                ) {


                    Row(
                        horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top
                    ) {


                        Column {

                            Box(
                                modifier = Modifier
                                    .shadow(elevation = 4.dp)

                                    .padding(25.dp)
                            ) {
                                Column {
                                    androidx.compose.material3.Text(
                                        "Car Number ",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        color = Color.Black.copy(alpha = .3f)
                                    )
                                    Spacer(modifier = Modifier.padding(10.dp))

                                    androidx.compose.material3.Text(
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
                                modifier = Modifier
                                    .size(120.dp)
                                    .padding(end = 5.dp),
                                painter = painterResource(R.drawable.greencarlogo),
                                contentDescription = "car image"
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            callAndChat()
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(top = 16.dp))
                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp)


                Spacer(modifier = Modifier.padding(top = 10.dp))

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .height(60.dp)
                        .border(0.dp, Color.Transparent)  // Remove any border here
                        .shadow(0.dp),  // Remove shadow if any (no elevation)
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFF2F2F2))  // Button color
                ) {
                    Row {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            tint = Color(0XFF46C96B),
                            painter = painterResource(R.drawable.dollar),
                            contentDescription = "cash"
                        )
                        Spacer(modifier = Modifier.padding(15.dp))

                        Text(
                            text = "32.5 EGP", fontSize = 20.sp, color = Color.Black
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(
                            text = "Cash", fontSize = 20.sp, color = Color.Black
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Change", fontSize = 18.sp, color = Color.Gray
                        )
                        Icon(
                            tint = Color.Gray,
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "KeyboardArrowRight"
                        )
                    }
                }
                Spacer(modifier = Modifier.padding( top =  15.dp))

                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp)
                Spacer(modifier = Modifier.padding( top =  15.dp))


                fromLocationToDestination()
                Spacer(modifier = Modifier.padding( top =  15.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,

                ) {
                    Text(" Share your trip status", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .background(Color(0XFF46C96B))
                            .width(80.dp)
                            .height(80.dp) // Ensuring it's square
                            .clip(CircleShape) // Apply circular shape
                    ) {
                        Text(
                            "Share",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White // Optional: Change text color for better contrast
                        )
                    }
                }
            }
        }

    }

}

@Composable
fun fromLocationToDestination() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Spacer(modifier = Modifier.padding(top = 10.dp))

        // Row for Cairo
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(26.dp),
                painter = painterResource(R.drawable.locationicon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(10.dp))

            Text("Cairo", fontSize = 20.sp, fontWeight = FontWeight.W600)

            Spacer(modifier = Modifier.weight(1f)) // Push the next icon to the end

            Box(
                modifier = Modifier
                    .size(35.dp)
                    .background(Color.LightGray, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.pen), // Replace with your pen icon resource
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        repeat(6) {
            VerticalDivider(
                thickness = 2.dp,
                modifier = Modifier
                    .height(5.dp)
                    .padding(start = 10.dp)
            )
        }

        // Row for Alex
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(26.dp),
                painter = painterResource(R.drawable.travel),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.padding(10.dp))

            Text("Alex", fontSize = 20.sp, fontWeight = FontWeight.W600)

            Spacer(modifier = Modifier.weight(1f))

            Row {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .background(Color.LightGray, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.pen),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(4.dp)) // Add space between icons

                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .background(Color.LightGray, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                     imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

        }
        HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp)

    }
}
