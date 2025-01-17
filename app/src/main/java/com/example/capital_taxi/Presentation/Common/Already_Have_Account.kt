package com.example.capital_taxi.Presentation.Common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R


@Composable
fun AlreadyHaveAccount(navController: NavController) {
    Row {
        Text(
            text = stringResource(id = R.string.already_have_account),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = R.string.signin),
            color = colorResource(R.color.primary_color),
            fontSize = 18.sp,
            modifier = Modifier.clickable {
                navController.popBackStack()
            }
        )
    }
}
