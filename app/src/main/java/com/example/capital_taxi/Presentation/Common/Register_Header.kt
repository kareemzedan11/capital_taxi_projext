package com.example.capital_taxi.Presentation.Common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R


@Composable
fun RegisterHeader() {
    Text(
        text = stringResource(id = R.string.sign_up),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    Text(
        text = stringResource(id = R.string.create_user),
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}