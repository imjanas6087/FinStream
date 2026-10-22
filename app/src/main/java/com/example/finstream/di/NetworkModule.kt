package com.example.finstream.di

import com.example.finstream.retrofit.CoinsListCall
import com.example.finstream.retrofit.RetrofitClientCoin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(RetrofitClientCoin.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCoinsListCall(retrofit: Retrofit): CoinsListCall =
        retrofit.create(CoinsListCall::class.java)
}
