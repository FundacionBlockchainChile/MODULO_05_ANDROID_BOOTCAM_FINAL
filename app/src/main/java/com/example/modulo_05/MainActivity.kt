// MainActivity.kt
package com.example.modulo_05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modulo_05.view.HomeView
import com.example.modulo_05.view.PatientListView
import com.example.modulo_05.viewModel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val appViewModel: AppViewModel = viewModel() // Singleton ViewModel across screens

            NavHost(navController, startDestination = "patientList") {
                composable("patientList") {
                    PatientListView(navController, appViewModel) // Pass the same ViewModel
                }
                composable("home/{patientName}") { backStackEntry ->
                    val patientName = backStackEntry.arguments?.getString("patientName")
                    HomeView(navController, appViewModel, patientName ?: "")
                }
            }
        }
    }
}
