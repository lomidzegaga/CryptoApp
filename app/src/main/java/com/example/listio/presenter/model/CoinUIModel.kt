package com.example.listio.presenter.model

data class CoinUIModel(
    val id: String,
    val name: String,
    val price: String,
    val percentChange24h: Double,
    val changeFromAth: String,
    val symbol: String
)
