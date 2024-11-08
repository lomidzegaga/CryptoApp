package com.example.listio.domain.repositories

import com.example.listio.data.model.CoinDto
import retrofit2.Response

fun interface CoinRepositories {

    suspend fun loadCoins(): Response<List<CoinDto>>

}