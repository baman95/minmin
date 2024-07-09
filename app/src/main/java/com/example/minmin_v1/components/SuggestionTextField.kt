package com.example.minmin_v1.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SuggestionTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    suggestions: List<String>
) {
    val coroutineScope = rememberCoroutineScope()
    val textFieldValue = remember { mutableStateOf(value) }

    Column {
        TextField(
            value = textFieldValue.value,
            onValueChange = {
                textFieldValue.value = it
                onValueChange(it)
            },
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        // Display suggestions based on input (implement this part as needed)
    }
}
