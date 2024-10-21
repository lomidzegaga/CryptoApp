package com.example.listio.utils

sealed class MainScreenActions {
    data object OnBuyClick : MainScreenActions()
    data class OnCoinDetailsClick(val coinId: String) : MainScreenActions()
    data class EnteredSearchText(val text: String) : MainScreenActions()
    data class ShowBottomSheet(val show: Boolean) : MainScreenActions()
}