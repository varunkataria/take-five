package com.example.thejournal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thejournal.domain.GetCompletedJournalEntryDatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.YearMonth
import javax.inject.Inject

/**
 * ViewModel for [ArchiveScreen]
 */
@HiltViewModel
open class ArchiveViewModel @Inject constructor(
    private val getCompletedJournalEntryDatesUseCase: GetCompletedJournalEntryDatesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArchiveUiState())
    val uiState: StateFlow<ArchiveUiState> = _uiState

    init {
        // Initialize completedDates with the current month's completed dates
        viewModelScope.launch {
            val completedDates = getCompletedJournalEntryDatesUseCase.execute(YearMonth.now())
            _uiState.value = _uiState.value.copy(
                completedDates = completedDates
            )
        }
    }

    fun onCalendarNavigationClick(yearMonth: YearMonth) {
        // get the list of completed dates for the given YearMonth
        viewModelScope.launch {
            val completedDates = getCompletedJournalEntryDatesUseCase.execute(yearMonth)
            _uiState.value = _uiState.value.copy(
                completedDates = completedDates
            )
        }
    }
}
