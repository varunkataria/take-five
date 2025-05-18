package com.example.takefive.ui.archive

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.takefive.data.EntryDetails
import com.example.takefive.data.EntryType
import com.example.takefive.data.EveningEntry
import com.example.takefive.data.MorningEntry
import com.example.takefive.data.JournalEntry
import com.example.takefive.ui.theme.T5_DARK_BLUE
import com.example.takefive.ui.theme.T5_LIGHT_BLUE
import com.example.takefive.ui.theme.T5_RED
import com.example.takefive.ui.theme.T5_WHITE
import com.example.takefive.ui.theme.TheJournalTheme
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

/**
 * Archive screen to view calendar and past entries
 */
@Composable
fun ArchiveScreen(
    onDateClick: (LocalDate, EntryType) -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ArchiveViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    ArchiveScreen(
        completedMorningEntryDates = uiState.completedMorningEntryDates,
        completedEveningEntryDates = uiState.completedEveningEntryDates,
        journalEntries = uiState.entries,
        onDateClick = onDateClick,
        onCalendarNavigationClick = viewModel::onCalendarNavigationClick,
        onCloseClick = onCloseClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArchiveScreen(
    completedMorningEntryDates: List<LocalDate>?,
    completedEveningEntryDates: List<LocalDate>?,
    journalEntries: List<JournalEntry>,
    onDateClick: (LocalDate, EntryType) -> Unit,
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
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = T5_DARK_BLUE
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onCloseClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Close",
                            tint = T5_DARK_BLUE
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent // Ensure the top bar is also blue
                )
            )
        }

    ) { paddingValues ->
        LazyColumn(
            modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.5f to T5_WHITE,
                            1f to T5_RED,
                        )
                    )
                )
                .padding(horizontal = 16.dp)
                .padding(paddingValues)
        ) {
            item {
                Calendar(
                    completedMorningEntryDates = completedMorningEntryDates,
                    completedEveningEntryDates = completedEveningEntryDates,
                    onDateClick = onDateClick,
                    onCalendarNavigationClick = onCalendarNavigationClick
                )
            }
            item {
                Text(
                    text = "Recent Entries",
                    modifier = Modifier
                        .padding(top = 32.dp, bottom = 8.dp)
                        .animateItem(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = T5_RED
                )
            }
            items(journalEntries, key = { it.details.id }) { entry ->
                JournalEntryBox(
                    onDateClick = onDateClick,
                    entry = entry,
                    modifier = Modifier.animateItem()
                )
            }
        }
    }
}

@Composable
private fun JournalEntryBox(
    onDateClick: (LocalDate, EntryType) -> Unit,
    entry: JournalEntry,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onDateClick(entry.details.date, entry.details.entryType) }
            .background(color = T5_WHITE)
            .border(
                width = 1.dp,
                color = T5_RED.copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(8.dp)
                    .background(
                        when (entry.details.entryType) {
                            EntryType.MORNING -> T5_LIGHT_BLUE
                            EntryType.EVENING -> T5_DARK_BLUE
                        }
                    )
            )
            Column {
                val formattedDate =
                    entry.details.date.format(DateTimeFormatter.ofPattern("M/d/yyyy"))
                when (entry) {
                    is MorningEntry -> {
                        Text(
                            text = "$formattedDate: Morning",
                            modifier = modifier.padding(top = 8.dp, start = 12.dp, end = 16.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = T5_RED
                        )
                        Text(
                            text = entry.gratefulThings[0].description,
                            modifier = modifier.padding(
                                top = 4.dp,
                                bottom = 8.dp,
                                start = 12.dp,
                                end = 16.dp
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = T5_DARK_BLUE
                        )
                    }

                    is EveningEntry -> {
                        Text(
                            text = "$formattedDate: Evening",
                            modifier = modifier.padding(top = 8.dp, start = 12.dp, end = 16.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = T5_RED
                        )
                        Text(
                            text = entry.amazingThings[0].description,
                            modifier = modifier.padding(
                                top = 4.dp,
                                bottom = 8.dp,
                                start = 12.dp,
                                end = 16.dp
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = T5_DARK_BLUE
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_7A)
@Composable
fun ArchiveScreenPreview() {
    TheJournalTheme {
        ArchiveScreen(
            completedMorningEntryDates = emptyList(),
            completedEveningEntryDates = emptyList(),
            journalEntries = listOf(
                MorningEntry(
                    details = EntryDetails(
                        id = 0,
                        date = LocalDate.now(),
                        entryType = EntryType.MORNING
                    ),
                    gratefulThings = emptyList(),
                    intentions = emptyList()
                )
            ),
            onDateClick = { _, _ -> },
            onCalendarNavigationClick = {},
            onCloseClick = {}
        )
    }
}
