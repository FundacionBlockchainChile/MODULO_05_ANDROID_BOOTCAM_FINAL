package com.example.modulo_05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.modulo_05.ui.theme.Modulo_05Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modulo_05Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PriceCalculatorApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun PriceCalculatorApp(modifier: Modifier = Modifier) {
    var age by remember { mutableStateOf("") }
    var dayOfWeek by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Ingresa tu edad:")
        TextField(
            value = age,
            onValueChange = { age = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(8.dp)
        )
        Text("Ingresa el día de la semana (Lun, Mar, etc.):")
        TextField(
            value = dayOfWeek,
            onValueChange = { dayOfWeek = it },
            modifier = Modifier.padding(8.dp)
        )
        Button(onClick = {
            result = calculatePrice(age, dayOfWeek)
        }, modifier = Modifier.padding(top = 8.dp)) {
            Text("Calcular Precio")
        }
        Text(result, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(top = 8.dp))
    }
}

fun calculatePrice(ageStr: String, dayOfWeek: String): String {
    return try {
        val age = ageStr.toInt()
        when {
            age < 0 || age > 100 -> "Error: Edad fuera de rango."
            age < 4 -> "Gratis"
            age <= 15 -> "Precio de la entrada: 15,000 CLP"
            age <= 60 -> {
                val price = if (dayOfWeek == "Mon" || dayOfWeek == "Tue") 25_000 else 30_000
                "Precio de la entrada: $price CLP"
            }
            else -> "Precio de la entrada: 20,000 CLP"
        }
    } catch (e: NumberFormatException) {
        "Error: Por favor ingresa una edad válida."
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Modulo_05Theme {
        PriceCalculatorApp()
    }
}