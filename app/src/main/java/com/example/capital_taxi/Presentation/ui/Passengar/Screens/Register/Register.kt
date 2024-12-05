package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Register


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardActions

import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Register.Components.userRegisterContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRegister(navController: NavController) {


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                title = { Text("Passenger Mode", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        // Box to center content and handle keyboard interaction
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .imePadding() // Ensures the padding is applied when the keyboard appears
        ) {
            // Column wrapped in vertical scroll to allow scrolling when keyboard is open
            userRegisterContent(

                navController
            )
        }
    }
}
