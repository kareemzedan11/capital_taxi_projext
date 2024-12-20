package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


import android.content.Context

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.capital_taxi.R

import com.google.android.gms.location.LocationServices
import com.google.maps.android.compose.Polyline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


@Composable
fun GoogleMapsDesign(context: Context, modifier: Modifier = Modifier) {
    var userLocation by remember { mutableStateOf<LatLng?>(null) }
    val context = LocalContext.current

    // Initialize the camera position state
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 15f) // Default camera position
    }

    // Fetch user's location when composable is launched
    LaunchedEffect(Unit) {
        // Check for location permission
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val latLng = LatLng(location.latitude, location.longitude)
                    userLocation = latLng
                    // Update camera position to user's location
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
                } else {
                    Toast.makeText(context, "Unable to fetch location", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(context, "Failed to get location", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        // Render the Google Map
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                mapType = MapType.NORMAL,
                isMyLocationEnabled = true
            ),
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
                myLocationButtonEnabled = false
            )
        ) {
            // Add a marker for user's location
            userLocation?.let { location ->
                Marker(
                    state = MarkerState(position = location),
                    title = "Your Location",
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                )

                // Optional: Add a circle around user's location
                Circle(
                    center = location,
                    radius = 500.0,
                    fillColor = Color(0x2200FF00),
                    strokeColor = Color(0x2200FF00),
                    strokeWidth = 2f
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            // Box for custom location button with white background
            Box(
                modifier = Modifier
                    .size(56.dp) // Adjust the size of the button
                    .background(Color.White, shape = CircleShape) // White circular background
                    .border(2.dp, Color.White, shape = CircleShape) // Optional border
                    .clickable {
                        // Handle location button click (e.g., move to user's location)
                        userLocation?.let { location ->
                            cameraPositionState.position =
                                CameraPosition.fromLatLngZoom(location, 15f)
                        }
                    },
                contentAlignment = Alignment.Center // Center the icon inside the button
            ) {
                Icon(
                    painter = painterResource(R.drawable.getlocation), // Your custom location icon
                    contentDescription = "My Location",
                    modifier = Modifier.size(32.dp), // Adjust icon size
                    tint = Color.Unspecified // Icon color
                )
            }
        }

    }}
        @Composable
fun GoogleMapsWithSimulatedMovement(
    context: Context,
    origin: LatLng,
    destinations: List<LatLng>,
    apiKey: String
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.builder()
            .target(origin)
            .zoom(12f)
            .build()
    }

    // State to hold route polyline
    var routePoints by remember { mutableStateOf<List<LatLng>>(emptyList()) }
    var simulatedPosition by remember { mutableStateOf(origin) } // Marker position

    // Fetch directions when the composable launches
    LaunchedEffect(origin, destinations) {
        if (destinations.isNotEmpty()) {
            val allPoints = mutableListOf<LatLng>()
            var currentOrigin = origin
            for (destination in destinations) {
                val segmentPoints = fetchDirections(context, currentOrigin, destination, apiKey)
                if (segmentPoints.isNotEmpty()) {
                    allPoints.addAll(segmentPoints)
                    currentOrigin = destination
                }
            }
            routePoints = allPoints
        }
    }

    // Simulate movement
    LaunchedEffect(routePoints) {
        if (routePoints.isNotEmpty()) {
            for (point in routePoints) {
                simulatedPosition = point
                cameraPositionState.position = CameraPosition.builder()
                    .target(point)
                    .zoom(15f)
                    .build()
                kotlinx.coroutines.delay(500) // Adjust delay for smoothness
            }
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(mapType = MapType.NORMAL),
        uiSettings = MapUiSettings(zoomControlsEnabled = false)
    ) {
        // Draw origin marker
        Marker(
            state = MarkerState(position = origin),
            title = "Origin",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
        )

        // Draw markers for all destinations
        destinations.forEachIndexed { index, destination ->
            Marker(
                state = MarkerState(position = destination),
                title = "Destination ${index + 1}",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
            )
        }

        // Draw the simulated marker
        Marker(
            state = MarkerState(position = simulatedPosition),
            title = "Moving Position",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
        )

        // Draw polyline for the route
        if (routePoints.isNotEmpty()) {
            Polyline(
                points = routePoints,
                color = Color.Blue,
                width = 5f
            )
        }
    }
}
@Composable
fun SimulateMovement(context: Context, routePoints: List<LatLng>) {
    var currentPointIndex by remember { mutableStateOf(0) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.builder()
            .target(routePoints.firstOrNull() ?: LatLng(0.0, 0.0))
            .zoom(15f)
            .build()
    }

    LaunchedEffect(routePoints) {
        if (routePoints.isNotEmpty()) {
            for (i in routePoints.indices) {
                currentPointIndex = i
                cameraPositionState.position = CameraPosition.builder()
                    .target(routePoints[i])
                    .zoom(15f)
                    .build()
                delay(1000L) // Simulates movement every second
            }
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // Add moving marker
        if (routePoints.isNotEmpty()) {
            Marker(
                state = MarkerState(position = routePoints[currentPointIndex]),
                title = "Moving to Destination",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
            )
        }
    }
}

suspend fun fetchDirections(
    context: Context,
    origin: LatLng,
    destination: LatLng,
    apiKey: String
): List<LatLng> {
    return withContext(Dispatchers.IO) {
        try {
            val url =
                "https://maps.googleapis.com/maps/api/directions/json?origin=${origin.latitude},${origin.longitude}&destination=${destination.latitude},${destination.longitude}&key=$apiKey"
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            connection.requestMethod = "GET"

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().readText()
                val jsonObject = JSONObject(response)
                val routes = jsonObject.getJSONArray("routes")
                if (routes.length() > 0) {
                    val overviewPolyline =
                        routes.getJSONObject(0).getJSONObject("overview_polyline")
                    val encodedPoints = overviewPolyline.getString("points")
                    decodePoly(encodedPoints)
                } else {
                    emptyList()
                }
            } else {
                Toast.makeText(context, "Failed to fetch directions", Toast.LENGTH_SHORT).show()
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            emptyList()
        }
    }
}
fun decodePoly(encoded: String): List<LatLng> {
    val poly = ArrayList<LatLng>()
    var index = 0
    val len = encoded.length
    var lat = 0
    var lng = 0

    while (index < len) {
        var b: Int
        var shift = 0
        var result = 0
        do {
            b = encoded[index++].code - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lat += dlat

        shift = 0
        result = 0
        do {
            b = encoded[index++].code - 63
            result = result or (b and 0x1f shl shift)
            shift += 5
        } while (b >= 0x20)
        val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
        lng += dlng

        val p = LatLng(
            lat / 1E5,
            lng / 1E5
        )
        poly.add(p)
    }
    return poly
}

@Composable
fun MapSection(modifier: Modifier=Modifier) {
    val origin = LatLng(30.1508, 31.2357) // Example origin
    val destinations = listOf(
        LatLng(30.0444, 31.2357), // Destination 1
        LatLng(29.9827, 31.1682)  // Destination 2
    )
    val apiKey = "AIzaSyArzERc9xBRprPePwc4uTopBW9WMBymX74"

    GoogleMapsDesign(modifier = modifier, context = LocalContext.current)
}

