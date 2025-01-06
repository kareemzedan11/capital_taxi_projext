package com.example.capital_taxi.Presentation.ui.Passengar.Screens.chat_bot

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R


@Composable
fun CapitalTaxiChatScreen(navController: NavController) {

    val messages = remember { mutableStateListOf<Pair<String, Boolean>>() }

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
                // Upper section: Messages and Options
                Column(
                    modifier = Modifier
                        .fillMaxHeight(.9f)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Show initial assistant messages
                    ChatBubble(
                        text = "Welcome to Capital Taxi Assistant!",
                        isUser = false
                    )
                    ChatBubble(
                        text = "How can I assist you today?",
                        isUser = false
                    )

                    OptionsList(
                        options = listOf("Book a Ride", "View Rates", "FAQ", "Contact Support"),
                        onOptionClick = { selectedOption ->
                            messages.add(selectedOption to true)
                        }
                    )


                    messages.forEach { (text, isUser) ->
                        ChatBubble(
                            text = text,
                            isUser = isUser
                        )
                        if (text == "Book a Ride") {
                            ChatBubble(
                                text = "How can I assist you today?hhhhhhhhh",
                                isUser = false
                            )
                        }
                        else{
                            ChatBubble(
                                text = "How can I assist you today?",
                                isUser = false
                            )
                        }

                    }

                }


                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {
                    MessageInputField(messages = messages)
                }
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
                            tint = colorResource(R.color.primary_color)
                        )
                    }
                }
                Spacer(Modifier.width(8.dp))
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
fun OptionsList(options: List<String>, onOptionClick: (String) -> Unit) {
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
                OptionButton(option, onOptionClick)
            }
        }
    }
}

@Composable
fun OptionButton(text: String, onClick: (String) -> Unit) {
    Button(
        onClick = { onClick(text) },
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
fun MessageInputField(messages: MutableList<Pair<String, Boolean>>) {
    var message by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            textStyle = TextStyle(Color.Black),
            value = message,
            onValueChange = { message = it },
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
            onClick = {
                if (message.isNotEmpty()) {
                    // Add the new user message to the list
                    messages.add(message to true)
                    // Clear the message input field after sending
                    message = ""
                }
            },
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
