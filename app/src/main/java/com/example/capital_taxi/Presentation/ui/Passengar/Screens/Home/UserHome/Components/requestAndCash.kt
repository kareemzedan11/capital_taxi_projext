package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R

@Composable
fun requestAndCash(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp) // Fixed height for the bottom section
            .background(Color.White)
    ) {
        Column {
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(top = 20.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = RoundedCornerShape(30.dp),
                elevation = ButtonDefaults.buttonElevation(4.dp)
            ) {
                Row {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        tint = Color(0XFF46C96B),
                        painter = painterResource(R.drawable.dollar),
                        contentDescription = "cash"
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = "Cash",
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Change",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                    Icon(
                        tint = Color.Gray,
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "KeyboardArrowRight"
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(Color(0XFF46C96B)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Request",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}

