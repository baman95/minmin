package com.example.minmin_v1.model

data class UserProfile(
    var displayName: String = "",
    var interests: String = "",
    var gender: String = "",
    var className: String = "",
    var favouriteSubject: String = "",
    var favouriteGame: String = "",
    var dob: String = "",
    var profilePictureUrl: String = ""
)
