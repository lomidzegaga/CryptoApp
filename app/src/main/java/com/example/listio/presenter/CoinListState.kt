package com.example.listio.presenter

import androidx.compose.runtime.Immutable
import com.example.listio.presenter.model.CoinUI

@Immutable
data class CoinListState(
    val isLoading: Boolean = true,
    val coins: List<CoinUI> = emptyList(),
    val suggestCoin: CoinUI? = null,
    val selectedCoin: CoinUI? = null,
)
