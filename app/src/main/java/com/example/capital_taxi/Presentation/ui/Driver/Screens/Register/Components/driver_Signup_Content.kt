package com.example.capital_taxi.Presentation.ui.Driver.Screens.Register.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.Presentation.Common.userMediaLoginOption
import com.example.capital_taxi.Presentation.Common.All_Register_textFields
import com.example.capital_taxi.Presentation.Common.AlreadyHaveAccount
import com.example.capital_taxi.Presentation.Common.RegisterHeader
import com.example.capital_taxi.Presentation.Common.SignUpButton
import com.example.capital_taxi.Presentation.Common.TermsAndConditionsCheckbox
import com.example.capital_taxi.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun driverSignupContent(navController: NavController) {

    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegisterHeader()
        Spacer(modifier = Modifier.height(16.dp))

        All_Register_textFields(

        )

        Spacer(modifier = Modifier.height(16.dp))

        TermsAndConditionsCheckbox(isChecked = isChecked, onCheckedChange = { isChecked = it })

        Spacer(modifier = Modifier.height(20.dp))

        SignUpButton(isEnabled = isChecked, onClick = { navController.navigate(Destination.FaceValidation.route) },R.string.Continue)

        Spacer(modifier = Modifier.padding(30.dp))

        userMediaLoginOption()

        Spacer(modifier = Modifier.padding(22.dp))

        AlreadyHaveAccount(navController)
    }
}