package com.example.core.data.networking

import com.example.core.domain.util.NetworkError
import com.example.core.domain.util.Result
import retrofit2.Response

suspend inline fun <reified T> responseToResult(
    response: Response<T>
): Result<T, NetworkError> = when (response.code()) {
    in 200..299 -> {
        try {
            Result.Success(response.body() ?: throw IllegalArgumentException())
        } catch (e: IllegalArgumentException) {
            Result.Error(NetworkError.SERIALIZATION)
        }
    }

    408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
    429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
    in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
    else -> Result.Error(NetworkError.UNKNOWN_ERROR)
}