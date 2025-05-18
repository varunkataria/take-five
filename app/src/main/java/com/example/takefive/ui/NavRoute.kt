package com.example.takefive.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.takefive.data.EntryType
import kotlinx.serialization.Serializable

data class NavRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

val bottomNavRoutes = listOf(
    NavRoute("settings", Home, Icons.Filled.Settings),
    NavRoute("journal", Journal(), Icons.Filled.Edit),
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
