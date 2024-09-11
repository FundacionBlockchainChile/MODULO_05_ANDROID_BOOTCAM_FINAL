// // app/src/main/java/com/example/modulo_05/ui/HomeView.kt
// package com.example.modulo_05.ui

// import androidx.compose.foundation.layout.*
// import androidx.compose.material3.*
// import androidx.compose.runtime.*
// import androidx.compose.ui.Alignment
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.unit.dp
// import androidx.compose.ui.tooling.preview.Preview
// import com.example.modulo_05.ui.components.*

// @Composable
// fun HomeView() {
//     var height by remember { mutableStateOf("") }
//     var weight by remember { mutableStateOf("") }
//     var age by remember { mutableStateOf("") }
//     var gender by remember { mutableStateOf("Hombre") }

//     Column(
//         modifier = Modifier
//             .fillMaxSize()
//             .padding(16.dp),
//         verticalArrangement = Arrangement.Center,
//         horizontalAlignment = Alignment.CenterHorizontally
//     ) {
//         Text("Calculadora de IMC", style = MaterialTheme.typography.titleMedium)
//         Spacer(modifier = Modifier.height(16.dp))
//         MultiButtonSegmented(
//             options = listOf("Hombre", "Mujer"),
//             selectedOption = gender,
//             onOptionSelected = { gender = it },
//             modifier = Modifier.fillMaxWidth()
//         )
//         Spacer(modifier = Modifier.height(16.dp))
//         OutlinedTextField(
//             value = height,
//             onValueChange = { height = it },
//             label = { Text("Altura (cm)") },
//             modifier = Modifier.fillMaxWidth()
//         )
//         Spacer(modifier = Modifier.height(8.dp))
//         OutlinedTextField(
//             value = weight,
//             onValueChange = { weight = it },
//             label = { Text("Peso (kg)") },
//             modifier = Modifier.fillMaxWidth()
//         )
//         Spacer(modifier = Modifier.height(8.dp))
//         OutlinedTextField(
//             value = age,
//             onValueChange = { age = it },
//             label = { Text("Edad") },
//             modifier = Modifier.fillMaxWidth()
//         )
//         Spacer(modifier = Modifier.height(16.dp))
//         Button(
//             onClick = {
//                 // Lógica para calcular el IMC
//             },
//             modifier = Modifier.fillMaxWidth()
//         ) {
//             Text("Calcular IMC")
//         }
//     }
// }

// @Preview(showBackground = true)
// @Composable
// fun HomeViewPreview() {
//     HomeView()
// }

// app/src/main/java/com/example/modulo_05/ui/HomeView.kt
package com.example.modulo_05.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.modulo_05.ui.components.*

@Composable
fun HomeView() {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Hombre") }
    var imcResult by remember { mutableStateOf("") }

    fun calcularIMC(peso: String, altura: String): String {
        val pesoKg = peso.toFloatOrNull() ?: return "Datos inválidos"
        val alturaM = (altura.toFloatOrNull() ?: return "Datos inválidos") / 100
        val imc = pesoKg / (alturaM * alturaM)
        return "%.1f".format(imc)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Calculadora de IMC", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))
        MultiButtonSegmented(
            options = listOf("Hombre", "Mujer"),
            selectedOption = gender,
            onOptionSelected = { gender = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Altura (cm)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Peso (kg)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { imcResult = calcularIMC(weight, height) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Resultado IMC: $imcResult", style = MaterialTheme.typography.bodyLarge)
    }
}