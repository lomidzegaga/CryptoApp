package com.example.listio.presenter

import androidx.compose.runtime.Immutable
import com.example.listio.presenter.model.CoinUI

@Immutable
data class CoinListState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val isSearchLoading: Boolean = false,
    val coins: List<CoinUI> = emptyList(),
    val searchText: String = "",
    val suggestCoin: CoinUI? = null,
    val selectedCoin: CoinUI? = null,
)
