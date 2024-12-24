package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Voucher

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.capital_taxi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun voucherScreen(navController: NavController) {


    VoucherScreenContent(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VoucherScreenContent(modifier: Modifier = Modifier, navController: NavController) {
    var voucher by remember { mutableStateOf("") }
    val context = LocalContext.current // Access the context safely
    val backgroundColor = Color(ContextCompat.getColor(context, R.color.general))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color((0XFF46C96B))),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Yellow Header Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color((0XFF46C96B))),

            ) {
            // Icon aligned to the start
            Icon(
                modifier = Modifier
                    .padding(start = 16.dp, top = 60.dp) // Adjust padding from the start and top
                    .size(40.dp)
                    .align(Alignment.TopStart) // Align icon to the top-start of the Box
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { navController.popBackStack()},
                imageVector = Icons.Default.Close,
                contentDescription = "Close Icon",
                tint = Color.Black
            )

            // Text centered in the Box
            Text(
                "Voucher",
                modifier = Modifier
                    .align(Alignment.Center) // Align text in the center of the Box
                    .padding(top = 60.dp), // Add padding from the top
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier=Modifier.height(20.dp))

        // Promo Code Input Section
        OutlinedTextField(
            value = voucher,
            onValueChange = { voucher = it },
            label = { Text("Have a promo code? Enter it here", color = Color.Gray) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp),
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(

                containerColor = Color.White
            )
        )
Spacer(modifier=Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center // Align content in the center of the Box
        ) {
            Text(
                "No Vouchers Available",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
    }

