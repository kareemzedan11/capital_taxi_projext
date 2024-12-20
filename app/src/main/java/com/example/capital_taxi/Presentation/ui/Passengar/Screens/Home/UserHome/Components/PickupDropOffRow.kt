package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PickupDropOffRow(
    iconRes: Int,
    text: String,
    onClick: () -> Unit
) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {


        Icon(
            modifier = Modifier.size(26.dp),
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color.Unspecified
        )



        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                    )
                }
            }
            // Underline: Draw a line at the bottom of the Button
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
                    .height(2.dp)
                    .align(Alignment.BottomCenter),
                color = Color.LightGray, // Underline color
                thickness = 2.dp
            )
        }
    }
}
