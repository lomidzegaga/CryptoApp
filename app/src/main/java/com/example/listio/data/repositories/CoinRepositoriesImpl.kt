package com.example.listio.data.repositories

import com.example.listio.data.model.CoinDto
import com.example.listio.data.provider.remote.RemoteDataProvider
import com.example.listio.domain.repositories.CoinRepositories
import retrofit2.Response
import javax.inject.Inject

class CoinRepositoriesImpl @Inject constructor(
    private val remoteDataProvider: RemoteDataProvider
) : CoinRepositories {

    override suspend fun getAllTickers(): Response<List<CoinDto>> =
        remoteDataProvider.loadCoins()
}