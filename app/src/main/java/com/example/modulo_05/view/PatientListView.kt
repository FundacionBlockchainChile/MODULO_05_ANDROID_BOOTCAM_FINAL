package com.example.modulo_05.view

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.modulo_05.viewModel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientListView(navController: NavController, viewModel: AppViewModel) {
    val patients by viewModel.patients.collectAsState()
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f) // Permite que la lista ocupe todo el espacio disponible
                    .padding(16.dp)
            ) {
                items(patients) { patient ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .border(1.dp, Color(0xFF2D3748), RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(text = "Nombre: ${patient.name}", style = MaterialTheme.typography.bodyLarge)
                            Text(text = "Edad: ${patient.age}", style = MaterialTheme.typography.bodyLarge)
                            Text(text = "Género: ${patient.gender}", style = MaterialTheme.typography.bodyLarge)
                            if (patient.imc.isEmpty()) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                    Button(
                                        onClick = { navController.navigate("home/${patient.name}") },
                                        modifier = Modifier.wrapContentWidth()
                                    ) {
                                        Text("Calcular IMC")
                                    }
                                }
                            } else {
                                Text(text = "IMC: ${patient.imc}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Estado de salud: ${patient.healthStatus}", style = MaterialTheme.typography.bodyLarge)
                            }
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
