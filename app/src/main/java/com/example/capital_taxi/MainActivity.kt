package com.example.capital_taxi

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.capital_taxi.Navigation.AppNavGraph
import com.example.capital_taxi.Presentation.ui.shared.Language.components.LanguagePreference
import updateLocale


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
        updateLocale(this , languageCode) // Dynamically applies the locale and layout direction


        requestPermissions()

        if ( LanguagePreference.getSavedLanguage(this) == "ar") {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        } else {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        }

        setContent {

            CompositionLocalProvider(
                LocalLayoutDirection provides if (languageCode == "ar") LayoutDirection.Rtl else LayoutDirection.Ltr
            ) {
                MaterialTheme(

                    content = {
                        val navController = rememberNavController()
                        AppNavGraph(navController = navController)
                    }
                )
            }
        }
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




/*
try {


        setContent {

            CompositionLocalProvider(
                LocalLayoutDirection provides if (languageCode == "ar") LayoutDirection.Rtl else LayoutDirection.Ltr
            ) {
                AppTheme(
                    darkTheme = isSystemInDarkTheme(), // أو أي طريقة لتحديد إذا كان الوضع داكن أو فاتح
                ) {
                    val navController = rememberNavController()
                    AppNavGraph(navController = navController)

                }
            }
        }}
catch (e: Exception) {
    Log.e("AppThemeError", "Error initializing theme", e)
}
 */