package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Register.Components

import RegisterRequest
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.capital_taxi.Presentation.Common.All_Register_textFields
import com.example.capital_taxi.Presentation.Common.AlreadyHaveAccount
import com.example.capital_taxi.Presentation.Common.RegisterHeader
import com.example.capital_taxi.Presentation.Common.SignUpButton
import com.example.capital_taxi.Presentation.Common.TermsAndConditionsCheckbox
import com.example.capital_taxi.Presentation.Common.userMediaLoginOption
import com.example.capital_taxi.R


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
@Composable
fun UserRegisterContent(navController: NavController) {
    val context = LocalContext.current

    val name = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") } // إضافة متغير `username`
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val isChecked = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegisterHeader()
        Spacer(modifier = Modifier.height(16.dp))

        // تمرير القيم إلى الحقول مع `username`
        All_Register_textFields(
            name = name,
            username = username,
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            phone = phone
        )

        Spacer(modifier = Modifier.height(16.dp))

        TermsAndConditionsCheckbox(
            isChecked = isChecked.value,
            onCheckedChange = { isChecked.value = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        SignUpButton(
            isEnabled = isChecked.value,
            onClick = {
                Log.d("RegisterDebug", "Name: ${name.value}, Username: ${username.value}, Email: ${email.value}, Password: ${password.value}, Phone: ${phone.value}")

                registerUser(
                    name.value,
                    username.value,
                    email.value,
                    password.value,
                    phone.value,
                    navController,
                    context
                )

            },
            text = R.string.SignUp
        )

        Spacer(modifier = Modifier.padding(30.dp))

        userMediaLoginOption()

        Spacer(modifier = Modifier.padding(22.dp))

        AlreadyHaveAccount(navController)
    }
}

fun registerUser(
    name: String,
    username: String,
    email: String,
    password: String,
    phone: String,
    navController: NavController,
    context: Context,
    isDriver: Boolean = false // تعيين `false` افتراضيًا ليكون مستخدم عادي، وإذا أردت سائق استخدم `true`
) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val role = if (isDriver) "driver" else "user" // تحديد الدور تلقائيًا
            val request = RegisterRequest(name, username, email, password, phone, role)
            println("📤 Sending request: $request")

            val response = ApiClient.authApiService.registerUser(request)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val message = response.body()?.message ?: "Registration successful"
                    println("✅ Registration successful: $message")
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    navController.navigate("homeScreen")
                } else {
                    val errorResponse = response.errorBody()?.string() ?: "Unknown error"
                    println("❌ Registration failed: $errorResponse")
                    Toast.makeText(context, "Registration failed: $errorResponse", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                val errorMessage = e.localizedMessage ?: "An error occurred"
                println("❌ Exception Error: $errorMessage")
                Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
