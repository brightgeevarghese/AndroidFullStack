package com.example.myapp.core.di

import com.example.myapp.domain.repository.UserRepository

interface AppContainer {
    val userRepository: UserRepository
}