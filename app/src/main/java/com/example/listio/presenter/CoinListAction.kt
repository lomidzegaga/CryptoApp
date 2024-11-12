package com.example.listio.presenter

import com.example.listio.presenter.model.CoinUI

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUI) : CoinListAction
    data class OnSearch(val searchText: String) : CoinListAction
    data object OnSwipeAction : CoinListAction
}