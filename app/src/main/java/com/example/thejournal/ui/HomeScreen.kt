package com.example.thejournal.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
    onNavBarItemClick: (Any) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(
        name = viewModel.name,
        onPromptClick = onPromptClick,
        onNavBarItemClick = onNavBarItemClick,
        modifier = modifier
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    name: String,
    onPromptClick: () -> Unit,
    onNavBarItemClick: (Any) -> Unit,
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
        },
        bottomBar = {
            NavigationBar {
                bottomNavRoutes.forEach { bottomNavRoute ->
                    NavigationBarItem(
                        selected = false,
                        onClick = { onNavBarItemClick(bottomNavRoute.route) },
                        icon = {
                            Icon(bottomNavRoute.icon, bottomNavRoute.name)
                        }
                    )
                }
            }
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
