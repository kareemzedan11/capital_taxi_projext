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
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.chat_bot.Components.ChatBubble
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.chat_bot.Components.MessageInputField
import com.example.capital_taxi.Presentation.viewmodel.passenger.PassengerChatViewModel
import com.example.capital_taxi.R
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CapitalTaxiChatScreen(navController: NavController) {
    val chatViewModel: PassengerChatViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Back",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
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
                // Show previous messages (user and assistant)
                Column(
                    modifier = Modifier
                        .fillMaxHeight(.9f)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Default welcome message
                    ChatBubble(text = "Welcome to Capital Taxi Assistant!", isUser = false)
                    ChatBubble(text = "How can I assist you today?", isUser = false)

                    // Displaying messages from the ViewModel
                    chatViewModel.messages.forEach { (text, isUser) ->
                        ChatBubble(text = text, isUser = isUser)
                    }
                }

                // Message input and send button
                MessageInputField(chatViewModel)
            }
        }
    )
}



