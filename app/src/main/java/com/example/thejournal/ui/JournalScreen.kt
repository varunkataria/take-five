package com.example.thejournal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thejournal.ui.theme.T5_DARK
import com.example.thejournal.ui.theme.T5_DARK_BLUE
import com.example.thejournal.ui.theme.T5_RED
import com.example.thejournal.ui.theme.T5_WHITE
import com.example.thejournal.ui.theme.TheJournalTheme
import java.time.LocalDate

/**
 * Evening Journal entry screen
 */
@Composable
fun JournalScreen(
    onCloseClick: () -> Unit,
    isBottomSheet: Boolean,
    modifier: Modifier = Modifier,
    viewModel: JournalViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    if (isBottomSheet) {
        JournalBottomSheet(
            uiState = uiState,
            onCloseClick = onCloseClick,
            onAmazingThingTextChange = viewModel::updateAmazingThing,
            onThingToImproveTextChange = viewModel::updateThingToImprove,
            onSubmitClick = viewModel::submitJournalEntry,
            modifier = modifier
        )
    } else {
        JournalFullScreen(
            uiState = uiState,
            onCloseClick = onCloseClick,
            onAmazingThingTextChange = viewModel::updateAmazingThing,
            onThingToImproveTextChange = viewModel::updateThingToImprove,
            onSubmitClick = viewModel::submitJournalEntry,
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun JournalBottomSheet(
    uiState: JournalUiState,
    onCloseClick: () -> Unit,
    onAmazingThingTextChange: (index: Int, newText: String) -> Unit,
    onThingToImproveTextChange: (index: Int, newText: String) -> Unit,
    onSubmitClick: (date: LocalDate, amazingThings: List<String>, thingsToImprove: List<String>) -> Unit,
    modifier: Modifier
) {
    ModalBottomSheet(
        onDismissRequest = { onCloseClick() },
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = T5_DARK,
        content = {
            Column(
                modifier = Modifier
                    .wrapContentHeight() // Ensure the bottom sheet wraps the content height
                    .nestedScroll(rememberNestedScrollInteropConnection())
//                    .padding(16.dp) // Optional: add padding to the sheet
            ) {
                JournalScreen(
                    uiState = uiState,
                    onAmazingThingTextChange = onAmazingThingTextChange,
                    onThingToImproveTextChange = onThingToImproveTextChange,
                    onSubmitClick = onSubmitClick,
                    modifier = modifier
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun JournalFullScreen(
    uiState: JournalUiState,
    onCloseClick: () -> Unit,
    onAmazingThingTextChange: (index: Int, newText: String) -> Unit,
    onThingToImproveTextChange: (index: Int, newText: String) -> Unit,
    onSubmitClick: (date: LocalDate, amazingThings: List<String>, thingsToImprove: List<String>) -> Unit,
    modifier: Modifier
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Evening Prompt",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = T5_WHITE
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onCloseClick) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = T5_WHITE
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = T5_DARK_BLUE // Ensure the top bar is also blue
                )
            )
        }
    ) { paddingValues ->
        JournalScreen(
            uiState = uiState,
            onAmazingThingTextChange = onAmazingThingTextChange,
            onThingToImproveTextChange = onThingToImproveTextChange,
            onSubmitClick = onSubmitClick,
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}


@Composable
private fun JournalScreen(
    uiState: JournalUiState,
    onAmazingThingTextChange: (index: Int, newText: String) -> Unit,
    onThingToImproveTextChange: (index: Int, newText: String) -> Unit,
    onSubmitClick: (date: LocalDate, amazingThings: List<String>, thingsToImprove: List<String>) -> Unit,
    modifier: Modifier
) {
    val scrollState = rememberScrollState() // State for scrolling

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .background(
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.4f to T5_DARK_BLUE,
                        1f to T5_RED,
                    )
                )
            )
            .imePadding()
            .padding(horizontal = 32.dp)
    ) {

        // Amazing things title
        Text(
            text = "Good things that happened today:",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = T5_WHITE,
            modifier = Modifier.padding(top = 32.dp, bottom = 4.dp)
        )
        // Amazing things text fields
        uiState.amazingThings.forEachIndexed { index, text ->
            OutlinedTextField(
                value = text,
                minLines = 2,
                readOnly = uiState.completed,
                onValueChange = { newText ->
                    onAmazingThingTextChange(index, newText)
                },
//                    label = { Text("${index + 1}") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = if (index == uiState.amazingThings.lastIndex) ImeAction.Done else ImeAction.Next
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = T5_WHITE, // Sets the background color
                    focusedContainerColor = T5_WHITE // Sets the background color
                ),
                shape = RoundedCornerShape(8.dp)
            )
        }

        // Improve day title
        Text(
            text = "Things to improve upon:",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = T5_WHITE,
            modifier = Modifier.padding(top = 32.dp, bottom = 4.dp)
        )
        // Improve day text fields
        uiState.thingsToImprove.forEachIndexed { index, text ->
            OutlinedTextField(
                value = text,
                minLines = 2,
                onValueChange = { newText ->
                    onThingToImproveTextChange(index, newText)
                },
                readOnly = uiState.completed,
//                    label = if (uiState.thingsToImprove.size > 1) {
//                        { Text("${index + 1}") }
//                    } else null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = if (index == uiState.thingsToImprove.lastIndex) ImeAction.Done else ImeAction.Next
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = T5_WHITE, // Sets the background color
                    focusedContainerColor = T5_WHITE // Sets the background color
                ),
                shape = RoundedCornerShape(8.dp)
            )
        }

        // Save button
        if (uiState.isToday && !uiState.completed) {
            Button(
                onClick = {
                    onSubmitClick(
                        uiState.date,
                        uiState.amazingThings,
                        uiState.thingsToImprove
                    )
                },
                modifier = Modifier
                    .padding(top = 64.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = T5_DARK_BLUE,
                )
            ) {
                Text(
                    text = "Submit",
                    style = MaterialTheme.typography.headlineSmall,
                    color = T5_WHITE,
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_7A
)
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
            onAmazingThingTextChange = { _, _ -> },
            onThingToImproveTextChange = { _, _ -> },
            onSubmitClick = { _, _, _ -> },
            modifier = Modifier
        )
    }
}
