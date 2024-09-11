package com.example.modulo_05.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.modulo_05.ui.components.MultiButtonSegmented
import com.example.modulo_05.viewModel.IMCViewModel

@Composable
fun HomeView(navController: NavController, viewModel: IMCViewModel = viewModel()) {
    val imcResult by viewModel.imcResult.observeAsState("")
    val gender by viewModel.gender.observeAsState("Hombre")
    val age by viewModel.age.observeAsState("")
    val height by viewModel.height.observeAsState("")
    val weight by viewModel.weight.observeAsState("")
    val errorMessage by viewModel.errorMessage.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate("patientList") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a lista de pacientes")
        }
        Spacer(modifier = Modifier.height(32.dp))
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = height,
            onValueChange = { viewModel.setHeight(it) },
            label = { Text("Altura (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = weight,
            onValueChange = { viewModel.setWeight(it) },
            label = { Text("Peso (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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

        errorMessage?.let {
            Snackbar(
                action = {
                    Button(onClick = { /* Dismiss action */ }) {
                        Text("OK")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(it)
            }
        }
    }
}