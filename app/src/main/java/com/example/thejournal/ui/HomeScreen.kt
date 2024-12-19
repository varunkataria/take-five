package com.example.thejournal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thejournal.R
import com.example.thejournal.ui.theme.T5_DARK_BLUE
import com.example.thejournal.ui.theme.T5_MEDIUM_BLUE
import com.example.thejournal.ui.theme.T5_RED
import com.example.thejournal.ui.theme.T5_WHITE

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
    val uiState by viewModel.uiState.collectAsState()
    HomeScreen(
        name = uiState.name,
        isEveningCompleted = uiState.isEveningCompleted,
        onPromptClick = onPromptClick,
        onNavBarItemClick = onNavBarItemClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    name: String,
    isEveningCompleted: Boolean,
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
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            TopAppBar(
                title = {
                    Icon(
                        painter = painterResource(id = R.drawable.take_five_logo), // Replace with your logo's resource ID
                        contentDescription = "take five",
                        modifier = Modifier.size(125.dp), // Adjust size as needed
                        tint = T5_RED // Tint the logo red
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
                            0.4f to T5_MEDIUM_BLUE,
                            1f to T5_DARK_BLUE,
                        )
                    )
                )
//                .offset(y = offsetY.dp)
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Good evening, ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                        append(name)
                        append("!")
                    }
                },
                style = MaterialTheme.typography.displayMedium,
                color = T5_WHITE,
                modifier = Modifier.padding(top = 48.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Gap between boxes
            ) {
                PromptBox("morning prompt", false, Modifier.weight(1f))
                PromptBox("evening prompt", isEveningCompleted, Modifier.weight(1f), onPromptClick)
            }
        }
    }
}

@Composable
private fun PromptBox(
    title: String,
    completed: Boolean,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick?.invoke() }
            .background(if (completed) T5_WHITE else T5_WHITE.copy(alpha = 0.5f))
            .border(
                width = if (completed) 1.dp else 0.dp,
                color = if (completed) T5_RED else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = if (completed) T5_RED else T5_WHITE
            )
            Icon(
                imageVector = if (completed) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                contentDescription = title,
                tint = if (completed) T5_RED else T5_WHITE,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}


@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        name = "Varun",
        isEveningCompleted = true,
        onPromptClick = {},
        onNavBarItemClick = {},
        modifier = Modifier
    )
}
