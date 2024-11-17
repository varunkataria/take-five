package com.example.thejournal.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for [HomeScreen]
 */
@HiltViewModel
open class HomeViewModel @Inject constructor() : ViewModel() {
    val name = "Varun"
}
