package com.example.thejournal.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.DateFormat

@Composable
fun JournalScreen(viewModel: JournalViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var amazingThings by remember { mutableStateOf(uiState.amazingThings) }
    var thingsToImprove by remember { mutableStateOf(uiState.thingsToImprove) }

    Column(modifier = Modifier.padding(16.dp)) {

        val formattedDate = DateFormat.getDateInstance(
            DateFormat.DEFAULT
        ).format(uiState.date)

        Text(
            text = formattedDate,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        amazingThings.forEachIndexed { index, text ->
            OutlinedTextField(
                value = text,
                onValueChange = { newText ->
                    amazingThings = amazingThings.toMutableList().apply { this[index] = newText }
                },
                label = { Text("${index + 1}") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )
        }

        thingsToImprove.forEachIndexed { index, text ->
            OutlinedTextField(
                value = text,
                onValueChange = { newText ->
                    thingsToImprove = thingsToImprove.toMutableList().apply { this[index] = newText }
                },
                label = { Text("${index + 1}") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )
        }
    }
}
