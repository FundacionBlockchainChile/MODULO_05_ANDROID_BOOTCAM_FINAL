package com.example.modulo_05.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.modulo_05.ui.components.MultiButtonSegmented
import com.example.modulo_05.viewModel.IMCViewModel

@Composable
fun HomeView(viewModel: IMCViewModel = viewModel()) {
    val imcResult by viewModel.imcResult.observeAsState("")
    val gender by viewModel.gender.observeAsState("Hombre")
    val age by viewModel.age.observeAsState("")
    val height by viewModel.height.observeAsState("")
    val weight by viewModel.weight.observeAsState("")

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
            onOptionSelected = { viewModel.setGender(it) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = age,
            onValueChange = { viewModel.setAge(it) },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = height,
            onValueChange = { viewModel.setHeight(it) },
            label = { Text("Altura (cm)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = weight,
            onValueChange = { viewModel.setWeight(it) },
            label = { Text("Peso (kg)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.calcularIMC() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Resultado IMC: $imcResult", style = MaterialTheme.typography.bodyLarge)
    }
}