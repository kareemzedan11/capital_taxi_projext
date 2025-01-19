package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Login.Components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app.ui.theme.CustomFontFamily
import com.example.app.ui.theme.responsiveTextSize
import com.example.capital_taxi.Helper.PermissionViewModel
import com.example.capital_taxi.Helper.checkLocationPermission
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.Presentation.Common.ForgetPassword
import com.example.capital_taxi.Presentation.Common.LoginForm
import com.example.capital_taxi.Presentation.Common.userMediaLoginOption
import com.example.capital_taxi.R


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

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Text(
            text = stringResource(R.string.signin),
            fontSize = responsiveTextSize(fraction = 0.06f, minSize = 20.sp, maxSize = 32.sp),

            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(40.dp))
        LoginForm(
            email = email,
            password = password,
            onEmailChange = { email = it },
            onPasswordChange = { password = it },
            passwordVisible = passwordVisible,
            onPasswordToggle = { passwordVisible = !passwordVisible }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.align(alignment = Alignment.End)) {
            ForgetPassword(navController)
        }
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
            shape = RoundedCornerShape(8.dp)


        ) {
            Text(
                text = stringResource(R.string.signin),
                fontSize = responsiveTextSize(fraction = 0.06f, minSize = 14.sp, maxSize = 18.sp),

                fontFamily = CustomFontFamily,
                color = Color.Black
            )

        }



        Spacer(modifier = Modifier.height(60.dp))


        Text(
            text = stringResource(R.string.sign_in_with),
            color = Color.Black,
            fontSize = responsiveTextSize(fraction = 0.06f, minSize = 14.sp, maxSize = 20.sp),

            fontFamily = CustomFontFamily,

            )

        Spacer(modifier = Modifier.height(40.dp))
        userMediaLoginOption()

        Spacer(modifier = Modifier.height(60.dp))

        // SignUp Text
        Row {
            Text(
                text = stringResource(id = R.string.Dont_have_an_account),
                fontSize = responsiveTextSize(fraction = 0.06f, minSize = 14.sp, maxSize = 20.sp),

                fontFamily = CustomFontFamily,
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(id = R.string.SignUp),
                color = colorResource(R.color.primary_color),
                fontSize = responsiveTextSize(fraction = 0.06f, minSize = 14.sp, maxSize = 20.sp),


                fontFamily = CustomFontFamily,
                modifier = Modifier.clickable {
                    navController.navigate(Destination.UserRegister.route)
                }
            )
        }
    }
}

