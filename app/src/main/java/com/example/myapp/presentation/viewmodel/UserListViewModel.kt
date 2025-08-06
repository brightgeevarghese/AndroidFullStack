package com.example.myapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.core.network.ApiProvider
import com.example.myapp.data.remote.dto.response.UserResponseDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for managing the UI state of the user list.
 *
 * This ViewModel fetches data from a remote API using Retrofit and exposes three observable
 * [StateFlow] properties:
 * - [users]: List of users to be displayed
 * - [error]: Error message if the API call fails
 * - [isLoading]: Indicates whether the data is currently being loaded
 *
 * The data is fetched asynchronously in [fetchUsers] using Kotlin coroutines.
 */
class UserListViewModel: ViewModel() {
    // Backing property for users list
    private val _users = MutableStateFlow<List<UserResponseDto>>(emptyList())
    //Exposes a read-only flow of users that the UI can observe.
    val users = _users.asStateFlow()
    // Backing property for error messages
    private val _error = MutableStateFlow<String?>(null)
    //Exposes a read-only flow for error messages to notify the UI in case of failure.
    val error = _error.asStateFlow()
    // Backing property to indicate loading state
    private val _isLoading = MutableStateFlow<Boolean>(false)
    //Exposes a read-only flow that represents the loading state of the data fetch operation.
    val isLoading = _isLoading.asStateFlow()

    /**
     * Fetches the list of users from the remote API using a coroutine scope.
     *
     * This function sets [isLoading] to true while the request is ongoing.
     * On success, it updates [users]; on failure, it sets [error].
     * Regardless of outcome, [isLoading] is set to false at the end.
     */
    fun fetchUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _users.value = ApiProvider.userApiService.getAllUsers()
            } catch (exception: Exception) {
                _error.value = exception.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }
}