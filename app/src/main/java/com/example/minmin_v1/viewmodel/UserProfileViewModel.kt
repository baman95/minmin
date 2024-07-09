package com.example.minmin_v1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UserProfile(
    var name: String? = null,
    var gender: String? = null,
    var className: String? = null,
    var dateOfBirth: String? = null,
    var photoUrl: String? = null
)

class UserProfileViewModel : ViewModel() {
    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile

    fun updateName(name: String) {
        _userProfile.value = _userProfile.value.copy(name = name)
    }

    fun updateGender(gender: String) {
        _userProfile.value = _userProfile.value.copy(gender = gender)
    }

    fun updateClassName(className: String) {
        _userProfile.value = _userProfile.value.copy(className = className)
    }

    fun updateDateOfBirth(dateOfBirth: String) {
        _userProfile.value = _userProfile.value.copy(dateOfBirth = dateOfBirth)
    }

    fun updatePhotoUrl(photoUrl: String) {
        _userProfile.value = _userProfile.value.copy(photoUrl = photoUrl)
    }

    fun saveUserProfile() {
        viewModelScope.launch {
            // Save the user profile to persistent storage, e.g., SharedPreferences or a database
            // For now, we're just updating the flow
        }
    }
}
