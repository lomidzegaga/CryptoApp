package com.example.listio.data.repositories

import com.example.listio.data.model.GetAllCoinsModelDto
import com.example.listio.data.model.GetAllTickersDto
import com.example.listio.data.model.GetCoinByIdDto
import com.example.listio.data.model.SearchCoinDto
import com.example.listio.data.provider.remote.RemoteDataProvider
import com.example.listio.domain.repositories.CoinRepositories
import retrofit2.Response
import javax.inject.Inject

class CoinRepositoriesImpl @Inject constructor(
    private val remoteDataProvider: RemoteDataProvider
) : CoinRepositories {
    override suspend fun getAllCoins(): Response<List<GetAllCoinsModelDto>> =
        remoteDataProvider.getAllCoins()

    override suspend fun getAllTickers(): Response<List<GetAllTickersDto>> =
        remoteDataProvider.getAllTickers()

    override suspend fun getCoinById(coinId: String): Response<GetCoinByIdDto> =
        remoteDataProvider.getCoinById(coinId = coinId)

    override suspend fun searchCoin(searchCoin: String?): Response<SearchCoinDto> =
        remoteDataProvider.searchCoin(searchCoin = searchCoin)
}