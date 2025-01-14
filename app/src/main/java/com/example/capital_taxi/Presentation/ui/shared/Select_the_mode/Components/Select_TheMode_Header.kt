package com.example.capital_taxi.Presentation.ui.shared.Select_the_mode.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R

@Composable
fun SelectTheModeHeader() {
    Column(
        modifier = Modifier.padding(start = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {


        // Updated to use string resources
        Text(
            text = stringResource(R.string.select_the_mode_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            text = stringResource(R.string.mode_instruction),
            fontSize = 20.sp,
            fontWeight = FontWeight.W600
        )


    }
}
