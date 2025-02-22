package com.example.thejournal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thejournal.domain.GetJournalEntryByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

/**
 * ViewModel for [HomeScreen]
 */
@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val getJournalEntryByDateUseCase: GetJournalEntryByDateUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    val name = "Varun"

    init {
        // Collect morning entry updates
        viewModelScope.launch {
            getJournalEntryByDateUseCase.getMorningEntry(LocalDate.now()).collect { morningEntry ->
                _uiState.value = _uiState.value.copy(
                    isMorningCompleted = morningEntry?.journalEntry?.completed ?: false
                )
            }
        }

        // Collect evening entry updates
        viewModelScope.launch {
            getJournalEntryByDateUseCase.getEveningEntry(LocalDate.now()).collect { eveningEntry ->
                _uiState.value = _uiState.value.copy(
                    isEveningCompleted = eveningEntry?.journalEntry?.completed ?: false,
                    name = name
                )
            }
        }
    }
}