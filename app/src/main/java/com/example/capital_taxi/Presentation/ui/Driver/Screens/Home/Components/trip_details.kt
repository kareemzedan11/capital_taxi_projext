package com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components

import androidx.compose.runtime.Composable
import TopBar
import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R
import drawerContent
import kotlinx.coroutines.delay


@Composable
fun tripDetailsCard(light: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {

            var blinkState by remember { mutableStateOf(true) }

            LaunchedEffect(Unit) {
                while (true) {
                    blinkState = !blinkState
                    delay(700L)
                }
            }

            if (light == true) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(.7f)
                        .fillMaxWidth()

                        .background(
                            color = if (blinkState) Color.Gray else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Comfort",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray
                )
                Text(
                    text = "129.90 EGP",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Comprehensive Graphical Service",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                var progress by remember { mutableStateOf(1f) }

                LaunchedEffect(Unit) {
                    val totalDuration = 30000L
                    val frameDuration = 16L
                    while (progress > 0f) {
                        progress -= frameDuration.toFloat() / totalDuration
                        delay(frameDuration)
                    }
                    progress = 0f
                }

                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color(0XFF46C96B),
                    Color(0XFFF2F2F2)
                )


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    androidx.compose.material.Icon(
                        modifier = Modifier.size(26.dp),
                        painter = painterResource(R.drawable.person),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )


                    Spacer(modifier = Modifier.width(5.dp))


                    Text(
                        text = "4.5",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                    horizontalAlignment = Alignment.Start

                ) {

                    RidePointDetails(
                        Locationicon = R.drawable.circle,
                        Destinationicon = R.drawable.travel,
                        LocationText = "Cairo",
                        DestinationText = "Alex",
                        distance1 = "3m (1.2KM)",
                        distance2 = "45m (20KM)",
                        isDestance = true,
                        onClick = { })

                }

                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color(0XFF46C96B))
                ) {
                    Text(text = "Accept Trip", color = Color.White, fontSize = 16.sp)
                }
                HorizontalDivider(
                    thickness = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                )
                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    repeat(7) {
                        Button(
                            onClick = { /* تنفيذ الرحلة */ },
                            modifier = Modifier
                                .padding(horizontal = 5.dp),
                            colors = ButtonDefaults.buttonColors(
                                Color.Transparent // بدون خلفية
                            ),
                            border = BorderStroke(1.dp, Color(0XFF46C96B)) // لون الحدود
                        ) {
                            Text(
                                text = "150 EGP",
                                color = Color(0xFF46C96B), // لون النص
                                fontSize = 16.sp
                            )
                        }


                    }

                }
            }
        }
    }
}

@Composable
fun RidePointDetails(
    distance1: String? = null,
    distance2: String? = null,
    isDestance: Boolean,
    Locationicon: Int,
    Destinationicon: Int,
    onClick: () -> Unit,
    LocationText: String,
    DestinationText: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start

        ) {

            Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically){

                androidx.compose.material.Icon(
                    modifier = Modifier.size(26.dp),
                    painter = painterResource(Locationicon),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                if (!isDestance) {

                    Button(
                        onClick = onClick,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            androidx.compose.material.Text(
                                text = LocationText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W600,
                            )
                        }
                    }
                }
                if (isDestance) {
                    Column {


                        distance1?.let {
                            Text(
                                modifier = Modifier.padding(horizontal = 10.dp),
                                text = it,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }




                        Button(
                            onClick = onClick,
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                androidx.compose.material.Text(
                                    text = LocationText,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W600,
                                )
                            }
                        }

                    }

                }

            }
            Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically){


            androidx.compose.material.Icon(
                    modifier = Modifier.size(26.dp),
                    painter = painterResource(Destinationicon),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                if (isDestance == false) {

                    Button(
                        onClick = onClick,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            androidx.compose.material.Text(
                                text = DestinationText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W600,
                            )
                        }
                    }
                }
                if (isDestance) {

                    Column {

                        if (isDestance) {
                            distance2?.let {
                                Text(
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    text = it,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                        }



                        Button(
                            onClick = onClick,
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                androidx.compose.material.Text(
                                    text = DestinationText,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W600,
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}
