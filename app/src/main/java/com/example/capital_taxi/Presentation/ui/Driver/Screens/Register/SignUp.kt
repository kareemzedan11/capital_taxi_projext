package com.example.capital_taxi.Presentation.ui.Driver.Screens.Register


import androidx.camera.core.impl.CameraConfig.RequiredRule
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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

import com.example.capital_taxi.R

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Register.Components.userSignupContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverSignUp(navController: NavController) {
    var name by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


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
                    .padding(paddingValues) // Respect padding from Scaffold
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                userSignupContent(navController)
            }
        }
    )
}
