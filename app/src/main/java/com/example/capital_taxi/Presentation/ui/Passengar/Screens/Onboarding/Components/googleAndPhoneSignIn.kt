package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Onboarding.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R

@Composable
fun GoogleAndPhone(navController: NavController) {
    Column(
        Modifier

            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 20.dp)) {
        ElevatedButton(
            onClick = {
                navController.navigate("DriverLoginInScreen") {
                    popUpTo("GoogleAndPhone") { inclusive = true } // Removes the previous screen
                    launchSingleTop = true // Avoids duplicates in the back stack
                }
            },
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Yellow),
            shape = RoundedCornerShape(16.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                "Driver Mode",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        ElevatedButton(
            onClick = {
                navController.navigate("UserLoginInScreen")
            },
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Black),
            shape = RoundedCornerShape(16.dp)
        ) {

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                "Passenger Mode",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))


    }
}