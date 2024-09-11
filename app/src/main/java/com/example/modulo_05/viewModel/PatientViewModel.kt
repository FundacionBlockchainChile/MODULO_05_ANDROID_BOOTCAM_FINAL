package com.example.modulo_05.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Patient(val name: String)

class PatientViewModel : ViewModel() {
    private val _patients = MutableLiveData<List<Patient>>(emptyList())
    val patients: LiveData<List<Patient>> get() = _patients

    fun addPatient(name: String) {
        _patients.value = _patients.value?.plus(Patient(name))
    }
}