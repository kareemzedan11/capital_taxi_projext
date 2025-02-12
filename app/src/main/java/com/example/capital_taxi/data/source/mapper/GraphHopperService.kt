package com.example.capital_taxi.data.source.mapper

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.capital_taxi.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import io.ktor.websocket.Frame
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class RouteResponse(val paths: List<Path>)

data class Path(val points: String, val distance: Double, val time: Long)

interface GraphHopperService {
    @GET("route")
    suspend fun getRoute(
        @Query("point") start: String,
        @Query("point") end: String,
        @Query("vehicle") vehicle: String = "car",
        @Query("locale") locale: String = "en",
        @Query("key") apiKey: String
    ): RouteResponse
}

fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://graphhopper.com/api/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun getGraphHopperService(): GraphHopperService {
    return getRetrofit().create(GraphHopperService::class.java)
}

suspend fun getRouteFromGraphHopper(startLatLng: LatLng, endLatLng: LatLng): RouteResponse {
    val service = getGraphHopperService()
    return service.getRoute(
        start = "${startLatLng.latitude},${startLatLng.longitude}",
        end = "${endLatLng.latitude},${endLatLng.longitude}",
        apiKey = "71ab0bb4-9572-4423-ab8f-332deb2827a7"
    )
}@Composable
fun MapScreen() {
    val context = LocalContext.current
    Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))

    var route by remember { mutableStateOf<RouteResponse?>(null) }
    var userLocation by remember { mutableStateOf<LatLng?>(null) }
    val startLatLng = LatLng(40.748817, -73.985428) // Example: Start point
    val endLatLng = LatLng(40.730610, -73.935242)   // Example: End point

    val mapView = remember { MapView(context).apply { setTileSource(TileSourceFactory.MAPNIK) } }
    val controller = mapView.controller

    // Fetch user's current location
    LaunchedEffect(true) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    userLocation = LatLng(it.latitude, it.longitude)
                    Log.d("MapScreen", "User location: $userLocation")
                } ?: Log.e("MapScreen", "Unable to fetch location")
            }.addOnFailureListener {
                Log.e("MapScreen", "Failed to get location", it)
            }
        } else {
            Log.e("MapScreen", "Location permission denied")
        }
    }

    // Auto-zoom to user location when updated
    LaunchedEffect(userLocation) {
        userLocation?.let { loc ->
            controller.animateTo(GeoPoint(loc.latitude, loc.longitude))
            controller.setZoom(18.0)  // تقليل الزووم إلى مستوى قريب جدًا
        }
    }

    // Fetch route from API
    LaunchedEffect(true) {
        try {
            route = getRouteFromGraphHopper(startLatLng, endLatLng)
        } catch (e: Exception) {
            Log.e("MapScreen", "Error fetching route", e)
        }
    }

    // Draw polyline for route
    route?.paths?.firstOrNull()?.let { path ->
        val points = decodePolyline(path.points)
        val polyline = Polyline().apply {
            setPoints(points.map { GeoPoint(it.latitude, it.longitude) })
        }
        mapView.overlayManager.add(polyline)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize()
        )

        Button(
            onClick = {
                userLocation?.let { loc ->
                    controller.animateTo(GeoPoint(loc.latitude, loc.longitude))
                    controller.setZoom(18.0)  // زووم أكبر ليكون أقرب للمكان
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Frame.Text("Zoom to My Location")
        }
    }
}


fun decodePolyline(encoded: String): List<LatLng> {
    val poly = mutableListOf<LatLng>()
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

        poly.add(LatLng(lat / 1E5, lng / 1E5))
    }
    return poly
}
