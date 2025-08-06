package com.example.myapp.data.remote.dto.request

data class ProfileRequestDto(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String, // Format: yyyy-MM-dd
    val email: String,
    val phoneNumber: String,
    val bio: String
)
