package com.example.listio.domain.model

data class Coin(
    val rank: Int,
    val id: String,
    val name: String,
    val price: Double,
    val percentChange24h: Double,
    val changeFromAth: Double,
    val symbol: String
)
