package com.example.listio.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.listio.presenter.screens.BottomSheet
import com.example.listio.presenter.screens.CoinsListScreen
import com.example.listio.presenter.screens.OnBoarding
import com.example.listio.presenter.view_models.MainScreenVM
import com.example.listio.utils.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainScreenVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val screenHeight = LocalConfiguration.current.screenHeightDp * 0.9
            val state = viewModel.state.collectAsStateWithLifecycle()

            if (viewModel.showSheet) {
                BottomSheet(
                    screenHeight = screenHeight.dp,
                )
            }

            when (viewModel.currentScreen) {
                Screens.OnBoarding -> {
                    OnBoarding(
                        state.value,
                        onNavigationClick = { viewModel.navigateTo(Screens.MainScreen) }
                    )
                }

                Screens.MainScreen -> {
                    CoinsListScreen(
                        state = state.value
                    )
                }

                Screens.CoinDetails -> {
                    // handle CoinDetails screen
                }

                Screens.BuyCoins -> {
                    // handle BuyCoins screen
                }
            }
        }
    }
}