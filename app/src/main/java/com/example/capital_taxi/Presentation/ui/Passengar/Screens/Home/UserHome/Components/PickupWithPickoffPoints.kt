@file:Suppress("NAME_SHADOWING")

package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import IntercityCard
import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.capital_taxi.Helper.PermissionViewModel
import com.example.capital_taxi.Helper.checkLocationPermission
import com.example.capital_taxi.R
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.delay
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickupWithPickoffPoints(navController: NavController ) {
    val permissionViewModel: PermissionViewModel = viewModel()
    val context = LocalContext.current

    // Check permissions when screen loads
    LaunchedEffect(context) {
        checkLocationPermission(context, permissionViewModel)
    }

    // FusedLocationProviderClient to get current location
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    // Declare locationName as a state
    var locationName by remember { mutableStateOf("Fetching location...") }

    // Launch an effect to fetch the location
    LaunchedEffect(Unit) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val geocoder = Geocoder(context, Locale.getDefault())
                val addressList = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                locationName = if (addressList != null && addressList.isNotEmpty()) {
                    addressList[0].getAddressLine(0)
                } else {
                    "Unable to fetch location"
                }
            } ?: run {
                locationName = "Unable to fetch location"
            }
        }
    }
    val labelText = locationName
    var pickupPoint by remember { mutableStateOf(labelText.ifEmpty { "Pickup Point" }) }
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
                color = colorResource(R.color.secondary_color), shape = RoundedCornerShape(20.dp)
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
                        tint = colorResource(R.color.primary_color)
                    )
                },
                onClick = {},
                hint = stringResource(R.string.Pickup_Point),

                initialText = locationName,
                query = locationName,
                onQueryChanged = { locationName = it },
                onLocationSelected = { locationName = it },
                apiKey = stringResource(id = R.string.SignUp)
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
                            color = colorResource(R.color.primary_color),
                            shape = RoundedCornerShape(50)
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
                        tint = colorResource(R.color.primary_color)
                    )
                },
                onClick = {},
                initialText = stringResource(R.string.PickOff_Point),
                hint = stringResource(R.string.PickOff_Point),
                query = destinationPoint,
                onQueryChanged = { destinationPoint = it },
                onLocationSelected = { destinationPoint = it },
                apiKey = stringResource(id = R.string.SignUp)
            )
        }
    }
}












