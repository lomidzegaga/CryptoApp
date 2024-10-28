package com.example.listio.presenter.model

import com.example.core.presentation.util.DisplayableNumber
import com.example.listio.domain.model.Coin
import com.example.core.presentation.util.toDisplayableNumber

data class CoinUI(
    val rank: Int,
    val id: String,
    val name: String,
    val symbol: String,
    val percentChange24h: DisplayableNumber,
    val changeFromAth: DisplayableNumber,
    val price: DisplayableNumber
)


fun Coin.toCoinUi(): CoinUI = CoinUI(
    id = id,
    rank = rank,
    name = name,
    symbol = symbol,
    price = price.toDisplayableNumber(),
    percentChange24h = percentChange24h.toDisplayableNumber(),
    changeFromAth = changeFromAth.toDisplayableNumber()
)