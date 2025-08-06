package com.example.myapp.data.remote.api

import com.example.myapp.data.remote.dto.request.UserRequestDto
import com.example.myapp.data.remote.dto.response.UserResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {
    @GET("users")
    suspend fun getAllUsers(): List<UserResponseDto>
    @GET("users/{username}")
    suspend fun getUserByUsername(@Path("username") username: String): UserResponseDto
    @POST("users")
    suspend fun createUser(@Body userRequestDto: UserRequestDto): UserResponseDto
    @PUT("users/{username}")
    suspend fun updateUser(@Path("username") username: String, @Body userRequestDto: UserRequestDto): UserResponseDto
    @DELETE("users/{username}")
    suspend fun deleteUser(@Path("username") username: String)
//    @GET("users")
//    suspend fun getUser(@Query("username") username: String): UserResponseDto
//    GET http://10.0.2.2:8080/api/v1/users?username=johnsmith
}