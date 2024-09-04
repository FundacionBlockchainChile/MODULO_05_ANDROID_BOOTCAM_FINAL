package com.example.modulo_05

class UsuarioManager {
    private val usuarios = mutableListOf<Usuario>()

    fun agregarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
    }

    fun eliminarUsuario(nombre: String) {
        usuarios.removeAll { it.nombre == nombre }
    }

    fun mostrarLista() {
        usuarios.forEach { it.mostrarDatos() }
    }

    fun filtrarUsuariosPorEdad(edad: Int): List<Usuario> {
        return usuarios.filter { it.edad > edad }
    }
}