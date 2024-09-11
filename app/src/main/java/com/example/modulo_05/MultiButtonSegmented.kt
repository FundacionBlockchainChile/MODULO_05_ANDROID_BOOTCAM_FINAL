// app/src/main/java/com/example/modulo_05/ui/components/MultiButtonSegmented.kt
package com.example.modulo_05.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MultiButtonSegmented(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        options.forEach { option ->
            if (selectedOption == option) {
                Button(
                    onClick = { onOptionSelected(option) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                ) {
                    Text(option)
                }
            } else {
                OutlinedButton(
                    onClick = { onOptionSelected(option) },
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                ) {
                    Text(option)
                }
            }
        }
    }
}