package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import IntercityCard
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun TripCars(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)

            .border(5.dp, Color.Transparent, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))

            .verticalScroll(rememberScrollState())


    ) {

        repeat(4) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .border(
                        5.dp,
                        Color.Transparent,
                        RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )

            ) {

                IntercityCard()
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}