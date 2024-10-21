package com.example.listio.data.model

import com.example.listio.domain.model.Coin
import com.squareup.moshi.Json

data class GetAllTickersDto(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val quotes: Quotes
) {
    data class Quotes(
        @Json(name = "USD") val usd: Usd
    ) {
        data class Usd(
            val price: Double,
            @Json(name = "percent_change_24h") val percentChange24h: Double,
            @Json(name = "percent_from_price_ath") val percentFromPriceAth: Double
        )
    }
}

fun GetAllTickersDto.toCoin(): Coin = Coin(
    id = id,
    name = name,
    symbol = symbol,
    rank = rank,
    price = quotes.usd.price,
    percentChange24h = quotes.usd.percentChange24h,
    changeFromAth = quotes.usd.percentFromPriceAth
)
