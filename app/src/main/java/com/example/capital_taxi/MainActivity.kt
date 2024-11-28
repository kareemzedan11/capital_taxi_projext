package com.example.capital_taxi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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
}
