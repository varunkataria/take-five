package com.example.thejournal.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "journal") {
        composable("journal") { JournalScreen(onDateClick = { navController.navigate("calendar") }) }
        composable("calendar") {
            ModalBottomSheet(
                onDismissRequest = { navController.popBackStack() },
                sheetState = rememberModalBottomSheetState(),
                content = { CalendarScreen() }
            )
        }
    }
}