// app/src/main/java/com/example/modulo_05/MainActivity.kt
package com.example.modulo_05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.modulo_05.ui.theme.Modulo_05Theme
import com.example.modulo_05.view.HomeView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Modulo_05Theme {
                HomeView()
            }
        }
    }
}