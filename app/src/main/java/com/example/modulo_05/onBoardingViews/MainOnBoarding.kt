package com.example.modulo_05.onBoardingViews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import cl.bootcamp.apponboarding.onBoardingViews.OnBoardingPager
import com.example.modulo_05.R
import com.example.modulo_05.data.PageData
import com.example.modulo_05.datastore.StoreBoarding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainOnBoarding(navController: NavController, store: StoreBoarding) {
    val items = listOf(
        PageData(R.raw.hola_android, "Hola Mundo!", "Descripción uno"),
        PageData(R.raw.page2, "Page 2", "Descripción dos"),
        PageData(R.raw.finish, "Finish!!", "Descripción tres")
    )

    val pagerState = rememberPagerState(initialPage = 0)

    OnBoardingPager(
        item = items,
        pagerState = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White),
        navController,
        store
    )
}

