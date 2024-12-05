package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import IntercityCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationModalBottomSheetContent(apiKey: String) {
    var pickupPoint by remember { mutableStateOf("") }
    var destinationPoint by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Arrange your trip",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
        )


        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false }
            ) {
                IntercityCard()
            }}
        PickupNowForMeUI()

        // Box for Pickup and Pickoff Points
        Box(
            modifier = Modifier
                .background(
                    color = Color(0XFFF2F2F2),
                    shape = RoundedCornerShape(20.dp)
                 )


                .padding(horizontal = 16.dp ),

        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Pickup Point Field
                LocationAutocompleteField(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Pickup Point",
                            tint = Color(0XFF46C96B)
                        )
                    },
                    label = "Pickup Point",
                    query = pickupPoint,
                    onQueryChanged = { pickupPoint = it },
                    onLocationSelected = { pickupPoint = it },
                    apiKey = apiKey
                )

                // Divider with Add Button
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    HorizontalDivider(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp), // Reduced horizontal padding
                        color = Color.LightGray,
                        thickness = 1.dp
                    )
                    IconButton(
                        onClick = { showBottomSheet = true  },
                        modifier = Modifier
                            .background(
                                color = Color(0XFF46C96B),
                                shape = RoundedCornerShape(50)
                            )
                            .size(36.dp) // Reduced icon size for less space
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Point",
                            tint = Color.White
                        )
                    }
                }

                // Pickoff Point Field
                LocationAutocompleteField(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Pickoff Point",
                            tint = Color(0XFF46C96B)
                        )
                    },
                    label = "Pickoff Point",
                    query = destinationPoint,
                    onQueryChanged = { destinationPoint = it },
                    onLocationSelected = { destinationPoint = it },
                    apiKey = apiKey
                )
            }
        }
}

        Spacer(modifier = Modifier.padding(vertical = 10.dp))
    MostVisitedPlaces()
    Spacer(modifier = Modifier.padding(vertical = 10.dp))
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {

        HorizontalDivider(
            modifier = Modifier,
            thickness = 2.dp
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        HorizontalDivider(
            modifier = Modifier,
            thickness = 2.dp
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationAutocompleteField(
    icon: @Composable (() -> Unit)? = null,
    label: String,
    query: String,
    onQueryChanged: (String) -> Unit,
    onLocationSelected: (String) -> Unit,
    apiKey: String
) {
    var suggestions by remember { mutableStateOf(listOf<String>()) }
    var expanded by remember { mutableStateOf(false) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Icon beside the text field
            if (icon != null) {
                icon()
                Spacer(modifier = Modifier.width(8.dp))
            }

            OutlinedTextField(
                value = query,
                onValueChange = {
                    onQueryChanged(it)
                    if (it.isNotEmpty()) {
                        fetchPlaceSuggestions(it, apiKey) { result ->
                            suggestions = result
                            expanded = suggestions.isNotEmpty()
                        }
                    } else {
                        suggestions = emptyList()
                        expanded = false
                    }
                },
                label = { Text(label) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(0.dp), // No rounded corners
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    errorBorderColor = Color.Transparent,
                    cursorColor = Color.Black // Optional: Set cursor color
                )
            )
        }
    }
}


@Composable
fun PickupNowForMeUI() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OptionButtonWithMenu(
            icon = Icons.Default.AddCircle,

            text = "Pickup Now",
            onMenuClick = { onPickupNowMenuClick() },
            showIcon = true // Show icon for "For Me"

        )
        Spacer(modifier = Modifier.width(8.dp))
        OptionButtonWithMenu(
            icon = Icons.Default.Person,
            text = "For Me",
            onMenuClick = { onForMeMenuClick() },
            showIcon = true // Show icon for "For Me"
        )
    }
}

@Composable
fun OptionButtonWithMenu(
    icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    text: String,
    onMenuClick: () -> Unit,
    showIcon: Boolean
) {
    Surface(
        modifier = Modifier
            .width(200.dp)
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFF2F2F2), // Light gray background
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showIcon && icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(10.dp))

            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown, // Menu icon
                    contentDescription = "KeyboardArrowDown",
                    tint = Color.Gray
                )
            }
        }
    }
}

fun onPickupNowMenuClick() {
    println("Pickup Now menu clicked!")
}

fun onForMeMenuClick() {
    println("For Me menu clicked!")
}

@Preview(showBackground = true)
@Composable
fun PreviewPickupNowForMeUI() {
    PickupNowForMeUI()
}
