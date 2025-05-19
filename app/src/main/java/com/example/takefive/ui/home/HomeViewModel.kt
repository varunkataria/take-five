package com.example.takefive.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.takefive.data.user.UserDetailsRepository
import com.example.takefive.domain.GetJournalEntryByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

/**
 * ViewModel for [HomeScreen]
 */
@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val getJournalEntryByDateUseCase: GetJournalEntryByDateUseCase,
    private val userDetailsRepository: UserDetailsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        // Collect morning entry updates
        viewModelScope.launch {
            getJournalEntryByDateUseCase.getMorningEntry(LocalDate.now())
                .collect { morningEntry ->
                    _uiState.update {
                        it.copy(
                            isMorningCompleted = morningEntry?.details?.completed ?: false
                        )
                    }
                }
        }

        // Collect evening entry updates
        viewModelScope.launch {
            getJournalEntryByDateUseCase.getEveningEntry(LocalDate.now())
                .collect { eveningEntry ->
                    _uiState.update {
                        it.copy(
                            isEveningCompleted = eveningEntry?.details?.completed ?: false
                        )
                    }
                }
        }

        // Collect updates for the user's name
        viewModelScope.launch {
            userDetailsRepository.getUserDetails().collectLatest { userDetails ->
                _uiState.update {
                    it.copy(
                        name = userDetails?.name.orEmpty()
                    )
                }
            }
        }
    }

    fun onNameChange(newName: String) {
        // Update the UI state immediately (optimistically update before the database call completes)
        _uiState.value = _uiState.value.copy(name = newName)
        viewModelScope.launch {
            userDetailsRepository.updateUserDetails(newName = newName)
        }
    }
}