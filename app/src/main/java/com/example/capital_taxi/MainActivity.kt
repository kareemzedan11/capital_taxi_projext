package com.example.capital_taxi

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.capital_taxi.Navigation.AppNavGraph
import com.example.capital_taxi.Presentation.Theme.DarkBackground
import com.example.capital_taxi.Presentation.Theme.DarkOnPrimary
import com.example.capital_taxi.Presentation.Theme.DarkOnSecondary
import com.example.capital_taxi.Presentation.Theme.DarkPrimary
import com.example.capital_taxi.Presentation.Theme.DarkSecondary
import com.example.capital_taxi.Presentation.Theme.DarkSurface
import com.example.capital_taxi.Presentation.Theme.LightBackground
import com.example.capital_taxi.Presentation.Theme.LightOnPrimary
import com.example.capital_taxi.Presentation.Theme.LightOnSecondary
import com.example.capital_taxi.Presentation.Theme.LightPrimary
import com.example.capital_taxi.Presentation.Theme.LightSecondary
import com.example.capital_taxi.Presentation.Theme.LightSurface
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components.DraggableIcon
import com.example.capital_taxi.Presentation.ui.screens.Language.components.LanguagePreference
import updateLocale
import java.util.Locale


class MainActivity : ComponentActivity() {

    private val multiplePermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val cameraGranted = permissions[Manifest.permission.CAMERA] ?: false
            val locationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false

            if (!cameraGranted) {
                Toast.makeText(
                    this,
                    "Camera permission is required to capture photos",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (!locationGranted) {
                Toast.makeText(this, "Location permission is required", Toast.LENGTH_SHORT).show()
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val languageCode = LanguagePreference.getSavedLanguage(this)
        updateLocale(this, languageCode)

        requestPermissions()


        setContent {
            // Dynamically set the layout direction based on language preference
            if (languageCode == "ar") {
                // For Arabic, set the layout direction to RTL
                window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
            } else {
                // For English (or other languages), set the layout direction to LTR
                window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
            }
            val lightColors = lightColorScheme(
                primary = LightPrimary,
                secondary = LightSecondary,
                background = LightBackground,
                surface = LightSurface,
                onPrimary = LightOnPrimary,
                onSecondary = LightOnSecondary
            )

            val darkcolors = darkColorScheme(
                primary = DarkPrimary,
                secondary = DarkSecondary,
                background = DarkBackground,
                surface = DarkSurface,
                onPrimary = DarkOnPrimary,
                onSecondary = DarkOnSecondary
            )

            MaterialTheme(
                colorScheme = lightColors,
                content = {
                    val navController = rememberNavController()
                    AppNavGraph(navController = navController)
                }
            )
        }
    }

    // Function to handle language change
    fun OnLanguageSelected(languageCode: String) {
        // Save the new language to preferences
        LanguagePreference.saveLanguage(this, languageCode)

        // Update the locale and layout direction
        updateLocale(this, languageCode)

        // Dynamically set the layout direction based on selected language
        if (languageCode == "ar") {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        } else {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }

        // Recreate activity to apply the changes
        recreate()
    }
    private fun requestPermissions() {
        val permissionsToRequest = mutableListOf<String>()

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(Manifest.permission.CAMERA)
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsToRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        if (permissionsToRequest.isNotEmpty()) {
            multiplePermissionsLauncher.launch(permissionsToRequest.toTypedArray())
        }
    }
}
