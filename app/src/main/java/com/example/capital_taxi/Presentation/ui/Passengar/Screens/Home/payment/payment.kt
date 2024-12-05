package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.payment


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.capital_taxi.R

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat


data class PaymentMethod(
    val name: String,
    val description: String,
    val discount: String,
    val iconRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(navController: NavController) {
    PaymentScreenContent(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreenContent(

    modifier: Modifier = Modifier,
    navController: NavController
) {
    var selectedMethod by remember { mutableStateOf<PaymentMethod?>(null) }
    val context = LocalContext.current // Access the context safely
    val backgroundColor = Color(ContextCompat.getColor(context, R.color.general))
    val paymentMethods = listOf(
        PaymentMethod(
            "Click",
            "Balance: 250,000 UZS",
            "Get 5% discount",
            R.drawable.click
        ), // Replace with your actual drawable resource
        PaymentMethod("Cash", "Prepare your cash", "Get 7% discount", R.drawable.cash),
        PaymentMethod("Credit Card", "Visa or MasterCard", "Get 5% discount", R.drawable.card),
        PaymentMethod("payme", "Pay with your PayPal balance", "Get 3% discount", R.drawable.paypal)
    )

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
            // Close Icon
            Icon(
                modifier = Modifier
                    .padding(start = 16.dp, top = 50.dp)
                    .size(40.dp)
                    .align(Alignment.TopStart)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { navController.popBackStack() },
                imageVector = Icons.Default.Close,
                contentDescription = "Close Icon",
                tint = Color.Black
            )

            // Header Text
            Text(
                "Payment Method",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 40.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Payment Methods List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(Color.White)
                .padding(16.dp)
        ) {
            item {
                Text(
                    "Choose Payment Method",
                    modifier = Modifier

                        .padding(top = 20.dp),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))

            }
            items(paymentMethods) { method ->
                PaymentMethodCard(
                    paymentMethod = method,
                    isSelected = selectedMethod == method,
                    onSelect = { selectedMethod = method }
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))

            }
            item {

                Button(
                    onClick = { /* Handle new method */ },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 30.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text("+ Add New Method", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun PaymentMethodCard(
    paymentMethod: PaymentMethod,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onSelect() }
            .background(
                color = if (isSelected) Color(0xFFFFF8E1) else Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Payment Method Icon
        Icon(
            painter = painterResource(id = paymentMethod.iconRes),
            contentDescription = paymentMethod.name,
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)

                .background(Color.Yellow),

            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Payment Method Details
        Column(modifier = Modifier.weight(1f)) {
            Text(paymentMethod.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(paymentMethod.description, color = Color.Gray, fontSize = 14.sp)
            Text(paymentMethod.discount, color = Color.Gray, fontSize = 14.sp)
        }

        // Selection Indicator
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(if (isSelected) Color.Yellow else Color.LightGray)
        )
    }
}
