package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


fun fetchPlaceSuggestions(
    query: String,
    apiKey: String,
    onResult: (List<String>) -> Unit
) {
    val url = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=$query&key=$apiKey"
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            connection.requestMethod = "GET"

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().readText()
                Log.d("API Response", response) // Log the API response for debugging
                val jsonObject = JSONObject(response)
                val predictions = jsonObject.getJSONArray("predictions")
                val suggestions = mutableListOf<String>()
                for (i in 0 until predictions.length()) {
                    val description = predictions.getJSONObject(i).getString("description")
                    suggestions.add(description)
                }
                withContext(Dispatchers.Main) {
                    onResult(suggestions)
                }
            } else {
                withContext(Dispatchers.Main) {
                    onResult(emptyList())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                onResult(emptyList())
            }
        }
    }
}
