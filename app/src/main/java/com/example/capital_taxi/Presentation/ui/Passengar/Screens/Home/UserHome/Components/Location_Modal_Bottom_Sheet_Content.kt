package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.capital_taxi.Helper.PermissionViewModel
import com.example.capital_taxi.Helper.checkLocationPermission
import com.example.capital_taxi.R
import com.google.android.gms.location.LocationServices
import java.util.Locale
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.fillMaxSize

@Composable
fun LocationModalBottomSheetContent(navController: NavController) {
    val permissionViewModel: PermissionViewModel = viewModel()
    val context = LocalContext.current

    // Check permissions when screen loads
    LaunchedEffect(context) {
        checkLocationPermission(context, permissionViewModel)
    }

    val isLocationGranted by permissionViewModel.isLocationGranted.collectAsState()

    // Check if location service is enabled
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    // Remember updated state of location enabled and granted
    val currentIsLocationEnabled = rememberUpdatedState(isLocationEnabled)
    val currentIsLocationGranted = rememberUpdatedState(isLocationGranted)

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

    Column(
        modifier = Modifier
            .fillMaxSize()

            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.Arrange_your_trip),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        )

        PickupNowForMeUI(navController)
        PickupWithPickoffPoints(navController)

        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        MostVisitedPlaces(navController)
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            HorizontalDivider(
                modifier = Modifier,
                thickness = 2.dp
            )
        }
    }
}
