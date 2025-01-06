package com.example.capital_taxi.Presentation.ui.screens.Confirm_information

import CountryCodePickerView
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.capital_taxi.R
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberImagePainter
import com.example.capital_taxi.Navigation.Destination
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmInformation(navController: NavController, Name: String, Email: String, PhotoUrl: String?) {
    // Default values if PhotoUrl is not provided
    val defaultPhotoUrl = "https://www.example.com/default_profile_picture.png"
    val photo = remember { mutableStateOf(Uri.parse(PhotoUrl ?: defaultPhotoUrl)) }

    var name by remember { mutableStateOf(Name) }
    var email by remember { mutableStateOf(Email) }
    var phone by remember { mutableStateOf("") }

    var phoneNumber by remember { mutableStateOf("") }

    var selectedCountry by remember { mutableStateOf("+1") }
    // For picking an image from the gallery
    val context = LocalContext.current
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            photo.value = it
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { null},
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
                                contentDescription = "Back",
                                tint = Color.Black
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Text(
                    "Confirm your information",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                )

                Spacer(modifier = Modifier.height(20.dp))


                Box(modifier = Modifier.height(80.dp).width(80.dp).clip(CircleShape)) {
                    AsyncImage(
                        model = photo.value,
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape).clickable { getContent.launch("image/*") }
                            .background(Color.Gray) // Default background if no image
                    )


                }
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                )
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    enabled = false
                )
                Spacer(modifier = Modifier.height(20.dp))


                // Country Code Picker and Phone Number Input Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = .2f)),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    CountryCodePickerView(onCountrySelected = { selectedCountry = it })

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it.trim() },
                        label = { Text("Phone Number") },
                        placeholder = { Text("Enter your phone number") },
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.White.copy(alpha = .2f)),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),

                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { navController.navigate(Destination.OtpScreen.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_color))
                ) {
                    Text("Next", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.W400)
                }
            }
        }
    }
}
