package com.example.capital_taxi.Presentation.Common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.capital_taxi.R

class Register_textFields

@Composable
fun All_Register_textFields(

) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }
    RegisterTextField(
        value = name,
        onValueChange = { name = it },
        label = stringResource(id = R.string.user_name),
        keyboardType = KeyboardType.Text
    )

    Spacer(modifier = Modifier.height(16.dp))

    RegisterTextField(
        value = email,
        onValueChange = { email = it },
        label = stringResource(id = R.string.email),
        keyboardType = KeyboardType.Email
    )

    Spacer(modifier = Modifier.height(16.dp))

    PasswordField(
        value = password,
        onValueChange = { password = it },
        label = stringResource(id = R.string.password),
        isVisible = passwordVisible,
        onVisibilityChange = { passwordVisible = !passwordVisible }
    )

    Spacer(modifier = Modifier.height(16.dp))

    PasswordField(
        value = confirmPassword,
        onValueChange = { confirmPassword = it },
        label = stringResource(id = R.string.confirm_password),
        isVisible = confirmPasswordVisible,
        onVisibilityChange = { confirmPasswordVisible = !confirmPasswordVisible }
    )

    Spacer(modifier = Modifier.height(16.dp))

    RegisterTextField(
        value = phone,
        onValueChange = { phone = it },
        label = stringResource(id = R.string.phone),
        keyboardType = KeyboardType.Phone
    )
}


