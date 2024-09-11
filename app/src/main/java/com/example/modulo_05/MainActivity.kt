package com.example.modulo_05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modulo_05.view.HomeView
import com.example.modulo_05.view.PatientListView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "patientList") {
                composable("patientList") { PatientListView(navController) }
                composable("home") { HomeView(navController) }
            }
        }
    }
}