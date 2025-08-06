package com.example.myapp.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapp.data.remote.dto.response.UserResponseDto
import com.example.myapp.presentation.viewmodel.UserListViewModel

@Composable
fun UserListScreen(viewModel: UserListViewModel = UserListViewModel()) {
    // Observe the current state of users, error, and loading
    val users: List<UserResponseDto> by viewModel.users.collectAsState()
    val error: String? by viewModel.error.collectAsState()
    val isLoading: Boolean by viewModel.isLoading.collectAsState()

    // Trigger fetchUsers() only once when the screen is first composed
    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }

    // Main screen layout using Scaffold for padding and structure
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn (
            modifier = Modifier
                .padding(innerPadding)
        ) {
            when {
                isLoading -> {
                    // Show loading indicator while data is being fetched
                    item { CircularProgressIndicator() }
                }
                error != null -> {
                    // Show a message if no users were found
                    item { Text(text = "Error = $error", color = MaterialTheme.colorScheme.error) }
                }
                users.isEmpty() -> {
                    // Show a message if no users were found
                    item { Text(text = "No users available") }
                }
                else -> {
                    items(users) {
                        // Render each user using UserCard
                        user -> UserCard(user)
                    }
                }
            }
        }
    }
}


@Composable
fun UserCard(user: UserResponseDto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f) // This makes the Column take available width
                ) {
                    Text(text = user.username)
                    Text(text = user.profileResponseDto.firstName)
                    Text(text = user.profileResponseDto.bio)
                }
                Spacer(modifier = Modifier.width(8.dp))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://lh3.googleusercontent.com/d/1HNMuVKyHb35R-x47xk-h2DDY00Pv4sGD=w600-h600")
                        .size(200)
                        .build(),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(60.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}