package com.example.capital_taxi.data.repository


import android.content.Context
import android.net.Uri
import com.example.capital_taxi.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.tasks.Task

class AuthRepository(private val context: Context) {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    private val googleSignInClient = GoogleSignIn.getClient(context, gso)

    fun getGoogleSignInClient() = googleSignInClient

    suspend fun handleSignInResult(
        task: Task<GoogleSignInAccount>,
        onSuccess: (String, String, String?) -> Unit,
        onFailure: (String) -> Unit
    ) {
        try {
            val account = task.getResult(Exception::class.java)
            if (account == null) {
                onFailure("Google sign-in failed: Account is null")
                return
            }
            val idToken = account.idToken
            val photoUrl = account.photoUrl?.toString()
            val displayName = account.displayName ?: "Unknown User"
            val email = account.email ?: "Unknown Email"

            val credential = GoogleAuthProvider.getCredential(idToken, null)
            firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { authTask ->
                    if (authTask.isSuccessful) {
                        onSuccess(displayName, email, photoUrl)
                    } else {
                        onFailure("Authentication failed: ${authTask.exception?.message}")
                    }
                }
        } catch (e: Exception) {
            onFailure("An error occurred during sign-in: ${e.message}")
        }
    }
}
