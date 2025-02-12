package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import TopBar
import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import drawerContent
import kotlinx.coroutines.launch
import androidx.compose.material.rememberBottomSheetScaffoldState

import androidx.compose.material.BottomSheetScaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capital_taxi.Helper.PartialBottomSheet
import com.example.capital_taxi.Helper.PermissionViewModel
import com.example.capital_taxi.Helper.checkLocationPermission
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components.Trip_preparation.FindDriverCard
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components.Trip_preparation.PickupWithDropOffButtons
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components.Trip_request.searchAboutADriver
import com.example.capital_taxi.data.source.mapper.MapScreen

import com.example.capital_taxi.domain.shared.LocationData
import com.example.capital_taxi.domain.shared.TripViewModel
import com.google.android.gms.location.LocationServices
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun homeScreenContent(navController: NavController) {
    var isConfirmed by remember { mutableStateOf(false) }
    var isSearch by remember { mutableStateOf(false) }

    val permissionViewModel: PermissionViewModel = viewModel()
    val context = LocalContext.current

    // تأكد من التحقق من الصلاحية عند تحميل الشاشة
    LaunchedEffect(context) {
        checkLocationPermission(context, permissionViewModel)
    }

    val isLocationGranted by permissionViewModel.isLocationGranted.collectAsState()

    val scope = rememberCoroutineScope()

    // BottomSheetScaffoldState
    val bottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    // DrawerState
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val gesturesEnabled = drawerState.isOpen

    // Check if location service is enabled
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    // Remember updated state of location enabled and granted
    val currentIsLocationEnabled = rememberUpdatedState(isLocationEnabled)
    val currentIsLocationGranted = rememberUpdatedState(isLocationGranted)

    // FusedLocationProviderClient to get current location
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    var locationName by remember { mutableStateOf("Fetching location...") }
    var destinationLat by remember { mutableStateOf(0.0) }
    var destinationLng by remember { mutableStateOf(0.0) }
    val tripViewModel: TripViewModel = viewModel()
    LaunchedEffect(Unit) {
        // Get the last known location
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                destinationLat = it.latitude
                destinationLng = it.longitude

                val geocoder = Geocoder(context, Locale.getDefault())
                val addressList = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                locationName = if (addressList != null && addressList.isNotEmpty()) {
                    addressList[0].getAddressLine(0) // الحصول على العنوان الكامل
                } else {
                    "Unable to fetch location"
                }
            } ?: run {
                locationName = "Unable to fetch location"
            }
        }
    }

    var showBottomSheet by remember { mutableStateOf(false) }

    PartialBottomSheet(
        showBottomSheet = showBottomSheet,
        onDismissRequest = { showBottomSheet = false }) {
        PaymentMethodContent()
    }

    // Main Container
    Box(modifier = Modifier.fillMaxSize()) {
        // Drawer
        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = gesturesEnabled,
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier.fillMaxWidth(0.8f) // Set drawer width to 60% of screen
                ) {
                    drawerContent(navController)
                }
            }
        ) {
            BottomSheetScaffold(
                scaffoldState = bottomSheetState,
                sheetPeekHeight = if (isConfirmed) 200.dp else 500.dp,
                content = { padding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        if (isLocationGranted) {
                          //  MapSection(navController = navController)
                            MapScreen()
                        } else {
                            Text("Location permission is required to view the map.")
                        }

                        // TopBar and DraggableIcon
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.TopStart)
                        ) {
                            TopBar(
                                onOpenDrawer = {
                                    scope.launch {
                                        if (drawerState.isClosed) {
                                            drawerState.open()
                                        } else {
                                            drawerState.close()
                                        }
                                    }
                                },
                                navController = navController
                            )
                            DraggableIcon(navController = navController)
                        }
                    }
                },
                sheetContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .border(
                                width = 2.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(16.dp)
                            )
                    ) {
                        // Use the current state to check if location is enabled and permission granted
                        if (currentIsLocationEnabled.value && currentIsLocationGranted.value) {
                            if (!isConfirmed) {
                                PickupWithDropOffButtons(
                                    navController = navController,
                                    locationName = locationName
                                )
                            } else if (isConfirmed) {
                                confirmPickup(onclick = { isSearch = true })
                                // TripDetailsLiveTracker()
                            }
                            if (isSearch) {
                                isConfirmed = false
                                searchAboutADriver()
                            }
                        } else {
                            EnableLocationServices(
                                permissionViewModel = permissionViewModel,
                                context = context
                            )
                        }
                    }
                }
            )

            if (currentIsLocationEnabled.value && currentIsLocationGranted.value && !isConfirmed && !isSearch) {
                val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY3YWIxNWE3ZWEzMjJhMzg0OTIzODMxOSIsInJvbGUiOiJ1c2VyIiwiaWF0IjoxNzM5MzAyNTU4LCJleHAiOjE3MzkzODg5NTh9.iMJKNoKYy2ZeiWMmjBAs_iraOBd-jJ4Eh5KEVcLDY7E" // Fetch or pass the token

                FindDriverCard(onclick = {
                    val userId = "67ab15a7ea322a3849238319" // Replace with actual user ID
                    val origin = LocationData(30.0444, 31.2357) // Tahrir Square
                    val destination = LocationData(30.1219, 31.4056) // Cairo International Airport
                    val fare = 150.0 // Example fare
                    val distanceInKm = 20.5 // Example distance
                    val paymentMethod = "cash"


                    tripViewModel.requestTrip(userId, origin, destination, paymentMethod, fare, distanceInKm, token)


                    // Call your method to request the trip, inside composable scope

                    isConfirmed = true
                })
            }
        }
    }
}


