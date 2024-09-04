package com.example.modulo_05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.modulo_05.ui.theme.Modulo_05Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modulo_05Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UsuarioApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UsuarioApp(modifier: Modifier = Modifier) {
    val usuarioManager = UsuarioManager()
    usuarioManager.agregarUsuario(Usuario("Rick Grimes", 45, "Sheriff", null))
    usuarioManager.agregarUsuario(Usuario("Walter White", 60, null, null))
    usuarioManager.agregarUsuario(Usuario("Jesse Pinkman", 25, "Cocinero", null))
    usuarioManager.agregarUsuario(Usuario("Saul Goodman", 50, "Abogado", null))
    usuarioManager.agregarUsuario(Usuario("Gus Fring", 55, "Empresario", null))

    println("Lista de usuarios inicial:")
    usuarioManager.mostrarLista()

    usuarioManager.eliminarUsuario("Walter White")

    println("\nLista de usuarios después de eliminar a Walter White:")
    usuarioManager.mostrarLista()

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Usuarios:")
        usuarioManager.mostrarLista()
    }
}

@Composable
fun UsuarioView(usuario: Usuario) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = "Nombre: ${usuario.nombre}")
        Text(text = "Edad: ${usuario.edad} años")
        Text(text = "Trabajo: ${usuario.trabajo ?: "No especificado"}")
        Text(text = "Referencia: ${usuario.referencia?.nombre ?: "No hay referencia citada"}")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Modulo_05Theme {
        UsuarioApp()
    }
}