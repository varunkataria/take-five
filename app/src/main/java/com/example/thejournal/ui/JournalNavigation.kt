package com.example.thejournal.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun JournalNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "journal") {
        composable("journal") { JournalScreen(onDateClick = { navController.navigate("calendar") }) }
        composable("calendar") { CalendarScreen() }
    }
}