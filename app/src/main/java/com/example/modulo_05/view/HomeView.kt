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
import com.example.modulo_05.viewModel.AppViewModel
import android.util.Log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, viewModel: AppViewModel = viewModel(), patientName: String) {
    // Reset the form when navigating
    LaunchedEffect(patientName) {
        viewModel.clearForm()
    }

    val imcResult by viewModel.imcResult.collectAsState()
    val healthStatus by viewModel.healthStatus.collectAsState()
    val age by viewModel.age.collectAsState()
    val height by viewModel.height.collectAsState()
    val weight by viewModel.weight.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // Track whether the save button should be visible
    var showSaveButton by remember { mutableStateOf(false) }

    // Validation logic for valid input
    val isInputValid = age.isNotEmpty() && height.isNotEmpty() && weight.isNotEmpty() &&
            age.toIntOrNull() != null && height.toDoubleOrNull() != null && weight.toDoubleOrNull() != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Registrando datos para: $patientName", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = age,
            onValueChange = {
                viewModel.setAge(it)
                showSaveButton = false // Hide the Save button when input changes
                viewModel.clearErrorMessage() // Clear error message
            },
            label = { Text("Edad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = height,
            onValueChange = {
                viewModel.setHeight(it)
                showSaveButton = false
                viewModel.clearErrorMessage()
            },
            label = { Text("Altura (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = weight,
            onValueChange = {
                viewModel.setWeight(it)
                showSaveButton = false
                viewModel.clearErrorMessage()
            },
            label = { Text("Peso (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.calcularIMC()
            showSaveButton = isInputValid // Only show Save button if input is valid
        }) {
            Text("Calcular IMC")
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (imcResult.isNotEmpty()) {
            Text("IMC: $imcResult")
            Text("Estado de salud: $healthStatus")
        }

        if (showSaveButton && isInputValid) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.addPatient(patientName)
                viewModel.updatePatientData(patientName, age, gender = "Hombre", imcResult, healthStatus)
                navController.navigate("patientList")
            }) {
                Text("Guardar")
            }
        }

        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}

