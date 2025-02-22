package com.example.thejournal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thejournal.data.EveningEntry
import com.example.thejournal.domain.GetAllCompletedJournalEntriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject

/**
 * ViewModel for [ArchiveScreen]
 */
@HiltViewModel
open class ArchiveViewModel @Inject constructor(
    private val getAllCompletedJournalEntriesUseCase: GetAllCompletedJournalEntriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArchiveUiState())
    val uiState: StateFlow<ArchiveUiState> = _uiState

    init {
        // Initialize completedDates with the current month's completed dates
        viewModelScope.launch {
            val journalEntries = getAllCompletedJournalEntriesUseCase.execute()
            val completedDates = filterEntriesByDate(journalEntries, YearMonth.now())
            _uiState.value = _uiState.value.copy(
                completedDates = completedDates,
                journalEntries = journalEntries.reversed()
            )
        }
    }

    fun onCalendarNavigationClick(yearMonth: YearMonth) {
        // get the list of completed dates for the given YearMonth
        viewModelScope.launch {
            val journalEntries = getAllCompletedJournalEntriesUseCase.execute()
            val completedDates = filterEntriesByDate(journalEntries, yearMonth)
            _uiState.value = _uiState.value.copy(
                completedDates = completedDates
            )
        }
    }

    private fun filterEntriesByDate(
        entries: List<EveningEntry>,
        yearMonth: YearMonth
    ): List<LocalDate> {
        val filteredEntries = entries.filter { entry ->
            entry.journalEntry.date.year == yearMonth.year && entry.journalEntry.date.month == yearMonth.month
        }
        // Return the dates as a list of LocalDate
        return filteredEntries.map { it.journalEntry.date }
    }
}
