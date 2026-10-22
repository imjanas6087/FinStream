package com.example.finstream.retrofit

import com.example.finstream.data.DashBoardDataBO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinsListCall {

    @GET("coins/markets")
    suspend fun getCoinDetails(
        @Query("vs_currency") currency : String = "usd"
    ) : Response<DashBoardDataBO>
}