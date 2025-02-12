package com.example.capital_taxi.domain.shared

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import java.io.IOException

data class LocationData(
    val lat: Double,
    val lng: Double
)

data class TripRequest(
    val id: String,
    val origin: LocationData,   // ✅ Use objects instead of String
    val destination: LocationData,  // ✅ Use objects instead of String
    val paymentMethod: String,
    val fare: Double,
    val distanceInKm: Double
)


data class TripResponse(
    val message: String,
    val trip: TripDetails?
)

data class TripDetails(
    val id: String,
    val user: String,
    val origin: LocationData,
    val destination: LocationData,
    val paymentMethod: String,
    val fare: Double,
    val distanceInKm: Double,
    val status: String
)

interface TripApiService {
    @POST("api/trips/create")
    suspend fun requestTrip(
        @Header("Authorization") token: String,
        @Body request: TripRequest
    ): TripResponse
}

object RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000/") // Adjust the base URL
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()

    val apiService: TripApiService = retrofit.create(TripApiService::class.java)
}


class TripViewModel : ViewModel() {
    var responseMessage = mutableStateOf("")

    fun requestTrip(
        userId: String,
        origin: LocationData,
        destination: LocationData,
        paymentMethod: String,
        fare: Double,
        distanceInKm: Double,
        token: String
    ) {
        viewModelScope.launch {
            try {
                val request = TripRequest(
                    id = userId,
                    origin = origin,  // ✅ Pass object directly
                    destination = destination,  // ✅ Pass object directly
                    paymentMethod = paymentMethod,
                    fare = fare,
                    distanceInKm = distanceInKm
                )

                val response = RetrofitClient.apiService.requestTrip("Bearer $token", request)
                responseMessage.value = response.message
                println("Trip Request Success: ${response.message}")

            } catch (e: Exception) {
                responseMessage.value = "Error: ${e.message}"
                println("Unexpected Error: ${e.message}")
            }
        }
    }

}