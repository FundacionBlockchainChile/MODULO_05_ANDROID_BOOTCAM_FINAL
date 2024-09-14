package com.example.modulo_05.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class Patient(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    var age: String = "",
    var gender: String = "",
    var imc: String = "",
    var healthStatus: String = ""
)

class AppViewModel : ViewModel() {
    private val _patients = MutableStateFlow<List<Patient>>(emptyList())
    val patients: StateFlow<List<Patient>> get() = _patients

    private val _imcResult = MutableStateFlow("")
    val imcResult: StateFlow<String> get() = _imcResult

    private val _healthStatus = MutableStateFlow("")
    val healthStatus: StateFlow<String> get() = _healthStatus

    private val _gender = MutableStateFlow("Hombre")
    val gender: StateFlow<String> get() = _gender

    private val _age = MutableStateFlow("")
    val age: StateFlow<String> get() = _age

    private val _height = MutableStateFlow("")
    val height: StateFlow<String> get() = _height

    private val _weight = MutableStateFlow("")
    val weight: StateFlow<String> get() = _weight

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun clearForm() {
        _age.value = ""
        _height.value = ""
        _weight.value = ""
        _imcResult.value = ""
        _healthStatus.value = ""
        _errorMessage.value = null
    }

    fun addOrUpdatePatient(name: String, age: String, gender: String, imc: String, healthStatus: String) {
        val existingPatient = _patients.value.find { it.name == name }
        if (existingPatient != null) {
            Log.d("AppViewModel", "Updating patient: $name")
            updatePatientData(name, age, gender, imc, healthStatus)
        } else {
            Log.d("AppViewModel", "Adding new patient: $name")
            addPatient(name)
            updatePatientData(name, age, gender, imc, healthStatus)
        }
        Log.d("AppViewModel", "Patients list: ${_patients.value}")
    }

    fun addPatient(name: String) {
        _patients.value = _patients.value + Patient(name = name)
        Log.d("AppViewModel", "Patient added: $name")
    }

    fun updatePatientData(name: String, age: String, gender: String, imc: String, healthStatus: String) {
        _patients.value = _patients.value.map { patient ->
            if (patient.name == name) {
                Log.d("AppViewModel", "Patient data updated for: $name")
                patient.copy(age = age, gender = gender, imc = imc, healthStatus = healthStatus)
            } else {
                patient
            }
        }
    }

    fun setGender(value: String) {
        _gender.value = value
    }

    fun setAge(value: String) {
        _age.value = value
    }

    fun setHeight(value: String) {
        _height.value = value
    }

    fun setWeight(value: String) {
        _weight.value = value
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    fun calcularIMC() {
        viewModelScope.launch {
            val heightValue = _height.value.toDoubleOrNull()
            val weightValue = _weight.value.toDoubleOrNull()
            if (heightValue != null && weightValue != null && heightValue > 0) {
                val heightInMeters = heightValue / 100
                val imc = weightValue / (heightInMeters * heightInMeters)
                _imcResult.value = String.format("%.2f", imc)
                _healthStatus.value = calculateHealthStatus(imc)
            } else {
                _errorMessage.value = "Datos inválidos"
            }
        }
    }

    private fun calculateHealthStatus(imc: Double): String {
        return when {
            imc < 18.5 -> "Bajo peso"
            imc < 24.9 -> "Peso Normal"
            imc < 29.9 -> "Sobrepeso"
            imc < 34.9 -> "Obesidad I"
            imc < 39.9 -> "Obesidad II"
            else -> "Obesidad III"
        }
    }
}