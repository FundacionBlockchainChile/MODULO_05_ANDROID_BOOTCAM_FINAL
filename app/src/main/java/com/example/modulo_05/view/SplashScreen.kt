package com.example.modulo_05.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.modulo_05.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, storeValue: Boolean) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation))
    LottieAnimation(composition, iterations = LottieConstants.IterateForever, modifier = Modifier.fillMaxSize())

    LaunchedEffect(Unit) {
        delay(3000) // 3 seconds delay
        navController.navigate(if (storeValue) "Home" else "OnBoarding")
    }
}