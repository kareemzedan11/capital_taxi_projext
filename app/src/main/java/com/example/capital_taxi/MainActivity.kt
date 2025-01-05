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
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components.DraggableIcon
import com.example.capital_taxi.Presentation.ui.screens.Onboarding.Components.GoogleAndPhone
import com.google.android.gms.auth.api.signin.GoogleSignIn

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider




class MainActivity : ComponentActivity() {

    private val multiplePermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val cameraGranted = permissions[Manifest.permission.CAMERA] ?: false
            val locationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false

            if (!cameraGranted) {
                Toast.makeText(this, "Camera permission is required to capture photos", Toast.LENGTH_SHORT).show()
            }

            if (!locationGranted) {
                Toast.makeText(this, "Location permission is required", Toast.LENGTH_SHORT).show()
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

            MaterialTheme(
                colorScheme = lightColors,
                content = {
                    val navController = rememberNavController()
                    AppNavGraph(navController = navController)
                }
            )
        }
    }


    private fun requestPermissions() {
        val permissionsToRequest = mutableListOf<String>()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.CAMERA)
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        if (permissionsToRequest.isNotEmpty()) {
            multiplePermissionsLauncher.launch(permissionsToRequest.toTypedArray())
        }
    }
}
