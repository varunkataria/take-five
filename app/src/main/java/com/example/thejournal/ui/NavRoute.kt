package com.example.thejournal.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.thejournal.data.EntryType
import kotlinx.serialization.Serializable

data class NavRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

val bottomNavRoutes = listOf(
    NavRoute("journal", Journal(), Icons.Filled.Edit),
    NavRoute("home", Home, Icons.Filled.Home),
    NavRoute("archive", Archive, Icons.Filled.DateRange)
)

@Serializable
object Home

@Serializable
data class Journal(
    val entryType: EntryType = EntryType.EVENING,
    val date: String? = null,
    val isBottomSheet: Boolean = false
)

@Serializable
object Archive
