package com.example.finstream.repository

import com.example.finstream.data.DashBoardDataBO
import com.example.finstream.retrofit.CoinsListCall
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinRepo @Inject constructor(private val api: CoinsListCall) {

    suspend fun getCoinRepo(): Response<DashBoardDataBO> {
        return api.getCoinDetails()
    }
}
