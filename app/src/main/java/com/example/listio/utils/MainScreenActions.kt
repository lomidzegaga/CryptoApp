package com.example.listio.utils

sealed class MainScreenActions {
    data object OnBuyClick : MainScreenActions()
    data object OnNavigationClick : MainScreenActions()
    data class OnCoinDetailsClick(val coinId: String) : MainScreenActions()
}