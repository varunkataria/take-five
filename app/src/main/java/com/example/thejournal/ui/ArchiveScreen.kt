package com.example.thejournal.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thejournal.ui.theme.TheJournalTheme
import java.time.LocalDate
import java.time.YearMonth

/**
 * Archive screen to view calendar and past entries
 */
@Composable
fun ArchiveScreen(
    onDateClick: (LocalDate) -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ArchiveViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    ArchiveScreen(
        completedDates = uiState.completedDates,
        onDateClick = onDateClick,
        onCalendarNavigationClick = viewModel::onCalendarNavigationClick,
        onCloseClick = onCloseClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArchiveScreen(
    completedDates: List<LocalDate>?,
    onDateClick: (LocalDate) -> Unit,
    onCalendarNavigationClick: (YearMonth) -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Archive",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onCloseClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Close"
                        )
                    }
                }
            )
        }

    ) { paddingValues ->
        Calendar(
            completedDates = completedDates,
            onDateClick = onDateClick,
            onCalendarNavigationClick = onCalendarNavigationClick,
            modifier = modifier
                .padding(horizontal = 16.dp)
                .padding(paddingValues)
        )
    }

}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_7A)
@Composable
fun ArchiveScreenPreview() {
    TheJournalTheme {
        ArchiveScreen(
            onDateClick = {},
            onCloseClick = {}
        )
    }
}
