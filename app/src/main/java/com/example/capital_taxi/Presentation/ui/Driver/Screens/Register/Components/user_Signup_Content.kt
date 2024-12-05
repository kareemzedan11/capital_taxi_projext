package com.example.capital_taxi.Presentation.ui.Driver.Screens.Register.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R



@Composable
fun userSignupContent(navController: NavController) {

    var name by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var Confirmpasswordvisible by remember { mutableStateOf(false) }
    var phonevisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Logo


        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = "Sign Up",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Create a new account",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(70.dp))



        OutlinedTextField(
            value =name,
            onValueChange = { name = it },
            label = { Text("UserName") },
            modifier = Modifier

                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )




        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value =email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier

                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )


        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(
                            if (passwordVisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
                        ),
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(
                            if (passwordVisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
                        ),
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (phonevisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { phonevisible = !phonevisible }) {
                    Icon(
                        painter = painterResource(
                            if (phonevisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
                        ),
                        contentDescription = if (phonevisible) "Hide password" else "Show password"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )



        Spacer(modifier = Modifier.height(24.dp))

        // Next Button
        Button(
            onClick = {   },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Continue")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Already Have an Account
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account?",
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Sign In",
                color = Color(0xFF6200EE),
                fontSize = 18.sp,
                modifier = Modifier.clickable { navController.popBackStack() }
            )
        }
    }
}