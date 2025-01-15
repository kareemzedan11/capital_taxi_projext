package com.example.capital_taxi.Presentation.ui.shared.Confirm_information.Components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R

// Title Text Composable
@Composable
fun TitleText() {
    Text(
        text = stringResource(R.string.confirm_information_title),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}




