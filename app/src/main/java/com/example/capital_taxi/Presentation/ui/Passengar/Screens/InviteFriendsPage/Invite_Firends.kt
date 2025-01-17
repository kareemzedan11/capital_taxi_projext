package com.example.capital_taxi.Presentation.ui.Passengar.Screens.InviteFriendsPage

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.InviteFriendsPage.Components.CodeBox
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.InviteFriendsPage.Components.DetailCard
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.InviteFriendsPage.Components.ShareOption
import com.example.capital_taxi.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InviteForMyApp(navController: NavController) {
    Scaffold(
        topBar = {

            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color.Transparent)
                                .border(4.dp, color = Color.Black, RoundedCornerShape(30.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(26.dp),
                                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                                contentDescription = stringResource(R.string.back),
                                tint = Color.Black
                            )
                        }
                    }
                },
                title = { Text("Invite Friends") })
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)) // Light background color
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {


                Spacer(modifier = Modifier.height(24.dp))
                // Header
                Text(
                    text = "Share with your friends and enjoy rewards!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.padding(30.dp))


                // Horizontal Scrollable Containers
                Text(
                    text = "Rewards",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    items(rewardItems) { reward ->
                        DetailCard(

                            title = reward.title,
                            description = reward.description,
                            backgroundColor = colorResource(R.color.secondary_color)
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(30.dp))


                // Code Box Section
                Text(
                    text = "Share Your Referral Code",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                CodeBox(code = "ABC123")


                Spacer(modifier = Modifier.padding(30.dp))


                // Share Options
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ShareOption(icon = R.drawable.facelogo, label = "Facebook")
                    ShareOption(icon = R.drawable.whatsapp, label = "WhatsApp")
                    ShareOption(icon = R.drawable.email, label = "Email")
                    ShareOption(icon = R.drawable.ic_more, label = "More")
                }
                Spacer(modifier = Modifier.weight(1f))

            }
        }
    }
}




// Sample data for rewards
data class Reward(val title: String, val description: String)

val rewardItems = listOf(
    Reward("Your Discount", "Get 20% off for every friend you invite!"),
    Reward("Win a Prize", "Get a $50 gift card when 5 friends join!"),
    Reward("Your Code", "Use code: ABC123 to share!")
)