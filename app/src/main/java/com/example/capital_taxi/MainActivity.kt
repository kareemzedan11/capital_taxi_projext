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
import com.example.capital_taxi.Presentation.ui.screens.Onboarding.Components.GoogleAndPhone
import com.google.android.gms.auth.api.signin.GoogleSignIn

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : ComponentActivity() {
    // Location permission request launcher
    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (!isGranted) {
                Toast.makeText(this, "Location permission is required", Toast.LENGTH_SHORT).show()
            }
        }

    // Camera permission request launcher
    private val cameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (!isGranted) {
                Toast.makeText(this, "Camera permission is required to capture photos", Toast.LENGTH_SHORT).show()
            }

        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        requestPermissions()

        setContent {
            // Define your light color scheme
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

            // Apply the theme
            MaterialTheme(
                colorScheme = lightColors, // Pass the lightColorScheme here
                content = {
                    val navController = rememberNavController()
                    AppNavGraph(navController = navController)
                }

            )

        }

    }

    // Request both Location and Camera permissions
    private fun requestPermissions() {
        // Check and request camera permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
        // Check and request location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}
