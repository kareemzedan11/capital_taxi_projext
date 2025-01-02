package com.example.capital_taxi.Presentation.ui.screens.Start


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Presentation.ui.screens.Start.Components.StartButtonDesign
import com.example.capital_taxi.R


@Composable
fun StartScreen(navController: NavController) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.secondary_color))

    ) {
Column {
    Box (modifier = Modifier.weight(6f).background(colorResource(R.color.secondary_color))){
        Image(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            painter = painterResource(R.drawable.test5),
            contentDescription = "Introduction image",
            contentScale = ContentScale.Crop
        )

    }
    Box(modifier = Modifier.weight(2f).background(colorResource(R.color.secondary_color),),



    ){
 Column(verticalArrangement = Arrangement.Top , horizontalAlignment = Alignment.Start) {
     Text("Let's Get Started",
         modifier = Modifier.padding(start = 20.dp),
         fontWeight = FontWeight.Bold, fontSize = 32.sp)


     StartButtonDesign(navController)
 }
    }
}

    }
}
