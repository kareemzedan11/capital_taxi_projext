package com.example.capital_taxi.Presentation.ui.Driver.Screens.Login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Login.Components.driverLoginContent

import com.example.capital_taxi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverLoginIn(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Driver Mode",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Use padding values to avoid overlapping the topBar
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                driverLoginContent(email, password, passwordVisible, navController)
            }
        }
    )
}
