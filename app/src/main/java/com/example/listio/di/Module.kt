package com.example.listio.di

import com.example.core.data.util.BASE_URL
import com.example.listio.data.provider.remote.RemoteDataProvider
import com.example.listio.data.repositories.CoinRepositoriesImpl
import com.example.listio.data.util.DefaultDispatcher
import com.example.listio.data.util.IoDispatcher
import com.example.listio.data.util.MainDispatcher
import com.example.listio.domain.repositories.CoinRepositories
import com.example.listio.domain.use_cases.CoinListUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideRetrofitClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return client
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(provideRetrofitClient())
        .addConverterFactory(
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
    fun provideListUseCase(coinRepositories: CoinRepositories): CoinListUseCase =
        CoinListUseCase(coinRepositories)

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