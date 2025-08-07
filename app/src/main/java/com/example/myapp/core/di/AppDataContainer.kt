package com.example.myapp.core.di

import com.example.myapp.core.network.NetworkModule
import com.example.myapp.data.repository.UserRepositoryImpl
import com.example.myapp.domain.repository.UserRepository

class AppDataContainer: AppContainer {
    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(
            userApiService = NetworkModule.userApiService
        )

    }
}