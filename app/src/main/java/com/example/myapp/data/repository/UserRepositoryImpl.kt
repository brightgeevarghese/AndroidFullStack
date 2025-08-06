package com.example.myapp.data.repository

import com.example.myapp.data.remote.api.UserApiService
import com.example.myapp.data.remote.dto.request.UserRequestDto
import com.example.myapp.data.remote.dto.response.UserResponseDto
import com.example.myapp.domain.repository.UserRepository

class UserRepositoryImpl(private val userApiService: UserApiService): UserRepository {

    override suspend fun getAllUsers(): List<UserResponseDto> = userApiService.getAllUsers()

    override suspend fun registerUser(userRequestDto: UserRequestDto): UserResponseDto =
        userApiService.createUser(userRequestDto)

    override suspend fun updateUser(
        username: String,
        userRequestDto: UserRequestDto
    ): UserResponseDto = userApiService.updateUser(username, userRequestDto)

    override suspend fun deleteUser(username: String) = userApiService.deleteUser(username)

}