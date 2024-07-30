package com.example.thejournal.ui

import android.text.format.DateUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thejournal.domain.AddJournalEntryUseCase
import com.example.thejournal.domain.GetJournalEntryByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
open class JournalViewModel @Inject constructor(
    private val addJournalEntryUseCase: AddJournalEntryUseCase?,
    private val getJournalEntryByDateUseCase: GetJournalEntryByDateUseCase?
) : ViewModel() {

    protected val _uiState = MutableStateFlow(JournalUiState())
    val uiState: StateFlow<JournalUiState> = _uiState

    init {
        loadJournalEntryForDate(Date())
    }

    private fun loadJournalEntryForDate(date: Date) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val entryWithDetails = getJournalEntryByDateUseCase?.execute(date)
            val today = DateUtils.isToday(date.time)
            if (entryWithDetails != null) {
                _uiState.value = _uiState.value.copy(
                    completed = entryWithDetails.journalEntry.completed,
                    amazingThings = entryWithDetails.amazingThings.map { it.description },
                    thingsToImprove = entryWithDetails.thingsToImprove.map { it.description },
                    isCurrentDayToday = today,
                    isLoading = false
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    completed = false,
                    amazingThings = listOf("", "", ""),
                    thingsToImprove = listOf(""),
                    isCurrentDayToday = today,
                    isLoading = false
                )
            }
        }
    }

    fun submitJournalEntry(date: Date, amazingThings: List<String>, thingsToImprove: List<String>) {
        viewModelScope.launch {
            addJournalEntryUseCase?.execute(
                date = date,
                amazingThings = amazingThings,
                thingsToImprove = thingsToImprove
            )
            _uiState.value = _uiState.value.copy(completed = true)
        }
    }
}
