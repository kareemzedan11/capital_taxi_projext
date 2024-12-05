package com.example.capital_taxi.Presentation.ui.Driver.Screens.VerficationScreens.faceValidation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaceValidation(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Face Validation", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
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
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.navigationBars)

                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DriverDocumentUploader(navController = navController)
            }
        }
    }
}

@Composable
fun DriverDocumentUploader(navController: NavController) {
    var isUploaded by remember { mutableStateOf(false) }
    var capturedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val savedImagePath = remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    // Camera launcher
    val captureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            val path = saveBitmapToFile(context, bitmap, "captured_photo.jpg")
            if (path != null) {
                savedImagePath.value = path
                capturedBitmap = bitmap
                isUploaded = true
            } else {
                Toast.makeText(context, "Failed to save photo.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Failed to capture photo.", Toast.LENGTH_SHORT).show()
        }
    }

    // Load saved bitmap on start
    LaunchedEffect(Unit) {
        val path = context.filesDir.resolve("captured_photo.jpg").absolutePath
        val loadedBitmap = loadBitmapFromFile(path)
        if (loadedBitmap != null) {
            capturedBitmap = loadedBitmap
            isUploaded = true
            savedImagePath.value = path
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DocumentButton(
            navController,
            documentName = "ID Picture",
            isUploaded = isUploaded,
            capturedBitmap = capturedBitmap,
            onCapture = { captureLauncher.launch(null) }
        )
    }
}

fun saveBitmapToFile(context: Context, bitmap: Bitmap, fileName: String): String? {
    val file = File(context.filesDir, fileName) // Save in app's private directory
    return try {
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out) // Save as JPEG
        }
        file.absolutePath // Return the file path
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

fun loadBitmapFromFile(filePath: String): Bitmap? {
    return BitmapFactory.decodeFile(filePath)
}

@Composable
fun DocumentButton(
    navController: NavController,
    documentName: String,
    isUploaded: Boolean,
    capturedBitmap: Bitmap?,
    onCapture: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(90.dp))

        Text(text = "Photo", color = Color.Black, fontSize = 25.sp, fontWeight = FontWeight.W900)
        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            if (capturedBitmap != null) {
                // Show captured image
                Image(
                    bitmap = capturedBitmap.asImageBitmap(),
                    contentDescription = "Captured Photo",
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                // Show Lottie animation
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.facescan))
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
                LottieAnimation(composition = composition, progress = { progress })
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        // Conditional button based on `isUploaded` state
        if (!isUploaded) {
            Button(
                onClick = onCapture,
                colors = ButtonColors(
                    containerColor = Color.White, contentColor = Color.Green,
                    disabledContainerColor = Color.White, disabledContentColor = Color.Green
                ),
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
                    .background(Color.Green)
            ) {
                Text(text = "Add a photo", color = Color.Green, fontSize = 18.sp)
            }
        } else {
            Button(
                onClick = { /* Handle upload confirmation */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                enabled = false // Disabled once photo is uploaded
            ) {
                Text(text = "Photo is uploaded", color = Color.White, fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "- Clearly visible face",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "- Without sunglasses",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 18.sp
            )
            Text(
                text = "- Good lighting and without filters",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(90.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .background(Color.Red),
            onClick = {navController.navigate(Destination.DriverLicence.route)}
        ) {
            Text("Continue", fontSize = 20.sp, color = Color.White)
        }
    }
}
