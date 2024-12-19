package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.Icon
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R
import kotlinx.coroutines.delay
import java.time.format.TextStyle


@Composable
fun RideDetailsScreen(navController: NavController) {


    Column(
        modifier = Modifier.fillMaxSize()

    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()


        ) {
            DraggableBottomSheet(sheetContent =  {BottomSheetContent()} )
        }


    }
}



@Composable
fun BottomSheetContent() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp)
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
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "The meeting will take place in ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    RoundedTimeDisplayWithFill()
                }
                Spacer(modifier = Modifier.padding(top = 10.dp))
                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp)
                Spacer(modifier = Modifier.padding(top = 10.dp))

                RideDetailsCard()
                Spacer(modifier = Modifier.padding(top = 10.dp))

                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp)
                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Box(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)

                        .fillMaxWidth()
                        .shadow(20.dp, shape = RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(16.dp)
                        .padding(2.dp),
                    contentAlignment = Alignment.TopStart
                ) {


                    Row(
                        horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top
                    ) {


                        Column {

                            Box(
                                modifier = Modifier


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

                            Spacer(modifier = Modifier.padding(top = 16.dp))

                            driverDetails()

                        }
                        Spacer(modifier = Modifier.padding(top = 16.dp))




                        Column {
                            Box(
                                modifier = Modifier
                                    .size(120.dp)
                                    .padding(end = 5.dp)
                                    .shadow(8.dp, shape = RoundedCornerShape(8.dp))
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.White)
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.uber),
                                    contentDescription = "car image",
                                    modifier = Modifier.fillMaxSize() // لجعل الصورة تغطي كل الـ Box
                                )
                            }

                            Spacer(modifier = Modifier.padding(top = 16.dp))

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
                Spacer(modifier = Modifier.padding(top = 15.dp))

                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp)
                Spacer(modifier = Modifier.padding(top = 15.dp))

                HorizontalImageScroll()

            }

        }


    }

}



@Composable
fun HorizontalImage() {
    Box(
        modifier = Modifier
            .size(300.dp) // حجم الصندوق
            .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.container),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun HorizontalImageScroll() {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(10.dp)
    ) {
        repeat(3) {
            HorizontalImage()
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}
@Composable
fun RoundedTimeDisplayWithFill() {
    val totalTimeMillis = 16 * 60 * 1000L
    val progress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = totalTimeMillis.toInt(), easing = LinearEasing)
        )
    }

    Box(
        modifier = Modifier
            .size(width = 70.dp, height = 40.dp)
            .background(Color(0xFFE8F5E9)) // Background color
    ) {
        // Animated Fill
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRoundRect(
                color = Color(0xFF4CAF50),
                topLeft = Offset(0f, 0f),
                size = Size(size.width * progress.value, size.height),
                cornerRadius = CornerRadius(25.dp.toPx(), 25.dp.toPx())
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            Text(
                text = "16 m",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )
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
        Spacer(modifier = Modifier.padding(bottom = 10.dp))

        HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp)

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideDetailsCard() {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,

    )
    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier

                .fillMaxWidth()
                .fillMaxHeight(.5f)
             ,
            containerColor = Color.White,
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            TripDetailsBottomSheetContent()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()

            .height(130.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
            .border(
                width = 2.dp,
                color = Color.Gray, // Set the border color
                shape = RoundedCornerShape(8.dp) // Optional: Rounded corners
            )
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            // Top Section with Title
            Text(
                text = "تفاصيل المشوار",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.End),
                textAlign = TextAlign.Right
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Message Row
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.padding(bottom = 5.dp).clickable { showBottomSheet = true },
                        painter = painterResource(id = R.drawable.ic_more),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Text Message
                Text(
                    text = "الالتقاء في نقطة الالتقاء عند مجرى السيل",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                    textAlign = TextAlign.Right,
                    modifier = Modifier.weight(1f)
                )
            }

        }
    }
}


@Composable
fun TripDetailsBottomSheetContent(){
    Column (

        modifier = Modifier.padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            "Trip Details",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.padding(bottom = 10.dp))

        HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp)

        Spacer(modifier = Modifier.weight(1f))

        fromLocationToDestination()

        Spacer(modifier = Modifier.weight(1f))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

            ) {
            Text(" Share your trip status", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {},
                modifier = Modifier
                    .border(

                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(23.dp)

                    )
                 , colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)


            ) {
                Text(
                    "Share",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }

        }
        Spacer(modifier = Modifier.padding(bottom = 10.dp))

        HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth(.9f)


                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            shape = RoundedCornerShape(8.dp)

        ) {
            androidx.compose.material3.Text(
                text = "Cancel Trip",
                color = Color.Red,
                fontSize = 18.sp,

                )
        }
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth(.9f)


                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            shape = RoundedCornerShape(8.dp)

        ) {
            androidx.compose.material3.Text(
                text = "Done",
                color = Color.White,
                fontSize = 18.sp,

                )
        }

        Spacer(modifier = Modifier.padding(bottom = 10.dp))

    }
}



