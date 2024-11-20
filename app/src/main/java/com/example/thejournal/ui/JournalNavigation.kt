package com.example.thejournal.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

/**
 * Navigation graph for the Journal screens
 */
@OptIn(ExperimentalMaterial3Api::class)
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
            if (journal.isBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { navController.popBackStack() },
                    sheetState = rememberModalBottomSheetState(),
                    content = {
                        JournalScreen(
                            onDateClick = { navController.navigate(route = Archive) },
                            onCloseClick = { navController.popBackStack() }
                        )
                    }
                )
            } else {
                JournalScreen(
                    onDateClick = { navController.navigate(route = Archive) },
                    onCloseClick = { navController.popBackStack() }
                )
            }
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