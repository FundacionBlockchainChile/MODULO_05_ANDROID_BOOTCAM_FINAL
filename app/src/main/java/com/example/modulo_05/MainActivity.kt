package com.example.modulo_05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var isImageVisible by remember { mutableStateOf(false) }
    var buttonText by remember { mutableStateOf("Mostrar Imagen") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeText()
        NameText()
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            isImageVisible = !isImageVisible
            buttonText = if (isImageVisible) "Ocultar Imagen" else "Mostrar Imagen"
        }) {
            Text(buttonText)
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (isImageVisible) {
            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = "Imagen de ejemplo"
            )
        }
    }
}

@Composable
fun WelcomeText() {
    Text(
        text = "¡Bienvenido a Jetpack Compose!",
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun NameText() {
    Text(
        text = "Tu Nombre",
        style = MaterialTheme.typography.titleMedium
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Modulo_05Theme {
        MainScreen()
    }
}