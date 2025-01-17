package com.example.capital_taxi.Presentation.ui.Passengar.Screens.TripHistory.Components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R

@Composable
fun Title() {
    Text(
        text = stringResource(id = R.string.Trip_History),
        modifier = Modifier

            .padding(top = 60.dp),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}
