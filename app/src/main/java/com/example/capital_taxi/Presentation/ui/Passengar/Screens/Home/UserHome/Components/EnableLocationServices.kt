package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R


@Composable
fun EnableLocationServices() {
    Box(
        modifier = Modifier

            .fillMaxWidth()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {


        Column (horizontalAlignment = Alignment.CenterHorizontally){


            Box(
                modifier = Modifier

                    .height(100.dp)
                    .width(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .windowInsetsPadding(WindowInsets.navigationBars)
                        .padding(horizontal = 20.dp),
                    contentDescription = "the service not available ",
                    painter = painterResource(R.drawable.logo9)
                )


            }
            Spacer(Modifier.height(50.dp))

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)) {
                        append("Enable Location\nServices\n")
                    }

                    withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.W600)) {
                        append("Please enable location services so\nwe can offer personalized features and\nservices based on where you are")
                    }
                },
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp),
                color = Color.Gray
            )
            Spacer(Modifier.height(30.dp))

            Button(
                onClick = { /* Handle new method */ },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier


                    .height(80.dp)
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(Color(0xffFFA500))
            ) {
                Text("Enable in Settings",
                    fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

        }
    }
}
