package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


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
