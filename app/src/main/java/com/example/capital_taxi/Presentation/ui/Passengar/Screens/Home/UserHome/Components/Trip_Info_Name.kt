package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TripInfoRow(
    distance: String,
    duration: String,
    price: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Distance \n\n $distance",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        VerticalDivider(
            thickness = 2.dp,
            modifier = Modifier
                .height(70.dp)
                .padding(horizontal = 12.dp)
        )
        Text(
            text = "Duration \n\n $duration",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        VerticalDivider(
            thickness = 2.dp,
            modifier = Modifier
                .height(70.dp)
                .padding(horizontal = 12.dp)
        )
        Text(
            text = "Price \n\n $price",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}
