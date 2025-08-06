package com.example.myapp.domain.repository

import com.example.myapp.data.remote.dto.request.UserRequestDto
import com.example.myapp.data.remote.dto.response.UserResponseDto

interface UserRepository {
    suspend fun getAllUsers(): List<UserResponseDto>
    suspend fun registerUser(userRequestDto: UserRequestDto): UserResponseDto
    suspend fun updateUser(username: String, userRequestDto: UserRequestDto): UserResponseDto
    suspend fun deleteUser(username: String)
}