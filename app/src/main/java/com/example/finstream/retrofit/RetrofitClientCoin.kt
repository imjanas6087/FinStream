package com.example.finstream.retrofit

import com.example.finstream.data.DashBoardDataBO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClientCoin {

    val BASE_URL : String = "https://api.coingecko.com/api/v3/"

    private fun getInstance() : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val coinListDetails : CoinsListCall = getInstance().create(CoinsListCall :: class.java)
}