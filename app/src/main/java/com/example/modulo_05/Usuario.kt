package com.example.modulo_05.model

class Usuario(
    val nombre: String,
    val edad: Int,
    val trabajo: String?,
    val referencia: Usuario?
) {
    fun mostrarDatos() {
        println("Nombre: $nombre.")
        println("Edad: $edad años.")
        if (trabajo != null) {
            println("Trabajo: $trabajo.")
        } else {
            println("Trabajo: No especificado.")
        }
        if (referencia != null) {
            println("Fue citado por: ${referencia.nombre} de ${referencia.edad} años.")
        } else {
            println("No hay referencia citada.")
        }
    }
}