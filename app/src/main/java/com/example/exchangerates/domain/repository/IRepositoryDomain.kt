package com.example.exchangerates.domain.repository

import com.example.exchangerates.domain.IRetrofitAPI
import com.example.exchangerates.model.CurrencyList
import com.example.exchangerates.model.SymbolsList
import retrofit2.Response

abstract class IRepositoryDomain(api: IRetrofitAPI) {
    abstract suspend fun getSymbols(): Response<SymbolsList>
    abstract suspend fun getCurrencyExchangeRate(base: String): Response<CurrencyList>
}