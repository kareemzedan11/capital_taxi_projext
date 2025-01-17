package com.example.capital_taxi.Presentation.ui.Passengar.Screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import android.content.Context
import android.content.SharedPreferences
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.profile.Components.ProfileTextField
import com.example.capital_taxi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

    val savedFirstName = sharedPreferences.getString("user_name", "") ?: ""
    var UserName by remember { mutableStateOf(savedFirstName) }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = stringResource(R.string.back),
                            tint = Color.Black
                        )
                    }
                },
                title = {
                    Text(
                        stringResource(R.string.Account_Settings),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .size(120.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.person),
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                            .background(Color.Gray, CircleShape)
                    )
                    IconButton(
                        onClick = {
                            // Handle file picker logic
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(26.dp)
                            .background(Color.White, CircleShape)
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(R.drawable.baseline_add_circle_outline_24),
                            contentDescription = null,
                            tint = colorResource(R.color.primary_color),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                ProfileTextField(
                    label = stringResource(R.string.user_name),
                    value = UserName,
                    onValueChange = { UserName = it },
                    leadingIcon = {
                        Icon(
                            tint = colorResource(R.color.primary_color),
                            painter = painterResource(id = R.drawable.baseline_person_outline_24),
                            contentDescription = null
                        )
                    }
                )

                ProfileTextField(
                    label = stringResource(R.string.email),
                    value = email,
                    onValueChange = { email = it },
                    leadingIcon = {
                        Icon(
                            tint = colorResource(R.color.primary_color),
                            painter = painterResource(id = R.drawable.baseline_email_24),
                            contentDescription = null
                        )
                    }
                )
                ProfileTextField(
                    label = stringResource(R.string.phone),
                    value = phone,
                    onValueChange = { phone = it },
                    leadingIcon = {
                        Icon(
                            tint = colorResource(R.color.primary_color),
                            painter = painterResource(id = R.drawable.baseline_phone_24),
                            contentDescription = null
                        )
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { navController.navigate(Destination.UserHomeScreen.route) },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_color)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.save_button),
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
            }
        }
    )
}
