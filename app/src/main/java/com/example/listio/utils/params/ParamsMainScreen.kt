package com.example.listio.utils.params

import androidx.compose.runtime.Stable
import com.example.listio.presenter.model.CoinUI

data class ParamsMainScreen(
    val searchText: String = "",
    val randomCoin: CoinUI,
    val totalAmountText: String,
    val isCoinDetailLoaded: Boolean = false,
    @Stable val list: List<CoinUI>
)
