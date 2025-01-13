package com.example.capital_taxi.Presentation.ui.Driver.Screens.Register.Components

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
import androidx.compose.ui.res.colorResource
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
fun userSignupContent(navController: NavController) {

    var name by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var Confirmpassword by remember { mutableStateOf("") }

    var isChecked by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    var Confirmpasswordvisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Logo
        // Title
        Text(

            text = stringResource(R.string.SignUp),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(

            text = stringResource(R.string.create_user),
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
                    tint =    colorResource(id = R.color.primary_color)
                ,
                    contentDescription = "person icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)

                )
            },
            label = { Text(stringResource(R.string.user_name)) },
            modifier = Modifier

                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Email TextField
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
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
                    tint =    colorResource(id = R.color.primary_color),
                    contentDescription = "Email icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)

                )
            },
            label = { Text(stringResource(R.string.Email_label)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password TextField with trailing icon
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
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
                    tint =    colorResource(id = R.color.primary_color),

                    contentDescription = "password icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)

                )
            },
            label = { Text(stringResource(R.string.password)) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible}) {
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

        Spacer(modifier = Modifier.height(24.dp))

        // Confirm Password TextField with trailing icon
        OutlinedTextField(
            value = Confirmpassword,
            onValueChange = { Confirmpassword = it },
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
                    tint =    colorResource(id = R.color.primary_color),
                    contentDescription = "password icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)

                )
            },
            label = { Text(stringResource(R.string.confirm_password) )},
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (Confirmpasswordvisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { Confirmpasswordvisible= !Confirmpasswordvisible }) {
                    Icon(
                        painter = painterResource(
                            if (Confirmpasswordvisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
                        ),
                        contentDescription = if (Confirmpasswordvisible) "Hide password" else "Show password"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Phone TextField with trailing icon
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
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
                    tint =    colorResource(id = R.color.primary_color),

                    contentDescription = "Phone icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)

                )
            },
            label = { Text(stringResource(R.string.phone)) },
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

                checked = isChecked,
                onCheckedChange = { isChecked= it },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = stringResource(R.string.terms_conditions),
                fontSize = 16.sp,
                modifier = Modifier.clickable {

                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {  navController.navigate( Destination.FaceValidation.route) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isChecked)   colorResource(id = R.color.primary_color) else Color.Gray // Change color based on checkbox state
            ),
            enabled = isChecked, // Enable the button only when the checkbox is checked
            shape = RoundedCornerShape(8.dp) // Rounded corners
        ) {
            Text(
                text = stringResource(R.string.Continue),
                fontSize = 18.sp,
                color = Color.White
            )
        }


        Spacer(modifier = Modifier.height(60.dp))


        Text(
            text = stringResource(R.string.OrSignUpWith),
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
                         colorResource(id = R.color.secondary_color),

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
                            colorResource(id = R.color.secondary_color),
                            shape = RoundedCornerShape(10.dp) // For rounded corners with 8.dp radius

                        ), contentAlignment = Alignment.Center

                ) {
                    Image(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        painter = painterResource(R.drawable.xicon),
                        contentDescription = "X Logo"
                    )
                }
                Spacer(Modifier.width(30.dp))


                Row {

                    Box(
                        modifier = Modifier
                            .size(50.dp)

                            .background(
                                colorResource(id = R.color.secondary_color),
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
                text = stringResource(R.string.already_have_account),
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(R.string.SignIn),
                color = colorResource(R.color.primary_color),
                fontSize = 18.sp,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
        }
    }}
