package com.example.core.presentation.util

sealed class Screens {
    data object OnBoarding : Screens()
    data object MainScreen : Screens()
    data object CoinDetails : Screens()
    data object BuyCoins : Screens()
}