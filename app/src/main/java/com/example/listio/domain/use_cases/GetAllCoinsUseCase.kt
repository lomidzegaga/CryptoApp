package com.example.listio.domain.use_cases

import com.example.listio.data.model.GetAllCoinsModelDto
import com.example.listio.domain.repositories.CoinRepositories
import retrofit2.Response
import javax.inject.Inject

class GetAllCoinsUseCase @Inject constructor(
    private val coinRepositories: CoinRepositories
) {
    suspend fun execute(): Response<List<GetAllCoinsModelDto>> = coinRepositories.getAllCoins()
}