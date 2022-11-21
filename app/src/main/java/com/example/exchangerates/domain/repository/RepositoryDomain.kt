package com.example.exchangerates.domain.repository

import com.example.exchangerates.domain.IRetrofitAPI
import com.example.exchangerates.model.SymbolsList
import retrofit2.Response
import javax.inject.Inject

class RepositoryDomain @Inject constructor(private val api: IRetrofitAPI) : IRepositoryDomain(api) {
    override suspend fun getSymbols(): Response<SymbolsList> {
        return api.getSymbolsCurrency()
    }

    override suspend fun getCurrencyExchangeRate(base: String) = api.getCurrencyExchangeRate(base)
}