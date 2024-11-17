package com.example.thejournal.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Home screen
 */
@Composable
fun HomeScreen(
    onPromptClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(
        name = viewModel.name,
        onPromptClick = onPromptClick,
        modifier = modifier
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    name: String,
    onPromptClick: () -> Unit,
    modifier: Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("take five")
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier.padding(paddingValues),
        ) {
            Text(
                text = "Good morning, $name!",
                style = MaterialTheme.typography.titleLarge,
            )
            Button(
                onClick = onPromptClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "evening prompt",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}
