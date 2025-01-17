package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Register.Components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.capital_taxi.Presentation.Common.All_Register_textFields
import com.example.capital_taxi.Presentation.Common.AlreadyHaveAccount
import com.example.capital_taxi.Presentation.Common.RegisterHeader
import com.example.capital_taxi.Presentation.Common.SignUpButton
import com.example.capital_taxi.Presentation.Common.TermsAndConditionsCheckbox
import com.example.capital_taxi.Presentation.Common.userMediaLoginOption
import com.example.capital_taxi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRegisterContent(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegisterHeader()
        Spacer(modifier = Modifier.height(16.dp))

        All_Register_textFields(

        )

        Spacer(modifier = Modifier.height(16.dp))

        TermsAndConditionsCheckbox(isChecked = isChecked, onCheckedChange = { isChecked = it })

        Spacer(modifier = Modifier.height(20.dp))

        SignUpButton(isEnabled = isChecked, onClick = { /* Handle Sign Up */ }, text = R.string.SignUp)

        Spacer(modifier = Modifier.padding(30.dp))

        userMediaLoginOption()

        Spacer(modifier = Modifier.padding(22.dp))

        AlreadyHaveAccount(navController)
    }
}