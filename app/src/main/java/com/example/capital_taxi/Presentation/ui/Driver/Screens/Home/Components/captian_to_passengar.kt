package com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app.ui.theme.CustomFontFamily
import com.example.app.ui.theme.responsiveTextSize
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun captainToPassengar(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()

                .height(130.dp)
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.clickable { navController.navigate(Destination.TripDetailsForDriver.route) },

                    contentDescription = null,

                    painter = painterResource(R.drawable.baseline_segment_24)
                )





                Spacer(modifier = Modifier.weight(1f))

                Column(

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.meet_passenger),
                        fontSize = responsiveTextSize(
                            fraction = 0.06f,
                            minSize = 14.sp,
                            maxSize = 18.sp
                        ),


                        fontFamily = CustomFontFamily,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(R.string.meet_before_time, "15:22"),
                        fontSize = responsiveTextSize(
                            fraction = 0.06f,
                            minSize = 12.sp,
                            maxSize = 16.sp
                        ),


                        fontFamily = CustomFontFamily,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )

                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}


