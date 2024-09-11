package com.example.listio.data.model

import com.squareup.moshi.Json

data class SearchCoinDto(
    val currencies: List<Currencies>?,
    val exchanges: List<Exchanges>?,
    val icos: List<Icos>?,
    val people: List<People>?,
    val tags: List<Tags>?,
) {
    data class Currencies(
        val id: String,
        val name: String,
        val symbol: String,
        val rank: Int,
        @Json(name = "is_new") val isNew: Boolean,
        @Json(name = "is_active") val isActive: Boolean,
        val type: String,
        val rev: Long,
        @Json(name = "contract_address") val contractAddress: List<ContractAddress>? = null
    ) {
        data class ContractAddress(
            val type: String, val address: String
        )
    }

    data class Exchanges(
        val id: String, val name: String, val rank: Int, val rev: Long
    )

    data class Icos(
        val id: String,
        val name: String,
        val symbol: String,
        @Json(name = "is_new") val isNew: Boolean,
        val rev: Long
    )

    data class People(
        val id: String,
        val name: String,
        @Json(name = "teams_count") val teamsCount: Int,
    )

    data class Tags(
        val id: String,
        val name: String,
        @Json(name = "coin_counter") val coinCounter: Int,
        @Json(name = "ico_counter") val icoCounter: Int
    )
}