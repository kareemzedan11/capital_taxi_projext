package com.example.capital_taxi.Presentation.ui.screens.new_password

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPasswordScreen(navController: NavController){

    var password by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()){

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            topBar = {
                TopAppBar(
                    title = { null },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Box(
                                modifier = Modifier

                                    .size(36.dp)
                                    .background(Color.Transparent)
                                    .border(4.dp, color = Color.Black, RoundedCornerShape(30.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier.size(26.dp),
                                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                                    contentDescription = "Back",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top) {
                    Text(
                        "Create New \n \nPassword",
                        fontWeight = FontWeight.W400,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        "This password should be different from previous password",
                        fontWeight = FontWeight.W400,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))


                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it.trim() },
                            label = { Text("New Password") },
                            placeholder = { Text("Enter your phone number") },
                            modifier = Modifier
                                .fillMaxWidth()

                                .background(Color.White.copy(alpha = .2f)),
                            singleLine = true,
                        )


                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { navController.navigate(Destination.OtpScreen.route) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_color)),
                        shape = RoundedCornerShape(8.dp)

                    ) {
                        Text(
                            text = "Save",
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.weight(2f))



                }
            }
        }
    }

     }
