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
import com.example.modulo_05.onBoardingViews.MainOnBoarding
import com.example.modulo_05.datastore.StoreBoarding
import com.example.modulo_05.viewModel.AppViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.modulo_05.views.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val context = LocalContext.current
    val dataStore = StoreBoarding(context)
    val store = dataStore.getBoarding.collectAsState(initial = false)
    
    val navController = rememberNavController()
    val appViewModel: AppViewModel = viewModel()

    NavHost(navController, startDestination = if (store.value) "patientList" else "splash") {
        composable("patientList") {
            PatientListView(navController, appViewModel)
        }
        composable("home/{patientName}") { backStackEntry ->
            val patientName = backStackEntry.arguments?.getString("patientName")
            HomeView(navController, appViewModel, patientName ?: "")
        }
        composable("onBoarding") {
            MainOnBoarding(navController, dataStore)
        }
        composable("splash") {
            SplashScreen(navController, store.value)
        }
    }
}
