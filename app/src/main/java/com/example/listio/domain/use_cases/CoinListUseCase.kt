package com.example.listio.domain.use_cases

import com.example.core.data.networking.safeCall
import com.example.core.domain.util.NetworkError
import com.example.core.domain.util.Result
import com.example.listio.data.mapper.toCoin
import com.example.listio.domain.model.Coin
import com.example.listio.domain.repositories.CoinRepositories
import retrofit2.Response
import javax.inject.Inject

class CoinListUseCase @Inject constructor(
    private val coinRepositories: CoinRepositories
) {
    suspend fun execute(): Result<List<Coin>, NetworkError> =
        safeCall<List<Coin>> {
            Response.success(coinRepositories.loadCoins().body()?.map { it.toCoin() })
        }
}