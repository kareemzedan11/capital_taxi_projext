package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Login.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.capital_taxi.Helper.GoogleAuthentication
import com.example.capital_taxi.R

@Composable
fun userMediaLoginOption() {

    // Social Media Login Options
    Row {

        SocialLoginButtons(onGoogleSignInClick = {}, icon = R.drawable.googleicon)

        Spacer(Modifier.width(30.dp))



        SocialLoginButtons(onGoogleSignInClick = {}, icon = R.drawable.xicon)

        Spacer(Modifier.width(30.dp))




        SocialLoginButtons(onGoogleSignInClick = {}, icon = R.drawable.facelogo)


        Spacer(modifier = Modifier.height(10.dp))

    }
}