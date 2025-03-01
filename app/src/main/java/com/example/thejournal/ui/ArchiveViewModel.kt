package com.example.thejournal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thejournal.data.JournalEntry
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
        viewModelScope.launch {
            val morningEntries = getAllCompletedJournalEntriesUseCase.getMorningEntries()
            val eveningEntries = getAllCompletedJournalEntriesUseCase.getEveningEntries()
            val completedMorningEntryDates = filterEntriesByDate(morningEntries, YearMonth.now())
            val completedEveningEntryDates = filterEntriesByDate(eveningEntries, YearMonth.now())
            val allEntries =
                (morningEntries + eveningEntries).sortedByDescending { it.details.date }

            _uiState.value = _uiState.value.copy(
                entries = allEntries,
                completedMorningEntryDates = completedMorningEntryDates,
                completedEveningEntryDates = completedEveningEntryDates,
            )
        }
    }

    fun onCalendarNavigationClick(yearMonth: YearMonth) {
        // get the list of completed dates for the given YearMonth
        viewModelScope.launch {
            val morningEntries = getAllCompletedJournalEntriesUseCase.getMorningEntries()
            val eveningEntries = getAllCompletedJournalEntriesUseCase.getEveningEntries()
            val completedMorningEntryDates = filterEntriesByDate(morningEntries, yearMonth)
            val completedEveningEntryDates = filterEntriesByDate(eveningEntries, yearMonth)
            _uiState.value = _uiState.value.copy(
                completedMorningEntryDates = completedMorningEntryDates,
                completedEveningEntryDates = completedEveningEntryDates,
            )
        }
    }

    private fun filterEntriesByDate(
        entries: List<JournalEntry>,
        yearMonth: YearMonth
    ): List<LocalDate> {
        val filteredEntries = entries.filter { entry ->
            val entryDetails = entry.details
            entryDetails.date.year == yearMonth.year && entryDetails.date.month == yearMonth.month
        }
        // Return the dates as a list of LocalDate
        return filteredEntries.map { it.details.date }
    }
}
