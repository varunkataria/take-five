package com.example.thejournal.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thejournal.ui.theme.TheJournalTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun JournalScreen(viewModel: JournalViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var amazingThings by remember { mutableStateOf(uiState.amazingThings) }
    var thingsToImprove by remember { mutableStateOf(uiState.thingsToImprove) }

    Column(
        modifier = Modifier.safeContentPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Date
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
        val formattedDate = uiState.date.format(formatter)

        Text(
            text = formattedDate,
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Amazing things title
        Text(
            text = "3 Amazing things that happened today...",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        // Amazing things text fields
        amazingThings.forEachIndexed { index, text ->
            OutlinedTextField(
                value = text,
                onValueChange = { newText ->
                    amazingThings = amazingThings.toMutableList().apply { this[index] = newText }
                },
                label = { Text("${index + 1}") },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Improve day title
        Text(
            text = "How could I have made today even better?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        // Improve day text fields
        thingsToImprove.forEachIndexed { index, text ->
            OutlinedTextField(
                value = text,
                onValueChange = { newText ->
                    thingsToImprove =
                        thingsToImprove.toMutableList().apply { this[index] = newText }
                },
                label = if (thingsToImprove.size > 1) {
                    { Text("${index + 1}") }
                } else null,
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 100.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Save button
        if (uiState.isToday) {
            Button(
                enabled = !uiState.completed,
                onClick = {
                    viewModel.submitJournalEntry(
                        date = uiState.date,
                        amazingThings = amazingThings,
                        thingsToImprove = thingsToImprove
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Submit",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JournalScreenPreview() {
    TheJournalTheme {
        JournalScreen(viewModel = FakeJournalViewModel())
    }
}

class FakeJournalViewModel : JournalViewModel(null, null) {
    init {
        _uiState.value = JournalUiState(
            completed = false,
            date = LocalDate.now(),
            amazingThings = listOf("Amazing Thing 1", "Amazing Thing 2", "Amazing Thing 3"),
            thingsToImprove = listOf("Thing to Improve 1"),
            isToday = true,
            isLoading = false
        )
    }
}