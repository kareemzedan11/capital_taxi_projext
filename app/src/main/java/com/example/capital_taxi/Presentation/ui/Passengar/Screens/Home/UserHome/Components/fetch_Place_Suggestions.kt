package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

fun fetchPlaceSuggestions(
    query: String,
    onResult: (List<String>) -> Unit
) {
    val url = "https://nominatim.openstreetmap.org/search?format=json&q=$query"

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            connection.requestMethod = "GET"
            connection.setRequestProperty("User-Agent", "YourAppName") // مطلوب في Nominatim

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().readText()
                Log.d("API Response", response) // Debugging
                val jsonArray = JSONArray(response)
                val suggestions = mutableListOf<String>()

                for (i in 0 until jsonArray.length()) {
                    val displayName = jsonArray.getJSONObject(i).getString("display_name")
                    suggestions.add(displayName)
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

