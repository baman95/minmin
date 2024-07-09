package com.example.minmin_v1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minmin_v1.data.FirebaseHelper
import com.example.minmin_v1.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val firebaseHelper = FirebaseHelper()

    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile

    private val _interestSuggestions = MutableStateFlow<List<String>>(emptyList())
    val interestSuggestions: StateFlow<List<String>> = _interestSuggestions

    private val _subjectSuggestions = MutableStateFlow<List<String>>(emptyList())
    val subjectSuggestions: StateFlow<List<String>> = _subjectSuggestions

    private val _gameSuggestions = MutableStateFlow<List<String>>(emptyList())
    val gameSuggestions: StateFlow<List<String>> = _gameSuggestions

    init {
        loadSuggestions()
    }

    fun loadUserProfile() {
        viewModelScope.launch {
            val profile = firebaseHelper.getUserProfile()
            _userProfile.value = profile
        }
    }

    fun updateDisplayName(displayName: String) {
        _userProfile.value = _userProfile.value.copy(displayName = displayName)
    }

    fun updateInterests(interests: String) {
        _userProfile.value = _userProfile.value.copy(interests = interests)
    }

//    fun updateLocation(location: String) {
//        _userProfile.value = _userProfile.value.copy(location = location)
//    }

    fun updateGender(gender: String) {
        _userProfile.value = _userProfile.value.copy(gender = gender)
    }

    fun updateClassName(className: String) {
        _userProfile.value = _userProfile.value.copy(className = className)
    }

    fun updateFavouriteSubject(favouriteSubject: String) {
        _userProfile.value = _userProfile.value.copy(favouriteSubject = favouriteSubject)
    }

    fun updateFavouriteGame(favouriteGame: String) {
        _userProfile.value = _userProfile.value.copy(favouriteGame = favouriteGame)
    }

//    fun updateIdealName(idealName: String) {
//        _userProfile.value = _userProfile.value.copy(idealName = idealName)
//    }

    fun updateDob(dob: String) {
        _userProfile.value = _userProfile.value.copy(dob = dob)
    }

    fun updateProfilePictureUrl(url: String) {
        _userProfile.value = _userProfile.value.copy(profilePictureUrl = url)
    }

    fun saveUserProfile() {
        viewModelScope.launch {
            firebaseHelper.saveUserProfile(_userProfile.value, onSuccess = {
                // Handle success
            }, onFailure = {
                // Handle failure
            })
        }
    }

    private fun loadSuggestions() {
        viewModelScope.launch {
            firebaseHelper.getSuggestions { suggestions ->
                _interestSuggestions.value = suggestions.interestSuggestions
                _subjectSuggestions.value = suggestions.subjects
                _gameSuggestions.value = suggestions.games
            }
        }
    }
}
