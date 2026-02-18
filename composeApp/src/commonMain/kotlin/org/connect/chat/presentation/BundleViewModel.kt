package org.connect.chat.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BundleViewModel : ViewModel() {

    var permissionData by mutableStateOf<String?>(null)
        private set

    fun setUser(permission: String) {
        permissionData = permission
    }
}