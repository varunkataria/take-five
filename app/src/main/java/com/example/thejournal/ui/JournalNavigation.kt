package com.example.thejournal.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

/**
 * Navigation graph for the Journal screens
 */
@Composable
fun JournalNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(
                onPromptClick = { navController.navigate(route = Journal()) },
                onNavBarItemClick = { route -> navController.navigate(route) }
            )
        }
        composable<Journal> { backStackEntry ->
            val journal = backStackEntry.toRoute<Journal>()
            JournalScreen(
                onCloseClick = { navController.popBackStack() },
                isBottomSheet = journal.isBottomSheet
            )
        }
        composable<Archive> {
            ArchiveScreen(
                onDateClick = { date ->
                    navController.navigate(
                        route = Journal(
                            date = date.toString(),
                            isBottomSheet = true
                        )
                    )
                },
                onCloseClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}