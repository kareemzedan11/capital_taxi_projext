package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import IntercityCard
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickupWithDropOffButtons(
    navController: NavController,
    locationName: String? = "Select Pickup Location"
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight()
                .padding(top = 40.dp),
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            LocationModalBottomSheetContent(navController = navController)
        }
    }

    var selectedVehicleIndex by remember { mutableStateOf(-1) }
    var selectedVehicleName by remember { mutableStateOf("") } // Track the selected vehicle name

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.Where_are_you_going_today),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        PickupDropOffRow(
            iconRes = R.drawable.circle,
            text = locationName ?: stringResource(R.string.Select_Pickup_Location),
            onClick = { showBottomSheet = true }
        )

        repeat(9) {
            VerticalDivider(
                thickness = 2.dp,
                modifier = Modifier
                    .height(5.dp)
                    .padding(start = 12.dp)
            )
        }

        PickupDropOffRow(
            iconRes = R.drawable.travel,
            text = stringResource(R.string.Select_Drop_Off_Location),
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
                        .scale(if (selectedVehicleIndex == index) 1.16f else 1f)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp), clip = false)
                        .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                        .clickable {
                            selectedVehicleIndex = index
                            selectedVehicleName = vehicle.name // Update selected vehicle name
                        }
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .background(
                                    color = colorResource(R.color.primary_color),
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

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .background(
                                    color = if (selectedVehicleIndex == index)
                                        colorResource(R.color.primary_color)
                                    else Color.White,
                                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
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
            IntercityCard(text = selectedVehicleName) // Pass selected vehicle name
        }
    }
}
