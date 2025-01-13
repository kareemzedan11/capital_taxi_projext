package com.example.capital_taxi.Presentation.ui.shared.Onboarding

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R
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


    val backgroundColor = Color(ContextCompat.getColor(context, R.color.primary_color))
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




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.primary_color)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.primary_color)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(0.53f)

                    .background(colorResource(R.color.primary_color)),
                contentAlignment = Alignment.Center


            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Icon(

                        painter = painterResource(R.drawable.logo),
                        contentDescription = "Logo"

                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            stringResource(R.string.onboarding_message1),
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            stringResource(R.string.onboarding_message2),
                            fontSize = 20.sp, color = Color.Black
                        )

                    }
                }
            }


            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
            ) {
                Image(
                    painter = painterResource(R.drawable.sec),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SignInButton(
                        onSignInClick = {
                            val signInIntent = googleSignInClient.signInIntent
                            signInLauncher.launch(signInIntent)
                        },
                        navController,
                        stringResource(R.string.continue_with_google),

                        color = true,
                        image = R.drawable.googleicon
                    )
                    SignInButton(
                        onSignInClick = { navController.navigate(Destination.PhoneVerification.route) },
                        navController, stringResource(R.string.continue_with_phone),
                        R.drawable.phone2
                    )

                    Text(

                        stringResource(R.string.log_in),

                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                        color = Color(0XFF987200),
                        modifier = Modifier
                            .clickable { navController.navigate(Destination.modeDesign.route) },
                    )

                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                }
            }

        }


    }

}

@Composable
private fun SignInButton(
    onSignInClick: () -> Unit,
    navController: NavController, text: String, image: Int, color: Boolean? = false
) {

    TextButton(
        onClick = { onSignInClick() },
        modifier = Modifier.Companion

            .fillMaxWidth(0.57f)
            .height(60.dp)
            .border(
                2.dp,
                color = Color(0XFFf2edde),
                RoundedCornerShape(10.dp)
            ),
        colors = ButtonDefaults.buttonColors(if (color ?: true) Color(0XFFf2edde) else Color.White),
        shape = RoundedCornerShape(10.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Icon(
                modifier = Modifier.size(26.dp),
                tint = Color(0XFF987200),
                contentDescription = null,
                painter = painterResource(image)
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(

                text,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = Color(0XFF987200),

                )
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}





