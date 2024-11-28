package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Start.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R

@Composable
fun StartButtonDesign(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextButton(
            onClick = { navController.navigate(Destination.OnboardingPager.route)  },
            modifier = Modifier
                .align(Alignment.BottomCenter) // Correct use of align
                .padding(vertical = 60.dp, horizontal = 50.dp)
                .border(1.dp, Color.Transparent, RoundedCornerShape(16.dp))
                .width(320.dp)
                .height(75.dp),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Start Now",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    contentDescription = null,
                    painter = painterResource(R.drawable.baseline_arrow_right_alt_24)
                )
            }
        }
    }
}
