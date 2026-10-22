package com.example.finstream.data

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class DashBoardDataBOGsonTest {

    private val json = """
        [{
          "ath": 126080.0, "ath_change_percentage": -33.00412,
          "ath_date": "2025-10-06T10:57:42.000Z", "atl": 67.81,
          "atl_change_percentage": 124468.0129, "atl_date": "2013-07-05T16:00:00.000Z",
          "circulating_supply": 20098843.0, "current_price": 84468.0,
          "fully_diluted_valuation": 1697822263429, "high_24h": 84808.0,
          "id": "bitcoin",
          "image": "https://coin-images.coingecko.com/coins/images/1/large/bitcoin.png",
          "last_updated": "2026-10-21T19:32:53.000Z", "low_24h": 82458.0,
          "market_cap": 1697715579936, "market_cap_change_24h": 41396613533.0,
          "market_cap_change_percentage_24h": 2.49931, "market_cap_rank": 1,
          "max_supply": 21000000.0, "name": "Bitcoin",
          "price_change_24h": 2059.65, "price_change_percentage_24h": 2.46677,
          "roi": null, "symbol": "btc", "total_supply": 20100106.0,
          "total_volume": 31352244811
        }]
    """.trimIndent()

    @Test
    fun `gson deserializes the markets array into DashBoardDataBO`() {
        val result = Gson().fromJson(json, DashBoardDataBO::class.java)

        assertEquals(1, result.size)
        assertEquals("Bitcoin", result[0].name)
        assertEquals("btc", result[0].symbol)
        assertEquals(84468.0, result[0].current_price, 0.001)
        assertEquals(null, result[0].roi)
    }
}
