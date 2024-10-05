package com.example.thejournal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thejournal.domain.AddJournalEntryUseCase
import com.example.thejournal.domain.GetJournalEntryByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
open class JournalViewModel @Inject constructor(
    private val addJournalEntryUseCase: AddJournalEntryUseCase,
    private val getJournalEntryByDateUseCase: GetJournalEntryByDateUseCase
) : ViewModel() {

    protected val _uiState = MutableStateFlow(JournalUiState())
    val uiState: StateFlow<JournalUiState> = _uiState

    init {
        loadJournalEntryForDate(LocalDate.now())
    }

    private fun loadJournalEntryForDate(date: LocalDate) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val entryWithDetails = getJournalEntryByDateUseCase.execute(date)
            val today = LocalDate.now() == date
            if (entryWithDetails != null) {
                _uiState.value = _uiState.value.copy(
                    completed = entryWithDetails.journalEntry.completed,
                    amazingThings = entryWithDetails.amazingThings.map { it.description },
                    thingsToImprove = entryWithDetails.thingsToImprove.map { it.description },
                    isToday = today,
                    isLoading = false
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    completed = false,
                    amazingThings = listOf("", "", ""),
                    thingsToImprove = listOf(""),
                    isToday = today,
                    isLoading = false
                )
            }
        }
    }

    fun submitJournalEntry(date: LocalDate, amazingThings: List<String>, thingsToImprove: List<String>) {
        viewModelScope.launch {
            addJournalEntryUseCase.execute(
                date = date,
                amazingThings = amazingThings,
                thingsToImprove = thingsToImprove
            )
            _uiState.value = _uiState.value.copy(completed = true)
        }
    }

    fun updateAmazingThing(index: Int, newText: String) {
        val updatedAmazingThings = _uiState.value.amazingThings.toMutableList()
        updatedAmazingThings[index] = newText
        _uiState.value = _uiState.value.copy(amazingThings = updatedAmazingThings)
    }

    fun updateThingToImprove(index: Int, newText: String) {
        val updatedThingsToImprove = _uiState.value.thingsToImprove.toMutableList()
        updatedThingsToImprove[index] = newText
        _uiState.value = _uiState.value.copy(thingsToImprove = updatedThingsToImprove)
    }
}
