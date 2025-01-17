package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R


@Composable
fun Payment_trip_cost() {
    Row {
        Icon(
            modifier = Modifier.size(20.dp),
            tint = Color(0XFF46C96B),
            painter = painterResource(R.drawable.dollar),
            contentDescription = "cash"
        )
        Spacer(modifier = Modifier.padding(15.dp))

        Text(
            text = "32.5 EGP", fontSize = 20.sp, color = Color.Black
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = "Cash", fontSize = 20.sp, color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Change", fontSize = 18.sp, color = Color.Gray
        )
        Icon(
            tint = Color.Gray,
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "KeyboardArrowRight"
        )
    }
}