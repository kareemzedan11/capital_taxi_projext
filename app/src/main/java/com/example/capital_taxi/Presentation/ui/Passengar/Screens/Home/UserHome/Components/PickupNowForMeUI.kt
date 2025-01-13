package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Helper.PartialBottomSheet
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R

@Composable
fun PickupNowForMeUI(navController: NavController) {
    var showPickupSheet by remember { mutableStateOf(false) }
    var showForMeSheet by remember { mutableStateOf(false) }

    if (showPickupSheet) {
        RideSelectionBottomSheet(
            title = stringResource(R.string.When_do_you_need_a_ride),
            options = listOf(stringResource(R.string.Now) to R.drawable.baseline_access_time_24,stringResource(R.string.Later) to R.drawable.baseline_punch_clock_24),
            selectedOption = remember { mutableStateOf("Now") },
            onDone = { navController.navigate(Destination.SelectTheMode.route) },
            onDismiss = { showPickupSheet = false }
        )
    }

    if (showForMeSheet) {
        RideSelectionBottomSheet(
            title =  stringResource(R.string.Who_is_the_ride_for),
            options = listOf(stringResource(R.string.For_Me) to R.drawable.baseline_person_24, stringResource(R.string.For_a_group) to R.drawable.baseline_groups_24),
            selectedOption = remember { mutableStateOf("For Me") },
            onDone = { /* Navigate or handle selection */ },
            onDismiss = { showForMeSheet = false }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 3.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        OptionButtonWithMenu(
            icon = Icons.Default.AddCircle,
            text =  stringResource(R.string.Pickup_Now),
            onMenuClick = { showPickupSheet = true },
            showIcon = true
        )
        Spacer(modifier = Modifier.width(8.dp))
        OptionButtonWithMenu(
            icon = Icons.Default.Person,
            text = stringResource(R.string.For_Me),
            onMenuClick = { showForMeSheet = true },
            showIcon = true
        )
    }
}

@Composable
fun RideSelectionBottomSheet(
    title: String,
    options: List<Pair<String, Int>>,
    selectedOption: MutableState<String>,
    onDone: () -> Unit,
    onDismiss: () -> Unit
) {
    PartialBottomSheet(
        showBottomSheet = true,
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.W700
            )

            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            options.forEach { option ->
                SelectableCard(
                    text = option.first,
                    isSelected = selectedOption.value == option.first,
                    iconRes = option.second,
                    onClick = { selectedOption.value = option.first }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onDone,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_color)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.Done_Button),
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun SelectableCard(
    text: String,
    isSelected: Boolean,
    iconRes: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.LightGray)
            .border(1.dp, color = Color.Gray)
            .clickable(onClick = onClick),
        elevation = CardDefaults.elevatedCardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(colorResource(R.color.secondary_color)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = text,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(26.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600
            )

            Spacer(modifier = Modifier.weight(1f))

            if (isSelected) {
                Icon(
                    painter = painterResource(R.drawable.selected),
                    contentDescription = "$text Selected",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}