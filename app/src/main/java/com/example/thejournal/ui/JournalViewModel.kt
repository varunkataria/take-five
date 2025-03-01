package com.example.thejournal.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.thejournal.data.EntryType
import com.example.thejournal.data.SubmissionItemType
import com.example.thejournal.domain.AddJournalEntryUseCase
import com.example.thejournal.domain.GetJournalEntryByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

/**
 * ViewModel for [JournalScreen]
 */
@HiltViewModel
open class JournalViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val addJournalEntryUseCase: AddJournalEntryUseCase,
    private val getJournalEntryByDateUseCase: GetJournalEntryByDateUseCase
) : ViewModel() {

    private val journal: Journal = savedStateHandle.toRoute()
    private val date = journal.date?.let { LocalDate.parse(it) } ?: LocalDate.now()
    private val entryType = journal.entryType

    private val _uiState = MutableStateFlow(
        JournalUiState(
            isMorningEntry = entryType == EntryType.MORNING,
            date = date,
            isToday = LocalDate.now() == date
        )
    )
    val uiState: StateFlow<JournalUiState> = _uiState

    init {
        loadJournalEntryForDate(date, entryType)
    }

    private fun loadJournalEntryForDate(date: LocalDate, entryType: EntryType) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            when (entryType) {
                EntryType.MORNING -> getJournalEntryByDateUseCase.getMorningEntry(date)
                    .collect { morningEntry ->
                        morningEntry?.let {
                            _uiState.value = _uiState.value.copy(
                                completed = morningEntry.details.completed,
                                intentions = morningEntry.intentions.map { it.description },
                                gratefulThings = morningEntry.gratefulThings.map { it.description },
                                isLoading = false
                            )
                        }
                    }

                EntryType.EVENING -> getJournalEntryByDateUseCase.getEveningEntry(date)
                    .collect { eveningEntry ->
                        eveningEntry?.let {
                            _uiState.value = _uiState.value.copy(
                                completed = eveningEntry.details.completed,
                                amazingThings = eveningEntry.amazingThings.map { it.description },
                                thingsToImprove = eveningEntry.thingsToImprove.map { it.description },
                                isLoading = false
                            )
                        }
                    }
            }
        }
    }

    fun submitJournalEntry() {
        viewModelScope.launch {
            if (_uiState.value.isMorningEntry) {
                addJournalEntryUseCase.addMorningEntry(
                    date = uiState.value.date,
                    intentions = uiState.value.intentions,
                    gratefulThings = uiState.value.gratefulThings,
                )
            } else {
                addJournalEntryUseCase.addEveningEntry(
                    date = uiState.value.date,
                    amazingThings = uiState.value.amazingThings,
                    thingsToImprove = uiState.value.thingsToImprove,
                )
            }
            _uiState.value = _uiState.value.copy(completed = true)
        }
    }

    fun updateSubmissionItem(type: SubmissionItemType, index: Int, newText: String) {
        _uiState.value = _uiState.value.run {
            when (type) {
                SubmissionItemType.AMAZING_THING -> copy(
                    amazingThings = amazingThings.toMutableList().apply { this[index] = newText }
                )

                SubmissionItemType.THING_TO_IMPROVE -> copy(
                    thingsToImprove = thingsToImprove.toMutableList()
                        .apply { this[index] = newText }
                )

                SubmissionItemType.INTENTION -> copy(
                    intentions = intentions.toMutableList().apply { this[index] = newText }
                )

                SubmissionItemType.GRATEFUL_THING -> copy(
                    gratefulThings = gratefulThings.toMutableList().apply { this[index] = newText }
                )
            }
        }
    }
}
