package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R
@Composable
fun CapitalTaxiChatScreen(navController: NavController) {
    androidx.compose.material.Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                title = {
                    androidx.compose.material.Text(
                        "Back",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    androidx.compose.material.IconButton(onClick = { navController.popBackStack() }) {
                        androidx.compose.material.Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // القسم العلوي: الرسائل والخيارات
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ChatBubble(
                        text = "Welcome to Capital Taxi Assistant!",
                        isUser = false
                    )
                    ChatBubble(
                        text = "How can I assist you today?",
                        isUser = false
                    )

                    OptionsList(
                        options = listOf("Book a Ride", "View Rates", "FAQ", "Contact Support")
                    )
                }

                // القسم السفلي: حقل إدخال وزر
                MessageInputField()
            }
        }
    )
}

@Composable
fun ChatBubble(text: String, isUser: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if (!isUser) {
                // أيقونة المساعد الذكي
                Card(
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = CircleShape,
                    modifier = Modifier.size(50.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(12.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(26.dp),
                            painter = painterResource(R.drawable.headphone_18080416), // أيقونة المساعد
                            contentDescription = null,
                            tint = Color(0XFF46C96B)
                        )
                    }
                }
                Spacer(Modifier.width(8.dp)) // مسافة بين الأيقونة والفقاعة
            }

            Box(
                modifier = Modifier
                    .background(
                        if (isUser) Color(0xFFD1E8FF) else Color(0xFFF5F5F5),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(12.dp)
            ) {
                Text(text = text, fontSize = 14.sp, color = Color.Black)
            }
        }
    }
}

@Composable
fun OptionsList(options: List<String>) {
    Box(
        modifier = Modifier
            .background(Color.LightGray.copy(alpha = .5f))
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(.8f)
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            options.forEach { option ->
                OptionButton(option)
            }
        }
    }
}

@Composable
fun OptionButton(text: String) {
    Button(
        onClick = { /* Handle click */ },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(text = text, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInputField() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = { /* Handle text input */ },
            placeholder = { Text("Type your message here...") },
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
             Color.Transparent,
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
            onClick = { /* Handle send action */ },
            modifier = Modifier.size(26.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.send),
                contentDescription = "Send Message",
                tint = Color.Unspecified
            )
        }
    }
}
