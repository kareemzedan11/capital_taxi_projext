package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Register.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun userRegisterContent(



    navController: NavController
) {
    var name by remember { mutableStateOf("") }

    var email1 by remember { mutableStateOf("") }
    var password1 by remember { mutableStateOf("") }
    var phone1 by remember { mutableStateOf("") }
    var Confirmpassword1 by remember { mutableStateOf("") }
    var passwordVisible1 by remember { mutableStateOf(false) }
    var Confirmpasswordvisible1 by remember { mutableStateOf(false) }
    var phonevisible1 by remember { mutableStateOf(false) }

    var isChecked1 by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally// This ensures scrolling when keyboard appears
    ) {
        // Title
        Text(

            text = "Sign Up",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(

            text = "Create a new User",
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium
        )
        // First Name & Last Name Row
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(

            value = name,
            onValueChange = { name = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                // Setting the container background color to #F2F2F2
                containerColor = Color(0xFFF2F2F2), // Use the Color class to set the color
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,

                ),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    tint = Color.Gray,
                    contentDescription = "person icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)
                        .background(Color.Green)
                )
            },
            label = { Text("User Name") },
            modifier = Modifier

                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Email TextField
        OutlinedTextField(
            value = email1,
            onValueChange = { email1 = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                // Setting the container background color to #F2F2F2
                containerColor = Color(0xFFF2F2F2), // Use the Color class to set the color
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,

                ),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    tint = Color.Gray,
                    contentDescription = "Email icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)
                        .background(Color.Green)
                )
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password TextField with trailing icon
        OutlinedTextField(
            value = password1,
            onValueChange = { password1 = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                // Setting the container background color to #F2F2F2
                containerColor = Color(0xFFF2F2F2), // Use the Color class to set the color
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,

                ),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    tint = Color.Gray,
                    contentDescription = "passord icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)
                        .background(Color.Green)
                )
            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible1) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible1 = !passwordVisible1 }) {
                    Icon(
                        painter = painterResource(
                            if (passwordVisible1) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
                        ),
                        contentDescription = if (passwordVisible1) "Hide password" else "Show password"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Confirm Password TextField with trailing icon
        OutlinedTextField(
            value = Confirmpassword1,
            onValueChange = { Confirmpassword1 = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                // Setting the container background color to #F2F2F2
                containerColor = Color(0xFFF2F2F2), // Use the Color class to set the color
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,

                ),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    tint = Color.Gray,
                    contentDescription = "password icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)
                        .background(Color.Green)
                )
            },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (Confirmpasswordvisible1) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { Confirmpasswordvisible1 = !Confirmpasswordvisible1 }) {
                    Icon(
                        painter = painterResource(
                            if (Confirmpasswordvisible1) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
                        ),
                        contentDescription = if (Confirmpasswordvisible1) "Hide password" else "Show password"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Phone TextField with trailing icon
        OutlinedTextField(
            value = phone1,
            onValueChange = { phone1 = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                // Setting the container background color to #F2F2F2
                containerColor = Color(0xFFF2F2F2), // Use the Color class to set the color
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,

                ),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Phone,
                    tint = Color.Gray,
                    contentDescription = "Phone icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)
                        .background(Color.Green)
                )
            },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth(),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.align(alignment = Alignment.Start),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(

                checked = isChecked1,
                onCheckedChange = { isChecked1 = it },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "I agree to terms&conditions",
                fontSize = 16.sp,
                modifier = Modifier.clickable {

                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { /* Handle Sign Up */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isChecked1) Color(0XFF46C96B) else Color.Gray // Change color based on checkbox state
            ),
            enabled = isChecked1, // Enable the button only when the checkbox is checked
            shape = RoundedCornerShape(8.dp) // Rounded corners
        ) {
            Text(
                text = "Sign up",
                fontSize = 18.sp,
                color = Color.White // Ensure text color is always visible
            )
        }


        Spacer(modifier = Modifier.height(60.dp))


        Text(
            text = "Or sign up With",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Social Media Login Options
        Row {

            Box(
                modifier = Modifier
                    .size(50.dp)

                    .background(
                        Color(0XFFF2F2F2),
                        shape = RoundedCornerShape(10.dp) // For rounded corners with 8.dp radius

                    ), contentAlignment = Alignment.Center

            ) {
                Image(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape),
                    painter = painterResource(R.drawable.googleicon),
                    contentDescription = "Google Logo"
                )
            }

            Spacer(Modifier.width(30.dp))

            Row {

                Box(
                    modifier = Modifier
                        .size(50.dp)

                        .background(
                            Color(0XFFF2F2F2),
                            shape = RoundedCornerShape(10.dp) // For rounded corners with 8.dp radius

                        ), contentAlignment = Alignment.Center

                ) {
                    Image(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        painter = painterResource(R.drawable.twitter),
                        contentDescription = "Google Logo"
                    )
                }
                Spacer(Modifier.width(30.dp))


                Row {

                    Box(
                        modifier = Modifier
                            .size(50.dp)

                            .background(
                                Color(0XFFF2F2F2),
                                shape = RoundedCornerShape(10.dp) // For rounded corners with 8.dp radius

                            ), contentAlignment = Alignment.Center

                    ) {
                        Image(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape),
                            painter = painterResource(R.drawable.facelogo),
                            contentDescription = "Google Logo"
                        )
                    }


                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(60.dp))

        // SignUp Text
        Row {
            Text(
                text = "Already have an account ?",
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "SignIn",
                color = Color(0xFF6200EE),
                fontSize = 18.sp,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
        }
    }}
