package com.example.thejournal.ui

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
class JournalViewModel @Inject constructor(
    private val addJournalEntryUseCase: AddJournalEntryUseCase,
    private val getJournalEntryByDateUseCase: GetJournalEntryByDateUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(JournalUiState())
    val uiState: StateFlow<JournalUiState> = _uiState

    init {
        loadJournalEntryForDate(Date())
    }

    private fun loadJournalEntryForDate(date: Date) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val entry = getJournalEntryByDateUseCase.execute(date)
            if (entry != null) {
                _uiState.value = _uiState.value.copy(
                    amazingThings = entry.amazingThings.map { it.description },
                    thingsToImprove = entry.thingsToImprove.map { it.description },
                    isLoading = false
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    amazingThings = listOf("", "", ""),
                    thingsToImprove = listOf(""),
                    isLoading = false
                )
            }
        }
    }
}
