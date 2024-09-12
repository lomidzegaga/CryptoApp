package com.example.listio.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.listio.MainViewModel
import com.example.listio.presenter.screens.BottomSheet
import com.example.listio.presenter.screens.MainScreen
import com.example.listio.presenter.screens.OnBoarding
import com.example.listio.utils.MainScreenActions
import com.example.listio.utils.Screens
import com.example.listio.utils.params.ParamsCoinDetails
import com.example.listio.utils.params.ParamsMainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val screenHeight = LocalConfiguration.current.screenHeightDp * 0.9

            val coins = viewModel.coins.collectAsState()
            val selectedCoin = viewModel.selectedCoin.collectAsState()
            val randomId by remember { mutableIntStateOf((1..1999).random()) }

            if (viewModel.showSheet) {
                BottomSheet(
                    params = ParamsCoinDetails(
                        maxSheetHeight = screenHeight.dp,
                        coinDetails = selectedCoin.value
                    )
                ) {
                    viewModel.isCoinDetailsLoaded = false
                    viewModel.showSheet(false)
                }
            }

            if (viewModel.isCoinDetailsLoaded) {
                viewModel.showSheet(true)
            }

            when (viewModel.currentScreen) {
                Screens.OnBoarding -> {
                    OnBoarding(viewModel.isButtonVisible) {
                        viewModel.navigateTo(Screens.MainScreen)
                    }
                }

                Screens.MainScreen -> {
                    val randomCoin by remember { mutableStateOf(coins.value[randomId]) }
                    MainScreen(
                        params = ParamsMainScreen(
                            totalAmountText = "$4 872,83",
                            randomCoin = randomCoin,
                            isCoinDetailLoaded = viewModel.showProgressIndicator,
                            list = coins.value
                        )
                    ) { action ->
                        when (action) {
                            MainScreenActions.OnBuyClick -> {
                                // Handle OnBuyClick action
                                viewModel.navigateTo(Screens.BuyCoins)
                            }

                            MainScreenActions.OnNavigationClick -> {
                                // Handle OnNavigationClick action
                            }

                            is MainScreenActions.OnCoinDetailsClick -> {
                                viewModel.getCoinById(coinId = action.coinId)
                            }
                        }
                    }
                }

                Screens.CoinDetails -> {
                    // BottomSheet(onDismiss = { viewModel.showSheet(false) })
                }

                Screens.BuyCoins -> {
                    // handle BuyCoins screen
                }
            }
        }
    }
}