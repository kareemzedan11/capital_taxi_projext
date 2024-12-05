import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneVerification(navController: NavController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    var phoneNumber by remember { mutableStateOf("") }
    var verificationId by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var isVerifying by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    var selectedCountry by remember { mutableStateOf("+1") }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val coroutineScope = rememberCoroutineScope()

    // List of countries with icons
    val countries = listOf(
        Triple("+1", "US", R.drawable.egypt,),
        Triple("+91", "India", R.drawable.us),
        Triple("+44", "UK", R.drawable.egypt),
        Triple("+20", "Egypt", R.drawable.egypt),
        Triple("+971", "UAE", R.drawable.egypt)
    )

    // Send verification code function
    fun sendVerificationCode() {
        if (phoneNumber.isNotEmpty()) {
            isVerifying = true
            val fullPhoneNumber = selectedCountry + phoneNumber
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(fullPhoneNumber) // Full phone number
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(context as Activity)
                .setCallbacks(object : OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {}

                    override fun onVerificationFailed(e: FirebaseException) {
                        errorMessage = "Verification failed: ${e.message}"
                        isVerifying = false
                    }

                    override fun onCodeSent(
                        verificationId: String,
                        token: PhoneAuthProvider.ForceResendingToken
                    ) {

                    }
                })
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    // Bottom sheet for selecting a country
    if (sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = { coroutineScope.launch { sheetState.hide() } },
            sheetState = sheetState,
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Select Country", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                countries.forEach { country ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedCountry = country.first
                                coroutineScope.launch { sheetState.hide() }
                            }
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = country.third),
                            contentDescription = country.second,
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("${country.first} (${country.second})")
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize().background(Color.White),
        topBar = {
            TopAppBar(
                title = { null },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Join us via Phone",
                    fontWeight = FontWeight.W900,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    "We'll text a code to verify your phone",
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                // Combined TextField
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it.trim() },
                    label = { Text("Phone Number") },
                    placeholder = { Text("Enter your phone number") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = .2f)),
                    singleLine = true,
                    leadingIcon = {
                        Row(
                            modifier = Modifier.padding(start = 8.dp),
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                        ) {
                            Text(
                                text = selectedCountry,
                                modifier = Modifier.clickable { coroutineScope.launch { sheetState.show() } },
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Icon(
                                painter = painterResource(R.drawable.baseline_arrow_drop_down_24),
                                contentDescription = "Select Country",
                                modifier = Modifier.clickable { coroutineScope.launch { sheetState.show() } }
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { sendVerificationCode() })
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { sendVerificationCode() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = phoneNumber.isNotEmpty() && !isVerifying
                ) {
                    Text("Send Code")
                }

                Spacer(modifier = Modifier.height(20.dp))

                if (verificationId.isNotEmpty()) {
                    OutlinedTextField(
                        value = otp,
                        onValueChange = { otp = it },
                        label = { Text("Enter OTP") },
                        placeholder = { Text("Enter OTP sent to your phone") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White.copy(alpha = .2f)),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            // Verification logic here
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = otp.isNotEmpty()
                    ) {
                        Text("Verify OTP")
                    }
                }

                if (errorMessage.isNotEmpty()) {
                    Text(errorMessage, color = Color.Red, fontSize = 16.sp)
                }
            }
        }
    }
}
