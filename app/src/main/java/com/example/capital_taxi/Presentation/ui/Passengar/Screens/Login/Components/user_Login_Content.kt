package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Login.Components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.capital_taxi.Helper.GoogleAuthentication
import com.example.capital_taxi.Helper.PermissionViewModel
import com.example.capital_taxi.Helper.checkLocationPermission
import com.example.capital_taxi.Helper.handleSignInResult
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components.EnableLocationServices
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components.PickupWithDropOffButtons
import com.example.capital_taxi.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun userLoginContent(


    navController: NavController
) {
    val permissionViewModel: PermissionViewModel = viewModel()
    val context = LocalContext.current

    // تأكد من التحقق من الصلاحية عند تحميل الشاشة
    LaunchedEffect(context) {
        checkLocationPermission(context, permissionViewModel)
    }

    val isLocationGranted by permissionViewModel.isLocationGranted.collectAsState()

    val scope = rememberCoroutineScope()
    var email1 by remember { mutableStateOf("") }
    var password1 by remember { mutableStateOf("") }
    var passwordVisible1 by remember { mutableStateOf(false) }
    var isGoogleSignInVisible by remember { mutableStateOf(false) }

    // Initialize FirebaseAuth instance
    val firebaseAuth = FirebaseAuth.getInstance()

    // Configure Google Sign-In options
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    // Initialize GoogleSignInClient
    val googleSignInClient = GoogleSignIn.getClient(context, gso)
    fun handleSignInResult(
        task: Task<GoogleSignInAccount>,
        firebaseAuth: FirebaseAuth,
        navController: NavController
    ) {
        try {
            val account = task.getResult(Exception::class.java)
            val idToken = account?.idToken
            val photoUrl = account?.photoUrl?.toString() // Convert Uri to String
            val displayName = account?.displayName ?: "Unknown User"
            val email = account?.email ?: "Unknown Email"

            // Authenticate with Firebase
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { authTask ->
                    if (authTask.isSuccessful) {
                        // Encode the values for safe navigation
                        val encodedName = Uri.encode(displayName)
                        val encodedEmail = Uri.encode(email)
                        val encodedPhotoUrl = Uri.encode(photoUrl ?: "")

                        // Navigate to ConfirmInformation
                        navController.navigate("ConfirmInformation?name=$encodedName&email=$encodedEmail&photoUrl=$encodedPhotoUrl")
                    } else {
                        println("Firebase Sign-In Failed: ${authTask.exception?.message}")
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    val signInLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task, firebaseAuth, navController)
        }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Text(
            text = "Sign In",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            shape = RoundedCornerShape(12.dp),// For rounded corners with 8.dp radius

            colors = TextFieldDefaults.outlinedTextFieldColors(
                // Setting the container background color to #F2F2F2
                containerColor = Color(0xFFF2F2F2), // Use the Color class to set the color
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,

                ),
            value = email1,
            onValueChange = { email1 = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    tint = colorResource(R.color.primary_color),
                    contentDescription = "email icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)

                )


            },
            label = { Text(stringResource(id = R.string.Email_label)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )


        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password1,
            onValueChange = { password1 = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    tint = colorResource(R.color.primary_color),

                    contentDescription = "password icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(20.dp)

                )
            },
            label = { Text(stringResource(id = R.string.Password_label)) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible1) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible1 = !passwordVisible1 }) {
                    Icon(
                        painter = painterResource(
                            if (passwordVisible1) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
                        ),
                        contentDescription = if (passwordVisible1) "Hide password" else "Show password"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                // Setting the container background color to #F2F2F2
                containerColor = Color(0xFFF2F2F2), // Use the Color class to set the color
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.Gray,

                ),
            shape = RoundedCornerShape(12.dp) // For rounded corners with 8.dp radius
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            textDecoration = TextDecoration.Underline,
            text = "Forget Password",
            modifier = Modifier
                .align(alignment = Alignment.End)
                .clickable { navController.navigate(Destination.NewPasswordScreen.route) },
            color = colorResource(R.color.primary_color),
            fontWeight = FontWeight.Bold, fontSize = 18.sp,


            )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (isLocationGranted) {
                    navController.navigate(Destination.UserHomeScreen.route)
                } else {
                    navController.navigate(Destination.searchForLocation.route)

                }


            },
            modifier = Modifier
                .fillMaxWidth()


                .height(60.dp),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_color)),
            shape = RoundedCornerShape(8.dp) // For rounded corners with 8.dp radius
// Use RectangleShape to remove any default rounded corners

        ) {
            Text(
                text = "Sign In",
                fontSize = 18.sp,
                color = Color.Black

            )
        }



        Spacer(modifier = Modifier.height(60.dp))


        Text(
            text = "Or sign in With",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Social Media Login Options
        Row {

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clickable {     // Launch the Google sign-in intent
                        val signInIntent = googleSignInClient.signInIntent
                        signInLauncher.launch(signInIntent) }
                    .background(
                        colorResource(R.color.secondary_color),
                        shape = RoundedCornerShape(10.dp) // For rounded corners with 8.dp radius

                    ), contentAlignment = Alignment.Center

            ) {
                Image(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape),
                    painter = painterResource(R.drawable.googleicon),
                    contentDescription = "Google Logo"
                )

            }
            if (isGoogleSignInVisible) {
                GoogleAuthentication(navController)
            }
            Spacer(Modifier.width(30.dp))

            Row {

                Box(
                    modifier = Modifier
                        .size(50.dp)

                        .background(
                            colorResource(R.color.secondary_color),
                            shape = RoundedCornerShape(10.dp) // For rounded corners with 8.dp radius

                        ), contentAlignment = Alignment.Center

                ) {
                    Image(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape),
                        painter = painterResource(R.drawable.xicon),
                        contentDescription = "X Logo"
                    )
                }
                Spacer(Modifier.width(30.dp))


                Row {

                    Box(
                        modifier = Modifier
                            .size(50.dp)

                            .background(
                                colorResource(R.color.secondary_color),
                                shape = RoundedCornerShape(10.dp) // For rounded corners with 8.dp radius

                            ), contentAlignment = Alignment.Center

                    ) {
                        Image(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape),
                            painter = painterResource(R.drawable.facelogo),
                            contentDescription = "Google Logo",


                            )
                    }


                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(60.dp))

        // SignUp Text
        Row {
            Text(
                text = stringResource(id = R.string.Dont_have_an_account),
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(id = R.string.SignUp),
                color = Color(0xFF6200EE),
                fontSize = 18.sp,
                modifier = Modifier.clickable {
                    navController.navigate(Destination.UserRegister.route)
                }
            )
        }
    }
}
