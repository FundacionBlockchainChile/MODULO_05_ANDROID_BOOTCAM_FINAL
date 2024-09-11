package com.example.modulo_05.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.modulo_05.viewModel.PatientViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientListView(navController: NavController, viewModel: PatientViewModel = viewModel()) {
    val patients by viewModel.patients.observeAsState(emptyList())
    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Pacientes") },
                actions = {
                    IconButton(onClick = { showDialog.value = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar Paciente")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog.value = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Paciente")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(patients) { patient ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(1.dp, Color(0xFF2D3748), RoundedCornerShape(10.dp)), // Borde redondeado
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Nombre: ${patient.name}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Button(
                            onClick = { navController.navigate("home") },
                            modifier = Modifier.wrapContentWidth(),
                        ) {
                            Text("Calcular IMC")
                        }
                    }
                }
            }
        }
    }

    if (showDialog.value) {
        AddPatientDialog(onDismiss = { showDialog.value = false }, onAdd = { name ->
            viewModel.addPatient(name)
            showDialog.value = false
        })
    }
}

@Composable
fun AddPatientDialog(onDismiss: () -> Unit, onAdd: (String) -> Unit) {
    var name by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Paciente") },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre del Paciente") }
            )
        },
        confirmButton = {
            Button(onClick = { onAdd(name) }) {
                Text("Agregar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
