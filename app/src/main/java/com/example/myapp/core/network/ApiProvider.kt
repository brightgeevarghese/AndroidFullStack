package com.example.myapp.core.network

import com.example.myapp.data.remote.api.UserApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.104:8080/api/v1/")
//            .baseUrl("http://10.0.2.2:8080/api/v1/")
            //Configures Retrofit to use Gson for JSON serialization/deserialization.
            // This converts JSON responses (e.g., from your User and Profile endpoints) into Kotlin data classes (e.g., UserDto, ProfileDto) and vice versa.
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userApiService: UserApiService by lazy {
        retrofit.create<UserApiService>(UserApiService::class.java)
    }
}