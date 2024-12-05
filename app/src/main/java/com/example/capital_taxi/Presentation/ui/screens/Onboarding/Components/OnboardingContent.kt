package com.example.capital_taxi.Presentation.ui.screens.Onboarding.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun OnboardingContent(imageRes: Int, title: String, description: String) {
    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .fillMaxSize()
            .padding(top = 60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(

            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier .weight(2f)
        )


     Box(modifier = Modifier.fillMaxWidth().weight(2f).background(Color.Transparent)){
         Column {
             BasicText(
                 modifier = Modifier.padding(start = 20.dp),

                 text = title,
                 style = TextStyle(fontSize = 25.sp, color = Color.Black, fontWeight = FontWeight.Bold)
             )

Spacer(modifier = Modifier.padding(vertical = 5.dp))

             BasicText(
                 modifier = Modifier.padding(start = 40.dp),

                 text = description,
                 style = TextStyle(fontSize = 20.sp, color = Color.Gray, fontWeight = FontWeight.W300)
             )
         }
     }


    }
}