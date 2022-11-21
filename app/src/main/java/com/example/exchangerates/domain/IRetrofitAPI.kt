package com.example.exchangerates.domain

import com.example.exchangerates.model.CurrencyList
import com.example.exchangerates.model.SymbolsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofitAPI {
    @GET("exchangerates_data/symbols")
    suspend fun getSymbolsCurrency(): Response<SymbolsList>

    @GET("exchangerates_data/latest")
    suspend fun getCurrencyExchangeRate(
        @Query("base") base: String
    ): Response<CurrencyList>
}