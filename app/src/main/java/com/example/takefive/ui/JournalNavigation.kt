package com.example.takefive.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.takefive.ui.archive.ArchiveScreen
import com.example.takefive.ui.home.HomeScreen
import com.example.takefive.ui.journal.JournalScreen

/**
 * Navigation graph for the Journal screens
 */
@Composable
fun JournalNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(
                onPromptClick = { entryType -> navController.navigate(route = Journal(entryType = entryType)) },
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
                onDateClick = { date, entryType ->
                    navController.navigate(
                        route = Journal(
                            entryType = entryType,
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