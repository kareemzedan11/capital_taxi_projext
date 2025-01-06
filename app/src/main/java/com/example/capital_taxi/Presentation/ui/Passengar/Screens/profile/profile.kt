package com.example.capital_taxi.Presentation.ui.Passengar.Screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController) {
    // Initialize SharedPreferences
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

    // Retrieve the saved first name from SharedPreferences
    val savedFirstName = sharedPreferences.getString("user_name", "") ?: ""
    var firstName by remember { mutableStateOf(savedFirstName) }

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
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                title = {
                    Text(
                        "Profile Settings",
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
                // Top Image
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .size(120.dp)
                ) {
                    // Profile Picture
                    Image(
                        painter = painterResource(R.drawable.person),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(120.dp)
                            .background(Color.Gray, CircleShape)
                    )

                    // Icon overlay
                    IconButton(
                        onClick = {
                            // Handle file picker logic here
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(26.dp)
                            .background(Color.White, CircleShape)
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(R.drawable.baseline_add_circle_outline_24),
                            contentDescription = "Upload Picture",
                            tint = colorResource(R.color.primary_color),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Text Fields with Icons
                ProfileTextField(
                    label = "First Name",
                    value = firstName,
                    onValueChange = { firstName = it },
                    leadingIcon = {
                        Icon(
                            tint = colorResource(R.color.primary_color),
                            painter = painterResource(id = R.drawable.baseline_person_outline_24),
                            contentDescription = "First Name Icon"
                        )
                    }
                )
                ProfileTextField(
                    label = "Last Name",
                    value = lastName,
                    onValueChange = { lastName = it },
                    leadingIcon = {
                        Icon(
                            tint = colorResource(R.color.primary_color),
                            painter = painterResource(id = R.drawable.baseline_person_outline_24),
                            contentDescription = "Last Name Icon"
                        )
                    }
                )
                ProfileTextField(
                    label = "Email",
                    value = email,
                    onValueChange = { email = it },
                    leadingIcon = {
                        Icon(
                            tint = colorResource(R.color.primary_color),
                            painter = painterResource(id = R.drawable.baseline_email_24),
                            contentDescription = "Email Icon"
                        )
                    }
                )
                ProfileTextField(
                    label = "Phone",
                    value = phone,
                    onValueChange = { phone = it },
                    leadingIcon = {
                        Icon(
                            tint = colorResource(R.color.primary_color),
                            painter = painterResource(id = R.drawable.baseline_phone_24),
                            contentDescription = "Phone Icon"
                        )
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        // Save new information if needed or navigate to the next screen
                        navController.navigate(Destination.UserHomeScreen.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_color)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Save ",
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null // Optional leading icon
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = leadingIcon,
        modifier = Modifier
             .fillMaxWidth(0.9f)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp)
        ,
        textStyle = LocalTextStyle.current.copy(color = Color.Black),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color.Black
        )
    )
}
