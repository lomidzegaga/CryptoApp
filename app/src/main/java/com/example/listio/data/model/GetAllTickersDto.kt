package com.example.listio.data.model

import com.example.listio.presenter.model.CoinUIModel
import com.example.listio.utils.formatPrice
import com.squareup.moshi.Json

data class GetAllTickersDto(
    val id: String, val name: String, val symbol: String, val rank: Int,
    val quotes: Quotes? = null
) {
    data class Quotes(
        @Json(name = "USD") val usd: Usd?
    ) {
        data class Usd(
            val price: Double,
            @Json(name = "percent_change_24h") val percentChange24h: Double,
            @Json(name = "percent_from_price_ath") val percentFromPriceAth: Double
        )
    }

    fun toCoinUIModel(): CoinUIModel = CoinUIModel(
        rank = rank,
        id = id,
        name = name.take(4),
        price = "$" + quotes?.usd?.price?.formatPrice(),
        percentChange24h = quotes?.usd?.percentChange24h ?: 0.0,
        changeFromAth = quotes?.usd?.percentFromPriceAth.toString() + " " + symbol.take(4),
        symbol = symbol
    )
}
