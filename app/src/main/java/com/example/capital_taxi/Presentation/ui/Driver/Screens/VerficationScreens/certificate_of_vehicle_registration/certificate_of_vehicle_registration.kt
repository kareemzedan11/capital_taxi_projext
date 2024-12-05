package com.example.capital_taxi.ui.screens.Driver.VerficationScreens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CertificateOfVehicleRegistration(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Certificate Of Vehicle Registration",
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
                .background(Color.White)
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "Upload Vehicle Registration",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Front of National ID Card Section
                VehicleRegistrationCaptureSection(
                    title = "Front of Vehicle Registration",
                    buttonText = "Add Front Photo",
                    backgroundColor = Color(0xFFE0F7FA) // Light Blue
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Back of National ID Card Section
                VehicleRegistrationCaptureSection(
                    title = "Back of Vehicle Registration",
                    buttonText = "Add Back Photo",
                    backgroundColor = Color(0xFFFCE4EC) // Light Pink
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .background(Color.Red),
                    onClick = {   } // Replace with actual route
                ) {
                    Text("Continue", fontSize = 20.sp, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun VehicleRegistrationCaptureSection(
    title: String,
    buttonText: String,
    backgroundColor: Color
) {
    var capturedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    // Define file path for storage
    val fileName =
        if (title.contains("Front")) "vehicle_registration_front.jpg" else "vehicle_registration_back.jpg"
    val file = File(context.filesDir, fileName)

    // Load the saved image if available
    LaunchedEffect(Unit) {
        if (file.exists()) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            capturedBitmap = bitmap
        }
    }

    // Camera launcher
    val captureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            capturedBitmap = bitmap
            saveBitmapToFile(bitmap, file)
        } else {
            Toast.makeText(context, "Failed to capture photo.", Toast.LENGTH_SHORT).show()
        }
    }

    // Box with background color
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                if (capturedBitmap != null) {
                    Image(
                        bitmap = capturedBitmap!!.asImageBitmap(),
                        contentDescription = "Captured Photo",
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.id), // Placeholder image
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Button(
                onClick = { captureLauncher.launch(null) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Green
                ),
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
                    .background(Color.Green)
            ) {
                Text(text = buttonText, color = Color.Green, fontSize = 18.sp)
            }
        }
    }
}
