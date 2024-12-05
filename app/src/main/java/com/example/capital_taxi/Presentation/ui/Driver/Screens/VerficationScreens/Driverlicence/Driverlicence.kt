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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R
import java.io.File
import java.io.FileOutputStream


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverLicence(navController: NavController) {
    var frontImageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var backImageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var selfieImageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Driver License", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
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
                    text = "Driver Licence",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Front of License Section
                DriverLicenseCaptureSection(
                    title = "Front of License",
                    buttonText = "Add Front Photo",
                    backgroundColor = Color(0xFFE0F7FA), // Light Blue
                    capturedBitmap = frontImageBitmap,
                    onBitmapCaptured = { frontImageBitmap = it },
                    fileName = "front_license.jpg",
                    placeholderImage = R.drawable.driverlicencelogo
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Back of License Section
                DriverLicenseCaptureSection(
                    title = "Back of License",
                    buttonText = "Add Back Photo",
                    backgroundColor = Color(0xFFFCE4EC), // Light Pink
                    capturedBitmap = backImageBitmap,
                    onBitmapCaptured = { backImageBitmap = it },
                    fileName = "back_license.jpg",
                    placeholderImage = R.drawable.driverlicencelogo
                )

                Spacer(modifier = Modifier.height(30.dp))

                // License Information Input
                DriverLicenseInfoSection()

                Spacer(modifier = Modifier.height(30.dp))

                // Selfie with License Section
                DriverLicenseCaptureSection(
                    title = "Selfie with License",
                    buttonText = "Add Selfie",
                    backgroundColor = Color(0xFFE8F5E9), // Light Green
                    capturedBitmap = selfieImageBitmap,
                    onBitmapCaptured = { selfieImageBitmap = it },
                    fileName = "selfie_license.jpg",
                    placeholderImage = R.drawable.selfiwithdriverlicence
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .background(Color.Red),
                    onClick = { navController.navigate(Destination.NationalIDValidation.route) }
                ) {
                    Text("Continue", fontSize = 20.sp, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun DriverLicenseCaptureSection(
    title: String,
    buttonText: String,
    backgroundColor: Color,
    capturedBitmap: Bitmap?,
    onBitmapCaptured: (Bitmap?) -> Unit,
    fileName: String,
    placeholderImage: Int
) {
    val context = LocalContext.current
    val directory = context.filesDir
    val file = File(directory, fileName)

    val captureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            saveImageToStorage(bitmap, file)
            onBitmapCaptured(bitmap)
        } else {
            Toast.makeText(context, "Failed to capture photo.", Toast.LENGTH_SHORT).show()
        }
    }

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
                        bitmap = capturedBitmap.asImageBitmap(),
                        contentDescription = "Captured Photo",
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    if (file.exists()) {
                        val bitmapFromStorage = BitmapFactory.decodeFile(file.absolutePath)
                        Image(
                            bitmap = bitmapFromStorage.asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = painterResource(placeholderImage),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
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

fun saveImageToStorage(bitmap: Bitmap, file: File) {
    try {
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


@Composable
fun DriverLicenseInfoSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        // License Number Input
        LicenseTextField(label = "Driver License Number")

        // Expiration Date Input
        LicenseTextField(label = "Expiration Date (YYYY-MM-DD)")
    }
}

@Composable
fun LicenseTextField(label: String) {
    var text by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        androidx.compose.material3.OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}




