package com.example.listio.di

import com.example.listio.data.provider.remote.RemoteDataProvider
import com.example.listio.data.repositories.CoinRepositoriesImpl
import com.example.listio.domain.repositories.CoinRepositories
import com.example.listio.domain.use_cases.GetAllCoinsUseCase
import com.example.listio.domain.use_cases.GetAllTickersUseCases
import com.example.listio.domain.use_cases.GetCoinByIdUseCase
import com.example.listio.domain.use_cases.SearchCoinUseCase
import com.example.listio.domain.use_cases.UseCases
import com.example.listio.utils.BASE_URL
import com.example.listio.utils.DefaultDispatcher
import com.example.listio.utils.IoDispatcher
import com.example.listio.utils.MainDispatcher
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            )
        ).build()

    @Singleton
    @Provides
    fun provideRemoteDataProvider(retrofit: Retrofit): RemoteDataProvider =
        retrofit.create(RemoteDataProvider::class.java)

    @Singleton
    @Provides
    fun provideRepositories(
        remoteDataProvider: RemoteDataProvider
    ): CoinRepositories = CoinRepositoriesImpl(remoteDataProvider)

    @Singleton
    @Provides
    fun provideUseCases(coinRepositories: CoinRepositories): UseCases = UseCases(
        GetAllCoinsUseCase(coinRepositories),
        GetAllTickersUseCases(coinRepositories),
        GetCoinByIdUseCase(coinRepositories),
        SearchCoinUseCase(coinRepositories)
    )

    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @DefaultDispatcher
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @MainDispatcher
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}