package com.example.minmin_v1.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassDropdown() {
    val classOptions = listOf("Class 1", "Class 2", "Class 3", "Class 4", "Class 5")
    val (selectedClass, setSelectedClass) = remember { mutableStateOf(classOptions[0]) }
    val (expanded, setExpanded) = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { setExpanded(!expanded) }
        ) {
            TextField(
                value = selectedClass,
                onValueChange = {},
                label = { Text("Class") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth().clickable { setExpanded(true) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { setExpanded(false) }
            ) {
                classOptions.forEach { classOption ->
                    DropdownMenuItem(
                        text = { Text(text = classOption) },
                        onClick = {
                            setSelectedClass(classOption)
                            setExpanded(false)
                        }
                    )
                }
            }
        }
    }
}
