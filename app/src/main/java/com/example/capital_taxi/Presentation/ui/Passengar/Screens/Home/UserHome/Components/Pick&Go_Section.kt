package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat.getColor
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.navigation.NavController
import com.example.capital_taxi.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pickAndGoDesign(navController: NavController) {
    val context = LocalContext.current // Get the context using LocalContext
    var money by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            LocationModalBottomSheetContent(navController = navController)
        }
    }
    val backgroundColor = Color.White
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.Start
        ) {  // Title Text


            Text(
                text = "Where are you going today?",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Column(
                modifier = Modifier

                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(1.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Pickup Location Row
                PickupDropOffRow(
                    iconRes = R.drawable.circle,
                    text = "Select Pickup Location",
                    onClick = { showBottomSheet = true }
                )

                // Vertical Divider
                repeat(9) {
                    VerticalDivider(
                        thickness = 2.dp,
                        modifier = Modifier
                            .height(5.dp)
                            .padding(start = 12.dp)
                    )
                }

                // Drop-Off Location Row
                PickupDropOffRow(

                    iconRes = R.drawable.travel,
                    text = "Select Drop-Off Location",
                    onClick = { showBottomSheet = true }

                )
            }


        }

//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            elevation = CardDefaults.elevatedCardElevation(10.dp),
//            shape = RoundedCornerShape(16.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.Transparent)
//                    .height(80.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Button(
//                    onClick = { /* TODO */ },
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF46C96B)),
//                    modifier = Modifier
//                        .width(200.dp)
//                        .height(50.dp),
//                    contentPadding = PaddingValues(0.dp)
//                ) {
//                    Text(text = "Find a driver", color = Color.Black, fontSize = 16.sp)
//                }
//
//            }
//        }

    }
}