package com.example.listio.data.mapper

import com.example.listio.data.model.CoinDto
import com.example.listio.domain.model.Coin

fun CoinDto.toCoin(): Coin = Coin(
    id = id,
    name = name,
    symbol = symbol,
    rank = rank,
    price = quotes.usd.price,
    percentChange24h = quotes.usd.percentChange24h,
    changeFromAth = quotes.usd.percentFromPriceAth
)