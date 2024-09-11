package com.example.listio.domain.use_cases

import com.example.listio.data.model.GetCoinByIdDto
import com.example.listio.domain.repositories.CoinRepositories
import retrofit2.Response
import javax.inject.Inject

class GetCoinByIdUseCase @Inject constructor(
    private val coinsRepositories: CoinRepositories
) {
    suspend fun execute(coinId: String): Response<GetCoinByIdDto> =
        coinsRepositories.getCoinById(coinId = coinId)
}