package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import IntercityCard
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickupWithPickoffPoints(navController: NavController) {
    var pickupPoint by remember { mutableStateOf("") }
    var destinationPoint by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    if (showBottomSheet) {
        ModalBottomSheet(modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }) {
            IntercityCard()
        }
    }
    // Box for Pickup and Pickoff Points
    Box(
        modifier = Modifier
            .background(
                color = Color(0XFFF2F2F2), shape = RoundedCornerShape(20.dp)
            )


            .padding(horizontal = 16.dp),

        ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Pickup Point Field
            LocationAutocompleteField(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Pickup Point",
                        tint = Color(0XFF46C96B)
                    )
                },
                label = "Pickup Point",
                query = pickupPoint,
                onQueryChanged = { pickupPoint = it },
                onLocationSelected = { pickupPoint = it },
                apiKey = stringResource(id = R.string.api_Key)
            )

            // Divider with Add Button
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp), // Reduced horizontal padding
                    color = Color.LightGray, thickness = 1.dp
                )
                IconButton(
                    onClick = {
                        showBottomSheet = true

                    }, modifier = Modifier
                        .background(
                            color = Color(0XFF46C96B), shape = RoundedCornerShape(50)
                        )
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Point",
                        tint = Color.White
                    )
                }
            }

            // Pickoff Point Field
            LocationAutocompleteField(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = "Pickoff Point",
                        tint = Color(0XFF46C96B)
                    )
                },
                label = "Pickoff Point",
                query = destinationPoint,
                onQueryChanged = { destinationPoint = it },
                onLocationSelected = { destinationPoint = it },
                apiKey = stringResource(id = R.string.api_Key)
            )
        }
    }
}

@Composable
fun startTripDesign() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(5.dp, Color.Transparent, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {
        // TripCars should take 80% of the height and align to the bottom
        TripCars(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .fillMaxHeight(0.5f) // Take up 80% of the screen height
                .align(Alignment.BottomCenter) // Align at the bottom of the Box
        )

        // The requestAndCash section stays at the bottom
        requestAndCash(modifier = Modifier.align(Alignment.BottomCenter)) // Stay fixed at the bottom
    }
}

@Composable
fun TripCars(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)

            .border(5.dp, Color.Transparent, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))

            .verticalScroll(rememberScrollState())


    ) {

        repeat(4) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .border(
                        5.dp,
                        Color.Transparent,
                        RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )

            ) {

                IntercityCard()
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}


