package com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.drawerTabs.Inbox
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@Composable
fun InboxPage(navController: NavController) {
    Scaffold(
        topBar = {

            TopAppBar(
                navigationIcon = {

                    Icon(
                        tint = Color.Black,
                        modifier = Modifier.clickable { navController.popBackStack() },
                        imageVector = Icons.Default.ArrowBack, contentDescription = null)
                },

                title = { Text("Inbox", fontWeight = FontWeight.Bold, color = Color.Black) },
                backgroundColor = Color.White,
                contentColor = Color.White
            )
        },
        content = { paddingValues ->
            InboxContent(Modifier.padding(paddingValues))
        }
    )
}

@Composable
fun InboxContent(modifier: Modifier = Modifier) {
    val inboxItems = listOf(
        "Your ride is scheduled at 10:00 AM.",
        "You have earned a reward!",
        "Update: New features are available in the app.",
        "Your payment was successful.",
        "Reminder: Complete your profile "
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(inboxItems) { item ->
            InboxItem(text = item,time = "10:00 AM")
        }
    }
}
@Composable
fun InboxItem(text: String, time: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 9.dp),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Box for the message
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
            }

            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = time,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}
