package com.example.thejournal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.thejournal.ui.theme.T5_DARK_BLUE
import com.example.thejournal.ui.theme.T5_MEDIUM_BLUE
import com.example.thejournal.ui.theme.T5_WHITE

/**
 * Settings screen
 */
@Composable
fun SettingsScreen(
    name: String,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.3f to T5_DARK_BLUE,
                        1f to T5_MEDIUM_BLUE,
                    )
                )
            )
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = T5_WHITE
        )

        OutlinedTextField(
            value = name,
            onValueChange = { onNameChange(it) },
            singleLine = true,
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = T5_WHITE,
                unfocusedTextColor = T5_WHITE,
                unfocusedBorderColor = T5_WHITE,
                focusedBorderColor = T5_WHITE,
                focusedLabelColor = T5_WHITE,
                unfocusedLabelColor = T5_WHITE,
            )
        )
    }
}
