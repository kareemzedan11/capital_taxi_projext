package com.example.capital_taxi.domain

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Header
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface TripApiService {

    @POST("api/trips/calculate-fare")
    suspend fun calculateFare(
        @Body request: Map<String, Any>
    ): Response<FareResponse>

    @POST("api/trips/create")
    suspend fun createTrip(
        @Header("Authorization") token: String,
        @Body request: Map<String, Any>
    ): Response<TripResponse>

    @PUT("api/trips/assign-driver")
    suspend fun assignDriver(
        @Header("Authorization") token: String,
        @Body request: Map<String, String>
    ): Response<GenericResponse>

    @PUT("api/trips/update-status")
    suspend fun updateTripStatus(
        @Header("Authorization") token: String,
        @Body request: Map<String, String>
    ): Response<GenericResponse>

    @GET("api/trips/user-trips")
    suspend fun getUserTrips(
        @Header("Authorization") token: String
    ): Response<List<TripResponse>>

    @GET("api/trips/driver-trips")
    suspend fun getDriverTrips(
        @Header("Authorization") token: String
    ): Response<List<TripResponse>>
}

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:5000/"

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    val apiService: TripApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TripApiService::class.java)
    }
}

data class FareResponse(
    val fare: Double,
    val paymentMethod: String,
    val route: Route
)

data class Route(
    val distance: Double,
    val duration: String
)
data class TripResponse(
    val message: String,
    val trip: Trip
)

data class Trip(
    val id: String,
    val user: String,
    val origin: String, // Store as String
    val destination: String, // Store as String
    val paymentMethod: String,
    val fare: Double,
    val distanceInKm: Double,
    val status: String,
    val driver: Driver?,
    val createdAt: String,
    val updatedAt: String
)

data class Location(
    val lat: Double,
    val lng: Double
)

data class Driver(
    val name: String,
    val phone: String
)

data class GenericResponse(
    val message: String
)

fun calculateFare(
    origin: Location,
    destination: Location,
    paymentMethod: String,
    coroutineScope: CoroutineScope,
    onSuccess: (FareResponse) -> Unit,
    onError: (String) -> Unit
) {
    coroutineScope.launch {
        try {
            val response = RetrofitClient.apiService.calculateFare(
                mapOf(
                    "origin" to mapOf("lat" to origin.lat, "lng" to origin.lng),
                    "destination" to mapOf("lat" to destination.lat, "lng" to destination.lng),
                    "paymentMethod" to paymentMethod
                )
            )
            if (response.isSuccessful && response.body() != null) {
                onSuccess(response.body()!!)
            } else {
                onError("Failed to calculate fare: ${response.message()}")
            }
        } catch (e: Exception) {
            onError("Error: ${e.message}")
        }
    }
}

fun createTrip(
    userId: String,
    origin: Location,
    destination: Location,
    paymentMethod: String,
    fare: Double,
    distance: Double,
    token: String,
    coroutineScope: CoroutineScope,
    onSuccess: (TripResponse) -> Unit,
    onError: (String) -> Unit
) {
    coroutineScope.launch {
        try {
            val response = RetrofitClient.apiService.createTrip(
                "Bearer $token",
                mapOf(
                    "id" to userId,
                    "origin" to mapOf("lat" to origin.lat, "lng" to origin.lng),
                    "destination" to mapOf("lat" to destination.lat, "lng" to destination.lng),
                    "paymentMethod" to paymentMethod,
                    "fare" to fare,
                    "distanceInKm" to distance
                )
            )
            if (response.isSuccessful && response.body() != null) {
                onSuccess(response.body()!!)
            } else {
                onError("Failed to create trip: ${response.message()}")
            }
        } catch (e: Exception) {
            onError("Error: ${e.message}")
        }
    }
}

fun assignDriver(
    tripId: String,
    driverId: String,
    token: String,
    coroutineScope: CoroutineScope,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit
) {
    coroutineScope.launch {
        try {
            val response = RetrofitClient.apiService.assignDriver(
                "Bearer $token",
                mapOf("tripId" to tripId, "driverId" to driverId)
            )
            if (response.isSuccessful && response.body() != null) {
                onSuccess(response.body()!!.message)
            } else {
                onError("Failed to assign driver: ${response.message()}")
            }
        } catch (e: Exception) {
            onError("Error: ${e.message}")
        }
    }
}

fun updateTripStatus(
    tripId: String,
    status: String,
    token: String,
    coroutineScope: CoroutineScope,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit
) {
    coroutineScope.launch {
        try {
            val response = RetrofitClient.apiService.updateTripStatus(
                "Bearer $token",
                mapOf("tripId" to tripId, "status" to status)
            )
            if (response.isSuccessful && response.body() != null) {
                onSuccess(response.body()!!.message)
            } else {
                onError("Failed to update trip status: ${response.message()}")
            }
        } catch (e: Exception) {
            onError("Error: ${e.message}")
        }
    }
}
fun getUserTrips(
    token: String,
    coroutineScope: CoroutineScope,
    onSuccess: (List<Trip>) -> Unit,
    onError: (String) -> Unit
) {
    coroutineScope.launch {
        try {
            val response = RetrofitClient.apiService.getUserTrips("Bearer $token")
            if (response.isSuccessful && response.body() != null) {
                // Extract the list of Trip objects from the TripResponse objects
                val tripList = response.body()!!.mapNotNull { it.trip } // Use mapNotNull to filter out null trips

                if (tripList.isEmpty()) {
                    onError("No trips available")
                } else {
                    // Pass the list of trips to onSuccess
                    onSuccess(tripList)
                }
            } else {
                onError("Failed to fetch trips: ${response.message()}")
            }
        } catch (e: Exception) {
            onError("Error: ${e.message}")
        }
    }
}

fun getDriverTrips(
    token: String,
    coroutineScope: CoroutineScope,
    onSuccess: (List<Trip>) -> Unit,
    onError: (String) -> Unit
) {
    coroutineScope.launch {
        try {
            val response = RetrofitClient.apiService.getDriverTrips("Bearer $token")
            if (response.isSuccessful && response.body() != null) {
                val trips = response.body()!!.map { it.trip }
                onSuccess(trips)
            } else {
                onError("Failed to fetch driver trips: ${response.message()}")
            }
        } catch (e: Exception) {
            onError("Error: ${e.message}")
        }
    }
}

fun parseLocation(json: String): Location? {
    return try {
        Gson().fromJson(json, Location::class.java)
    } catch (e: Exception) {
        Log.e("parseLocation", "Failed to parse location: ${e.message}")
        null
    }}