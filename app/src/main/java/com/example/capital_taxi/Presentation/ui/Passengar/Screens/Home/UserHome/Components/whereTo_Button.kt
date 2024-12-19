package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R
import com.google.android.libraries.mapsplatform.transportation.consumer.model.Vehicle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhereToButton(navController: NavController) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()


        ) {
            DraggableBottomSheet(sheetContent = { DraggableBottomSheet_Content(navController) })
        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DraggableBottomSheet_Content(navController: NavController) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PickupWithDropOffButtons(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickupWithDropOffButtons(navController: NavController) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            LocationModalBottomSheetContent(navController = navController)
        }
    }

    var selectedVehicleIndex by remember { mutableStateOf(-1) } // Track selected item index

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Pickup Location Row
        PickupDropOffRow(
            iconRes = R.drawable.circle,
            text = "Select Pickup Location",
            onClick = { showBottomSheet = true }
        )

        // Vertical Divider
        repeat(9) {
            VerticalDivider(
                thickness = 2.dp,
                modifier = Modifier
                    .height(5.dp)
                    .padding(start = 12.dp)
            )
        }

        // Drop-Off Location Row
        PickupDropOffRow(
            iconRes = R.drawable.travel,
            text = "Select Drop-Off Location",
            onClick = { showBottomSheet = true }
        )

        Spacer(modifier = Modifier.padding(top = 15.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(vehicleOptions) { index, vehicle ->
                Box(
                    modifier = Modifier
                        .width(170.dp)
                        .height(150.dp)
                        .scale(if (selectedVehicleIndex == index) 1.16f else 1f) // Scale the selected item
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(20.dp),
                            clip = false
                        )
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable {
                            selectedVehicleIndex = index // Set the clicked item as selected
                        }
                ) {
                    Column {
                        // Top Box with Image
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .background(
                                    color = Color(0XFF46C96B),
                                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(vehicle.imageRes),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        // Bottom Box with Text
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .background(
                                    color = if (selectedVehicleIndex == index) Color(0XFF46C96B) else Color.White,
                                    shape = RoundedCornerShape(
                                        bottomStart = 20.dp,
                                        bottomEnd = 20.dp
                                    )
                                )
                        ) {
                            Column(
                                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = vehicle.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = if (selectedVehicleIndex == index) Color.White else Color.Black
                                )
                                Text(
                                    text = "£${vehicle.price}",
                                    fontSize = 18.sp,
                                    color = if (selectedVehicleIndex == index) Color.White else Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 15.dp))

        if (selectedVehicleIndex != -1) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(
                        color = Color(0XFFECECEC),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "Distance \n \n 2.4 miles",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    VerticalDivider(
                        thickness = 2.dp,
                        modifier = Modifier

                            .height(70.dp)
                            .padding(horizontal = 12.dp)
                    )
                    Text(
                        text = "Duration \n \n 15:02",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,

                        color = Color.Black
                    )
                    VerticalDivider(
                        thickness = 2.dp,
                        modifier = Modifier
                            .height(70.dp)
                            .padding(horizontal = 12.dp)
                    )
                    Text(
                        text = "Price \n \n £10.50",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,

                        color = Color.Black
                    )


                }
            }
            Spacer(modifier = Modifier.padding(top = 15.dp))

            Button(
                onClick = { /* Perform action */ },
                colors = ButtonDefaults.buttonColors(Color(0XFF46C96B)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = "Order Now",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}


@Composable
fun PickupDropOffRow(
    iconRes: Int,
    text: String,
    onClick: () -> Unit
) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {


        Icon(
            modifier = Modifier.size(26.dp),
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color.Unspecified
        )



        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                    )
                }
            }
            // Underline: Draw a line at the bottom of the Button
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
                    .height(2.dp)
                    .align(Alignment.BottomCenter),
                color = Color.LightGray, // Underline color
                thickness = 2.dp
            )
        }
    }
}
