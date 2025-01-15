package com.example.capital_taxi.Presentation.viewmodel.shared

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewPasswordViewModel : ViewModel() {
    var password by mutableStateOf("")
    var showDialog by mutableStateOf(false)
    var dialogMessage by mutableStateOf("")
    var snackbarMessage by mutableStateOf("")

    // These would typically be passed from your resources
    val validPasswordMessage = "Please enter a valid password"
    val successPasswordMessage = "Password changed successfully"

    // Handle password change
    fun onPasswordChange(newPassword: String) {
        password = newPassword.trim()
    }

    // Handle save button click
    fun onSaveClicked(onSnackbar: (String) -> Unit) {
        if (password.isNotEmpty()) {
            dialogMessage = successPasswordMessage
            showDialog = true
        } else {
            snackbarMessage = validPasswordMessage
            onSnackbar(snackbarMessage)
        }
    }

    // Handle dialog confirmation (Navigate to login screen)
    fun onDialogConfirm(navigate: () -> Unit) {
        showDialog = false
        navigate()
    }
}
