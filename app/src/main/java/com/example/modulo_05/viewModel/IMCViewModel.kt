package com.example.modulo_05.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IMCViewModel : ViewModel() {
    private val _imcResult = MutableLiveData<String>()
    val imcResult: LiveData<String> get() = _imcResult

    private val _gender = MutableLiveData("Hombre")
    val gender: LiveData<String> get() = _gender

    private val _age = MutableLiveData("")
    val age: LiveData<String> get() = _age

    private val _height = MutableLiveData("")
    val height: LiveData<String> get() = _height

    private val _weight = MutableLiveData("")
    val weight: LiveData<String> get() = _weight

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

    fun calcularIMC() {
        val pesoKg = _weight.value?.toFloatOrNull() ?: 0f
        val alturaM = (_height.value?.toFloatOrNull() ?: 0f) / 100
        val imc = pesoKg / (alturaM * alturaM)
        _imcResult.value = "%.1f".format(imc)
    }
}