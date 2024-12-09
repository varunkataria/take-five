package com.example.thejournal.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thejournal.R
import com.example.thejournal.ui.theme.T5_DARK
import com.example.thejournal.ui.theme.T5_RED

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
//    // Trigger the animation when the screen is opened
//    var triggerAnimation by remember { mutableStateOf(false) }
//
//    // Animate the offsetY based on triggerAnimation
//    val offsetY by animateFloatAsState(
//        targetValue = if (triggerAnimation) 0f else 32f, // Move text 32 pixels up
//        animationSpec = tween(durationMillis = 500) // Duration of the animation
//    )
//
//    // Launch the animation effect when the screen is first loaded
//    LaunchedEffect(Unit) {
//        triggerAnimation = true
//    }

    Scaffold(
        modifier = modifier.background(T5_RED),
        topBar = {
            TopAppBar(
                title = {
                    Icon(
                        painter = painterResource(id = R.drawable.take_five_logo), // Replace with your logo's resource ID
                        contentDescription = "take five",
                        modifier = Modifier.size(125.dp), // Adjust size as needed
                        tint = T5_DARK // Tint the logo red
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent // Ensure the top bar is also blue
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.Transparent
            ) {
                bottomNavRoutes.forEach { bottomNavRoute ->
                    NavigationBarItem(
                        selected = false,
                        onClick = { onNavBarItemClick(bottomNavRoute.route) },
                        icon = {
                            Box(
                                modifier = Modifier
                                    .height(150.dp)
                                    .fillMaxWidth(0.9f)
                                    .clip(RoundedCornerShape(16.dp)), // Adds rounded corners
                                contentAlignment = Alignment.Center // Centers the icon
                            ) {
                                Icon(
                                    imageVector = bottomNavRoute.icon,
                                    contentDescription = bottomNavRoute.name,
                                    tint = Color.White
                                )
                            }
                        },
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.5f to T5_RED,
                            1f to T5_DARK,
                        )
                    )
                )
//                .offset(y = offsetY.dp)
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = "Good evening, $name!",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.padding(top = 48.dp)
            )
            Button(
                onClick = onPromptClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {
                Text(
                    text = "evening prompt",
                    style = MaterialTheme.typography.headlineMedium,
                    color = T5_RED,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        name = "Varun",
        onPromptClick = {},
        onNavBarItemClick = {},
        modifier = Modifier
    )
}
