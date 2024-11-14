package com.example.thejournal.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
data class Journal(val date: String? = null)

@Serializable
object Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Journal()) {
        composable<Journal> {
            JournalScreen(
                onDateClick = { navController.navigate(route = Calendar) }
            )
        }
        composable<Calendar> {
            ModalBottomSheet(
                onDismissRequest = { navController.popBackStack() },
                sheetState = rememberModalBottomSheetState(),
                content = {
                    CalendarScreen(onDateClick = { date ->
                        navController.navigate(
                            route = Journal(date = date.toString())
                        )
                    })
                }
            )
        }
    }
}