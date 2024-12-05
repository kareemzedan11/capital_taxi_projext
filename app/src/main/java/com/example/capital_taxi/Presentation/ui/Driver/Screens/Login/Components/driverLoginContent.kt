package com.example.capital_taxi.Presentation.ui.Driver.Screens.Login.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R


@Composable
fun driverLoginContent(
    email: String,
    password: String,
    passwordVisible: Boolean,
    navController: NavController
) {
    var email1 = email
    var password1 = password
    var passwordVisible1 = passwordVisible
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = "Sign In  ",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(100.dp))

        // Email TextField
        OutlinedTextField(
            value = email1,
            onValueChange = { email1 = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password TextField with trailing icon
        OutlinedTextField(
            value = password1,
            onValueChange = { password1 = it },
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

        // Sign In Button
        Button(
            onClick = {
                println("clicked")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Sign In")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text = "Don't have an account?",
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "SignUp",
                color = Color(0xFF6200EE),
                fontSize = 18.sp,
                modifier = Modifier.clickable {
                   navController.navigate(Destination.driverSignUp.route)
                }
            )
        }

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Or Continue With",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(40.dp))

        Row {
            Image(
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp)
                    .clip(CircleShape),
                painter = painterResource(R.drawable.googleicon),
                contentDescription = "Email Logo"
            )

            Spacer(Modifier.width(40.dp))
            Image(
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp)
                    .clip(CircleShape)
                    .background(Color.Black),
                painter = painterResource(R.drawable.miscrologo),
                contentDescription = "Microsoft Logo"
            )

            Spacer(Modifier.width(40.dp))
            Image(
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp)
                    .clip(CircleShape)
                    .background(Color.Black),
                painter = painterResource(R.drawable.twitter),
                contentDescription = "Twitter Logo"
            )

            Spacer(Modifier.width(40.dp))
            Image(
                modifier = Modifier
                    .height(35.dp)
                    .width(35.dp)
                    .clip(CircleShape)
                    .background(Color.Black),
                painter = painterResource(R.drawable.facelogo),
                contentDescription = "Facebook Logo"
            )
        }
    }
}
