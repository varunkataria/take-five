package com.example.thejournal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thejournal.data.SubmissionItemType
import com.example.thejournal.ui.theme.T5_DARK_BLUE
import com.example.thejournal.ui.theme.T5_LIGHT_BLUE
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
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isMorningEntry = uiState.isMorningEntry
    val primaryColor = if (isMorningEntry) T5_LIGHT_BLUE else T5_DARK_BLUE
    val accentColor = if (isMorningEntry) T5_DARK_BLUE else T5_WHITE
    if (isBottomSheet) {
        JournalBottomSheet(
            uiState = uiState,
            isMorningEntry = isMorningEntry,
            primaryColor = primaryColor,
            accentColor = accentColor,
            onCloseClick = onCloseClick,
            onTextChange = viewModel::updateSubmissionItem,
            onSubmitClick = viewModel::submitJournalEntry,
            modifier = modifier
        )
    } else {
        JournalFullScreen(
            uiState = uiState,
            isMorningEntry = isMorningEntry,
            primaryColor = primaryColor,
            accentColor = accentColor,
            onCloseClick = onCloseClick,
            onTextChange = viewModel::updateSubmissionItem,
            onSubmitClick = viewModel::submitJournalEntry,
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun JournalBottomSheet(
    uiState: JournalUiState,
    isMorningEntry: Boolean,
    primaryColor: Color,
    accentColor: Color,
    onCloseClick: () -> Unit,
    onTextChange: (type: SubmissionItemType, index: Int, newText: String) -> Unit,
    onSubmitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = { onCloseClick() },
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = primaryColor,
        content = {
            Box(
                modifier = modifier
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.4f to primaryColor,
                                1f to T5_RED
                            )
                        )
                    )
                    .padding(bottom = 16.dp)
                    .wrapContentHeight() // Ensure the bottom sheet wraps the content height
                    .nestedScroll(rememberNestedScrollInteropConnection())
            ) {
                JournalScreen(
                    uiState = uiState,
                    isMorningEntry = isMorningEntry,
                    primaryColor = primaryColor,
                    accentColor = accentColor,
                    onTextChange = onTextChange,
                    onSubmitClick = onSubmitClick,
                    modifier = modifier
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun JournalFullScreen(
    uiState: JournalUiState,
    isMorningEntry: Boolean,
    primaryColor: Color,
    accentColor: Color,
    onCloseClick: () -> Unit,
    onTextChange: (type: SubmissionItemType, index: Int, newText: String) -> Unit,
    onSubmitClick: () -> Unit,
    modifier: Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = if (isMorningEntry) "Morning Prompt" else "Evening Prompt",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = accentColor
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onCloseClick) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = accentColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent // Ensure the top bar is also blue
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.4f to primaryColor,
                            1f to T5_RED
                        )
                    )
                )
                .padding(paddingValues)
        ) {
            JournalScreen(
                uiState = uiState,
                isMorningEntry = isMorningEntry,
                primaryColor = primaryColor,
                accentColor = accentColor,
                onTextChange = onTextChange,
                onSubmitClick = onSubmitClick
            )
        }
    }
}


@Composable
private fun JournalScreen(
    uiState: JournalUiState,
    isMorningEntry: Boolean,
    primaryColor: Color,
    accentColor: Color,
    onTextChange: (type: SubmissionItemType, index: Int, newText: String) -> Unit,
    onSubmitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState() // State for scrolling

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(horizontal = 32.dp)
    ) {
        if (isMorningEntry) {
            SubmissionItemTextField(
                title = "I am grateful for:",
                values = uiState.gratefulThings,
                completed = uiState.completed,
                primaryColor = primaryColor,
                accentColor = accentColor,
                onTextChange = { index, newText ->
                    onTextChange(SubmissionItemType.GRATEFUL_THING, index, newText)
                }
            )
            SubmissionItemTextField(
                title = "My intentions for today are:",
                values = uiState.intentions,
                completed = uiState.completed,
                primaryColor = primaryColor,
                accentColor = accentColor,
                onTextChange = { index, newText ->
                    onTextChange(SubmissionItemType.INTENTION, index, newText)
                }
            )
        } else {
            SubmissionItemTextField(
                title = "Good things that happened today:",
                values = uiState.amazingThings,
                completed = uiState.completed,
                primaryColor = primaryColor,
                accentColor = accentColor,
                onTextChange = { index, newText ->
                    onTextChange(SubmissionItemType.AMAZING_THING, index, newText)
                }
            )
            SubmissionItemTextField(
                title = "Things to improve upon:",
                values = uiState.thingsToImprove,
                completed = uiState.completed,
                primaryColor = primaryColor,
                accentColor = accentColor,
                onTextChange = { index, newText ->
                    onTextChange(SubmissionItemType.THING_TO_IMPROVE, index, newText)
                }
            )
        }

        // Save button
        if (uiState.isToday && !uiState.completed) {
            Button(
                onClick = {
                    onSubmitClick()
                },
                modifier = Modifier
                    .padding(top = 64.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor,
                )
            ) {
                Text(
                    text = "Submit",
                    style = MaterialTheme.typography.headlineSmall,
                    color = accentColor,
                )
            }
        }
    }
}

@Composable
fun SubmissionItemTextField(
    title: String,
    values: List<String>,
    completed: Boolean,
    primaryColor: Color,
    accentColor: Color,
    onTextChange: (Int, String) -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        color = accentColor,
        modifier = Modifier.padding(top = 32.dp, bottom = 4.dp)
    )

    values.forEachIndexed { index, text ->
        OutlinedTextField(
            value = text,
            minLines = 2,
            readOnly = completed,
            onValueChange = { newText ->
                onTextChange(index, newText)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = if (index == values.lastIndex) ImeAction.Done else ImeAction.Next
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = T5_WHITE,
                focusedContainerColor = T5_WHITE,
                unfocusedBorderColor = T5_WHITE,
            ),
            shape = RoundedCornerShape(8.dp)
        )
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
                isMorningEntry = false,
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
            isMorningEntry = false,
            primaryColor = T5_DARK_BLUE,
            accentColor = T5_WHITE,
            onSubmitClick = {},
            onTextChange = { _, _, _ -> },
            modifier = Modifier
        )
    }
}
