package com.example.listio.domain.repositories

import com.example.listio.data.model.GetAllCoinsModelDto
import com.example.listio.data.model.GetAllTickersDto
import com.example.listio.data.model.GetCoinByIdDto
import com.example.listio.data.model.SearchCoinDto
import retrofit2.Response

interface CoinRepositories {

    suspend fun getAllCoins(): Response<List<GetAllCoinsModelDto>>

    suspend fun getAllTickers(): Response<List<GetAllTickersDto>>

    suspend fun getCoinById(coinId: String): Response<GetCoinByIdDto>

    suspend fun searchCoin(searchCoin: String?): Response<SearchCoinDto>
}