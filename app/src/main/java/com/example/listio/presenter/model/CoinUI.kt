package com.example.listio.presenter.model

import com.example.listio.domain.model.Coin
import com.example.listio.utils.toDisplayableNumber

data class CoinUI(
    val rank: Int,
    val id: String,
    val name: String,
    val symbol: String,
    val percentChange24h: DisplayableNumber,
    val changeFromAth: DisplayableNumber,
    val price: DisplayableNumber
)


data class DisplayableNumber(
    val value: Double,
    val formatted: String
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