package com.luisfagundes.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.luisfagundes.designsystem.theme.spacing

@Composable
fun SortDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onSortOptionSelected: (String) -> Unit
) {
    var selectedOption by remember { mutableStateOf("Name") }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.background,
            contentColor = contentColorFor(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = modifier
            ) {
                Text(
                    text = "Sort by",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = MaterialTheme.spacing.default)
                )
                RadioButtonGroup(
                    options = listOf("Name", "Achievement Percentage", "Date"),
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it }
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.default))
                Button(
                    onClick = { onSortOptionSelected(selectedOption) },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("OK")
                }
            }
        }
    }
}

@Composable
fun RadioButtonGroup(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.verySmall)
            ) {
                RadioButton(
                    selected = option == selectedOption,
                    onClick = { onOptionSelected(option) }
                )
                Text(
                    text = option,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = MaterialTheme.spacing.small)
                )
            }
        }
    }
}
