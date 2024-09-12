package com.example.listio.utils.params

import androidx.compose.runtime.Stable
import com.example.listio.presenter.model.CoinUIModel

data class ParamsMainScreen(
    val randomCoin: CoinUIModel,
    val totalAmountText: String,
    val isCoinDetailLoaded: Boolean = false,
    @Stable val list: List<CoinUIModel>
)
