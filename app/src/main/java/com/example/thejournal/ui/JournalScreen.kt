package com.example.thejournal.ui

import androidx.compose.foundation.clickable
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thejournal.ui.theme.TheJournalTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun JournalScreen(onDateClick: () -> Unit, viewModel: JournalViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    JournalScreen(
        uiState = uiState,
        onDateClick = onDateClick,
        onAmazingThingTextChange = viewModel::updateAmazingThing,
        onThingToImproveTextChange = viewModel::updateThingToImprove,
        onSubmitClick = viewModel::submitJournalEntry
    )
}

@Composable
private fun JournalScreen(
    uiState: JournalUiState,
    onDateClick: () -> Unit,
    onAmazingThingTextChange: (index: Int, newText: String) -> Unit,
    onThingToImproveTextChange: (index: Int, newText: String) -> Unit,
    onSubmitClick: (date: LocalDate, amazingThings: List<String>, thingsToImprove: List<String>) -> Unit
) {
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
            modifier = Modifier.clickable { onDateClick() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Amazing things title
        Text(
            text = "What are three amazing things that happened today?",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        // Amazing things text fields
        uiState.amazingThings.forEachIndexed { index, text ->
            OutlinedTextField(
                value = text,
                readOnly = uiState.completed,
                onValueChange = { newText ->
                    onAmazingThingTextChange(index, newText)
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
        uiState.thingsToImprove.forEachIndexed { index, text ->
            OutlinedTextField(
                value = text,
                onValueChange = { newText ->
                    onThingToImproveTextChange(index, newText)
                },
                readOnly = uiState.completed,
                label = if (uiState.thingsToImprove.size > 1) {
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
                    onSubmitClick(
                        uiState.date,
                        uiState.amazingThings,
                        uiState.thingsToImprove
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
        JournalScreen(
            uiState = JournalUiState(
                completed = false,
                date = LocalDate.now(),
                amazingThings = listOf(
                    "I saw The Wild Robot movie and it was really great",
                    "I got home safe from Seattle",
                    "I spent time with my beautiful, charming, amazing, gorgeous, funny boyfriend"
                ),
                thingsToImprove = listOf("I could have saved money on lunch and made food at home."),
                isToday = true,
                isLoading = false
            ),
            onDateClick = {},
            onAmazingThingTextChange = { _, _ -> },
            onThingToImproveTextChange = { _, _ -> },
            onSubmitClick = { _, _, _ -> }
        )
    }
}
