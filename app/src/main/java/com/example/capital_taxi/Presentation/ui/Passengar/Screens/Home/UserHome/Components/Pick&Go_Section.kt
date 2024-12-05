package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
fun pickAndGoDesign(modifier: Modifier = Modifier) {
    val context = LocalContext.current // Get the context using LocalContext
    var money by remember { mutableStateOf("") }
    var showPickUpBottomSheet by remember { mutableStateOf(false) }
    var showFareBottomSheet by remember { mutableStateOf(false) }
    val pickUpSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    val fareSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    val backgroundColor = Color(ContextCompat.getColor(context, R.color.general))
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(backgroundColor)
//            .windowInsetsPadding(WindowInsets.navigationBars)
//            .padding(20.dp),
//        verticalArrangement = Arrangement.spacedBy(20.dp),
//        horizontalAlignment = Alignment.Start
//    ) {  // Title Text



//        Text(
//            text = "Where are you going today?",
//            style = MaterialTheme.typography.titleLarge.copy(
//                fontWeight = FontWeight.Bold,
//                fontSize = 22.sp,
//                color = Color.Black
//            ),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp)
//        )

//        // Pickup Button
//        Button(
//            onClick = { showPickUpBottomSheet = true },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
//            shape = RoundedCornerShape(20.dp)
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Start
//            ) {
//                Icon(
//                    painter = painterResource(R.drawable.yellow),
//                    contentDescription = null,
//                    modifier = Modifier.size(30.dp),
//                    tint = Color.Unspecified
//                )
//                Spacer(modifier = Modifier.width(12.dp))
//                Text(
//                    text = "Choose pick up point",
//                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
//                    modifier = Modifier.weight(1f)
//                )
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Add Icon",
//                    modifier = Modifier.size(24.dp),
//                    tint = Color.Black
//                )
//            }
//        }

        // Destination Button
//        ElevatedButton (
//            onClick = { showPickUpBottomSheet = true },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
//            shape = RoundedCornerShape(30.dp)
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Start
//            ) {
//                Icon(
//                    painter = painterResource(R.drawable.travel),
//                    contentDescription = null,
//                    modifier = Modifier.size(35.dp),
//                    tint = Color.Unspecified
//                )
//                Spacer(modifier = Modifier.width(12.dp))
//                Text(
//                    text = "Choose your destination",
//                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
//                    modifier = Modifier.weight(1f)
//                )
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Add Icon",
//                    modifier = Modifier.size(24.dp),
//                    tint = Color.Black
//                )
//            }
//        }
//
//        if (showPickUpBottomSheet) {
//            ModalBottomSheet(
//                modifier = Modifier.fillMaxHeight(),
//                sheetState = pickUpSheetState,
//                onDismissRequest = { showPickUpBottomSheet = false }
//            ) {
//                LocationModalBottomSheetContent(apiKey = "AIzaSyArzERc9xBRprPePwc4uTopBW9WMBymX74")
//            }
//        }


//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 20.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ) {
//
//
//            // Button in the Middle
//            Button(
//                onClick = { /* TODO */ },
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
//                shape = RoundedCornerShape(16.dp),
//                modifier = Modifier
//                    .width(200.dp)
//                    .height(50.dp),
//                contentPadding = PaddingValues(0.dp) // Set contentPadding to 0
//            ) {
//                Text(text = "Find a driver", color = Color.Black, fontSize = 16.sp)
//            }
//
//
//        }sp


    }
