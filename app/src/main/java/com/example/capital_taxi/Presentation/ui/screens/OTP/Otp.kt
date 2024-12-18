package com.example.capital_taxi.Presentation.ui.screens.OTP

import android.widget.Space
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen(navController: NavController) {
    var timer by remember { mutableStateOf(60) }
    var otpValues = remember { mutableStateListOf("", "", "", "") }

    val focusRequesters = List(4) { FocusRequester() }
    val localFocusManager = LocalFocusManager.current

    val progress by animateFloatAsState(
        targetValue = timer / 60f,
        animationSpec = androidx.compose.animation.core.tween(
            durationMillis = 1000,
            easing = LinearEasing
        )
    )

    // Countdown logic
    LaunchedEffect(key1 = timer) {
        if (timer > 0) {
            delay(1000L)
            timer--
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Text(
                        "OTP Verification",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0XFF46C96B)), contentAlignment = Alignment.TopCenter
        ) { Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
            Image(

                modifier = Modifier.size(200.dp).clip(CircleShape)
                ,
                painter = painterResource(R.drawable.otp), contentDescription = null
            )

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "OTP",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Enter OTP sent to +0123456789",
                fontSize = 18.sp,
                color = Color(0XFFF2F2F2),
                fontWeight = FontWeight.Bold
            )
        }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.BottomCenter)
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom

            ) {

                Box(
                    modifier = Modifier
                        .fillMaxHeight(.6f)
                        .fillMaxWidth()


                        .clip(RoundedCornerShape(topStart = 100.dp ))
                        .background(Color.White),
                ) {
                    Column (         horizontalAlignment = Alignment.CenterHorizontally,){


                        Spacer(modifier = Modifier.height(60.dp))
                        // OTP Input Boxes
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            otpValues.forEachIndexed { index, value ->
                                OtpInputBox(
                                    value = value,
                                    onValueChange = { newValue ->
                                        if (newValue.length <= 1) {
                                            otpValues[index] = newValue
                                            // Move to next field if input is not empty
                                            if (newValue.isNotEmpty() && index < focusRequesters.lastIndex) {
                                                focusRequesters[index + 1].requestFocus()
                                            } else if (newValue.isEmpty() && index > 0) {
                                                focusRequesters[index - 1].requestFocus()
                                            }
                                        }
                                    },
                                    modifier = Modifier
                                        .size(60.dp)
                                        .padding(4.dp)
                                        .focusRequester(focusRequesters[index])
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = if (timer > 0) "Resend code in $timer seconds" else "Resend code now",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                            HorizontalDivider(

                                color = Color.Gray,
                                thickness = 1.dp,
                                modifier = Modifier
                                       .fillMaxWidth(.42f)


                            )


                        Spacer(modifier = Modifier.height(16.dp))


                        // Linear Progress Indicator
                        LinearProgressIndicator(
                            progress = progress,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))


                        Button(
                            onClick = {navController.navigate(Destination.SelectTheMode.route)},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)


                                .height(60.dp),
                            colors = ButtonDefaults.buttonColors(Color(0XFF46C96B)),
                            shape = RoundedCornerShape(8.dp)

                        ) {
                            Text(
                                text = "Verify Now",
                                fontSize = 18.sp,

                                )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }

                }
            }

        }
    }
}

@Composable
fun OtpInputBox(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            // Allow only digits and a single character
            if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                onValueChange(newValue)
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = LocalTextStyle.current.copy(
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        ),
        modifier = modifier
            .background(Color.White)
            .border(1.dp, Color.Gray)
    )
}
