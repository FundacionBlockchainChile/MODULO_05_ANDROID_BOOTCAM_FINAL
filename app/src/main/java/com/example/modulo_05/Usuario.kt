package com.example.modulo_05

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

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val usuario1 = Usuario("Rick Grimes", 45, "Sheriff", null)
            val usuario2 = Usuario("Walter White", 60, null, usuario1)
            usuario1.mostrarDatos()
            println()
            usuario2.mostrarDatos()
        }
    }
}