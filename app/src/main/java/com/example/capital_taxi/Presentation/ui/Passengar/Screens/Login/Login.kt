package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Login



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
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Login.Components.userLoginContent

import com.example.capital_taxi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserLogin(navController: NavController) {


    Scaffold(
        topBar = {
            TopAppBar(

                title = { Text("Passenger Mode", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            userLoginContent(   navController)
        }
    }
}
