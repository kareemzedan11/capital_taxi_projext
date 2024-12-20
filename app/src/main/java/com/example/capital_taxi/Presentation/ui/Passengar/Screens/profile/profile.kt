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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
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
                            .size(26.dp) // Adjust icon size as needed
                            .background(Color.White, CircleShape)
                    ) {
                        Icon(
                            modifier = Modifier .size(32.dp),

                            painter = painterResource(R.drawable.baseline_add_circle_outline_24), // Replace with your icon resource
                            contentDescription = "Upload Picture",
                            tint = Color((0XFF46B96C))
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
                            painter = painterResource(id = R.drawable.baseline_phone_24),
                            contentDescription = "phone Icon"
                        )
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { navController.navigate(Destination.UserHomeScreen.route)},
                    modifier = Modifier
                        .fillMaxWidth(.9f)


                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(Color(0XFF46C96B)),
                    shape = RoundedCornerShape(16.dp)

                ) {
                    Text(
                        text = "Save ",
                        fontSize = 18.sp,

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
