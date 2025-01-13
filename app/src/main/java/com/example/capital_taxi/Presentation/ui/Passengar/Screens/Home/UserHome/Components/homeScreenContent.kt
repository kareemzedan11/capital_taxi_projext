package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import TopBar
import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R
import drawerContent
import kotlinx.coroutines.launch
import androidx.compose.material.rememberBottomSheetScaffoldState

import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capital_taxi.Helper.PartialBottomSheet
import com.example.capital_taxi.Helper.PermissionViewModel
import com.example.capital_taxi.Helper.checkLocationPermission
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components.TripDetailsForDriver
import com.google.android.gms.location.LocationServices
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
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

    LaunchedEffect(Unit) {
        // Get the last known location
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {

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
                ModalDrawerSheet {
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
                            MapSection(navController = navController)
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

                        // TripDetailsLiveTracker()
                       // RideDetailsBottomSheetContent()
                       // TripRatingDialog()

                        // Use the current state to check if location is enabled and permission granted
                        if (currentIsLocationEnabled.value && currentIsLocationGranted.value) {
                            if (!isConfirmed) {
                                PickupWithDropOffButtons(
                                    navController = navController,
                                    locationName = locationName
                                )
                            } else if (isConfirmed) {
                                confirmPickup(onclick = { isSearch = true })

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

                FindDriverCard(onclick = { isConfirmed = true })
            }
        }
    }
}

@Composable
private fun FindDriverCard(onclick: () -> Unit) {


    var showBottomSheet by remember { mutableStateOf(false) }

    PartialBottomSheet(
        showBottomSheet = showBottomSheet,
        onDismissRequest = { showBottomSheet = false }) {

        PaymentMethodContent()


    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            elevation = CardDefaults.elevatedCardElevation(10.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    Arrangement.Start,
                    Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(26.dp)
                            .clickable { showBottomSheet = true },
                        painter = painterResource(R.drawable.dollar),
                        tint = Color.Unspecified,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = { onclick() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(
                                R.color.primary_color
                            )
                        ),
                        modifier = Modifier
                            .width(200.dp)
                            .height(50.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.Find_a_driver),
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
//
//                                Icon(
//                                    modifier = Modifier.size(26.dp),
//                                    painter = painterResource(R.drawable.tools),
//                                    tint = Color.Black,
//                                    contentDescription = null
//                                )
                }
            }
        }
    }
}


@Composable
fun PaymentMethodContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.Payment_method),
            fontSize = 24.sp,

            fontWeight = FontWeight.W700
        )
        Divider(
            thickness = 2.dp,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        PaymentCard()
    }
}


@Composable
fun PaymentCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)

            .border(1.dp, color = Color.Gray),
        elevation = CardDefaults.elevatedCardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(colorResource(R.color.secondary_color)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.dollar),
                    contentDescription = "Cash",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(26.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                stringResource(R.string.Cash),

                fontSize = 18.sp,
                fontWeight = FontWeight.W600
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.selected),
                contentDescription = "Selected",
                tint = Color.Unspecified,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}
