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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thejournal.ui.theme.TheJournalTheme
import java.time.LocalDate

/**
 * Calendar screen to select a date
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchiveScreen(
    onDateClick: (LocalDate) -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
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
            onDateClick = onDateClick,
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
