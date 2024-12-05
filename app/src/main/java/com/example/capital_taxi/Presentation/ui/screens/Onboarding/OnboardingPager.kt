package com.example.capital_taxi.Presentation.ui.screens.Onboarding

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.example.capital_taxi.Presentation.ui.screens.Onboarding.Components.GoogleAndPhone
import com.example.capital_taxi.Presentation.ui.screens.Onboarding.Components.OnboardingScreen
import com.google.accompanist.pager.HorizontalPagerIndicator


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R
import com.google.accompanist.pager.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun OnboardingPager(navController: NavController, onSignInClick: () -> Unit) {


    // Context for accessing resources
    val context = LocalContext.current


    val backgroundColor = Color(ContextCompat.getColor(context, R.color.general))
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
            handleSignInResult(task, firebaseAuth, navController) // Pass navController here
        }




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color((0XFF46C96B))),
        contentAlignment = Alignment.Center

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .background(Color((0XFF46C96B))),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                "Capital Taxi",
                color = Color.Black,
                fontWeight = FontWeight.W900,
                fontSize = 40.sp
            )

            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .clip(CircleShape)


                    .background(Color.White)
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(top = 10.dp , end = 10.dp)
                        .size(150.dp),
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "logo"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text("On Time, ", color = Color.Black, fontWeight = FontWeight.W900, fontSize = 40.sp)
            Text(
                " Every Time! ",
                color = Color.Black,
                fontWeight = FontWeight.W900,
                fontSize = 40.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            ElevatedButton(
                onClick = {
                    // Launch the Google sign-in intent
                    val signInIntent = googleSignInClient.signInIntent
                    signInLauncher.launch(signInIntent)
                },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = RoundedCornerShape(16.dp)
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Icon(
                    modifier = Modifier.size(30.dp),
                    tint = Color.Unspecified,
                    contentDescription = "Google icon",
                    painter = painterResource(R.drawable.googleicon)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "Continue with Google",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            ElevatedButton(
                onClick = { navController.navigate("PhoneVerification") },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Black),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    "Continue with phone",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            ElevatedButton(
                onClick = { navController.navigate(Destination.SelectTheMode.route) },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    "Continue with Email and Password",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.weight(1f))

        }

    }
}
