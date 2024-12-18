package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PickupNowForMeUI() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OptionButtonWithMenu(
            icon = Icons.Default.AddCircle,

            text = "Pickup Now",
            onMenuClick = {   },
            showIcon = true

        )
        Spacer(modifier = Modifier.width(8.dp))
        OptionButtonWithMenu(
            icon = Icons.Default.Person,
            text = "For Me",
            onMenuClick = {  },
            showIcon = true // Show icon for "For Me"
        )
    }
}