@Composable
fun requestAndCash(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp) // Fixed height for the bottom section
            .background(Color.White)
    ) {
        Column {
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(top = 20.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = RoundedCornerShape(30.dp),
                elevation = ButtonDefaults.buttonElevation(4.dp)
            ) {
                Row {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        tint = Color(0XFF46C96B),
                        painter = painterResource(R.drawable.dollar),
                        contentDescription = "cash"
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = "Cash",
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Change",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                    Icon(
                        tint = Color.Gray,
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "KeyboardArrowRight"
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(Color(0XFF46C96B)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Request",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun confirmPickup() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.2f)
            .background(Color.Transparent)
            .border(
                width = 2.dp, // Border thickness
                color = Color.White, // Border color
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            ),
        contentAlignment = Alignment.Center
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
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .padding(top = 10.dp),
                    thickness = 6.dp,
                    color = Color(0XFFF2F2F2)
                )

                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(Color(0XFF46C96B)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Confirm Pickup",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}


@Composable
fun CustomProgressBarWithSlowFill() {
    // Total number of segments
    val totalSegments = 4

    // State to track the current segment being filled
    var currentSegment by remember { mutableStateOf(0) }

    // Animate the current segment index
    val animatedSegment by animateIntAsState(
        targetValue = currentSegment,
        animationSpec = tween(durationMillis = 1000) // Duration for each segment
    )

    // Control for iterative animation in the last segment (color fill)
    val infiniteTransition = rememberInfiniteTransition()
    val lastSegmentFillColor by infiniteTransition.animateColor(
        initialValue = Color.Transparent, // Starting with transparent
        targetValue = Color(0XFF46C96B), // Fully filled color
        animationSpec = infiniteRepeatable(
            animation = tween(
                5000,
                easing = LinearEasing
            ), // Slow fill animation (increased duration)
            repeatMode = RepeatMode.Reverse
        )
    )

    // Start the animation to fill each segment
    LaunchedEffect(Unit) {
        repeat(totalSegments) { segment ->
            delay(1500) // Delay before filling the next segment
            currentSegment = segment + 1
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp), // Height of the bar
        horizontalArrangement = Arrangement.spacedBy(4.dp) // Space between segments
    ) {
        repeat(totalSegments) { index ->
            Box(
                modifier = Modifier
                    .weight(1f) // Equal width for each segment
                    .fillMaxHeight()
                    .padding(horizontal = 3.dp)

                    .background(
                        when {
                            index < animatedSegment - 1 -> Color(0XFF46C96B) // Fully filled segments
                            index == animatedSegment - 1 -> lastSegmentFillColor // Animated last segment fill color
                            else -> Color(0XFFF2F2F2) // Remaining segments
                        },
                        shape = RoundedCornerShape(4.dp) // Rounded corners
                    )
            )
        }
    }
}

@Composable
fun driverViewing() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text("3", fontSize = 18.sp, fontWeight = FontWeight.W600)
        Spacer(modifier = Modifier.weight(1f))
        Text("drivers are viewing your request", fontSize = 18.sp, fontWeight = FontWeight.W600)

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.person),
            contentDescription = null,
            modifier = Modifier
                .size(26.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )


        Image(
            painter = painterResource(id = R.drawable.person),
            contentDescription = null,
            modifier = Modifier
                .size(26.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )


        Image(
            painter = painterResource(id = R.drawable.person),
            contentDescription = null,
            modifier = Modifier
                .size(26.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )




        Spacer(modifier = Modifier.weight(1f))

    }

}

@Composable
fun searchAboutADriver() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.06f)
                .background(Color(0XFFF2F2F2)), contentAlignment = Alignment.Center


        ) {

            driverViewing()

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f) // Ensure Box takes up 30% of height
                .background(Color.White)
                .border(
                    width = 2.dp, // Border thickness
                    color = Color.White, // Border color
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                ),
            contentAlignment = Alignment.Center
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
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .padding(top = 10.dp),
                        thickness = 6.dp,
                        color = Color(0XFFF2F2F2)
                    )

                    Spacer(modifier = Modifier.height(8.dp)) // Adjust spacer height if needed

                    Text(
                        "Ride requested. Please wait for the driver.",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )


                    Spacer(modifier = Modifier.height(8.dp)) // Adjust spacer height if needed

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(vertical = 10.dp),
                        thickness = 2.dp,
                        color = Color(0XFFF2F2F2)
                    )

                    // Use animated progress bar
                    CustomProgressBarWithSlowFill()
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Box(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    10.dp
                                )
                            )
                            .background(
                                Color.LightGray,

                                ),

                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Drop off time : 3:45am",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W600
                        )
                    }

                    val composition by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(R.raw.location)
                    )
                    val progress by animateLottieCompositionAsState(
                        composition = composition,
                        iterations = LottieConstants.IterateForever // Infinite loop
                    )
                    Box(modifier = Modifier.fillMaxWidth(.5f)) {

                        LottieAnimation(
                            composition = composition,
                            progress = progress,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                }
            }
        }
    }


}
















@Composable
fun tripTracker() {

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
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .padding(top = 10.dp),
                    thickness = 6.dp,
                    color = Color(0XFFF2F2F2)
                )

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
                            callAndChat()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun driverDetails() {
    Row (modifier = Modifier.padding(horizontal = 10.dp)){
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(R.drawable.person),
            contentDescription = "person Icon"
        )

        Column {

            Row {
                Spacer(modifier = Modifier.padding(5.dp))

                Text(
                    "Mohamed",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black.copy(alpha = .3f)
                )
                Spacer(modifier = Modifier.padding(5.dp))

                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)

            }
            Row {
                Spacer(modifier = Modifier.padding(5.dp))
                Icon(imageVector = Icons.Default.Star, contentDescription = null)


                Spacer(modifier = Modifier.padding(3.dp))
                Text(
                    "4.9",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black.copy(alpha = .3f)
                )
                Spacer(modifier = Modifier.padding(6.dp))
                Text(
                    "+2000 Trips",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black.copy(alpha = .3f)
                )


            }

        }


    }
}



@Composable
fun callAndChat() {
    Row(modifier = Modifier.padding(vertical = 10.dp)) {
        Box(
            modifier = Modifier
                .size(43.dp)
                .clip(CircleShape)
                .background(Color(0XFFc6c5c5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "call",
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Box(
            modifier = Modifier
                .size(43.dp)
                .clip(CircleShape)
                .background(Color(0XFFc6c5c5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.messageicon),
                contentDescription = "message icon",

            )
        }
    }
}
