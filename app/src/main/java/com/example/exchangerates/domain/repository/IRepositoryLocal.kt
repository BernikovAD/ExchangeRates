package com.example.exchangerates.domain.repository

import com.example.exchangerates.dataBase.ExchangeRateDataBase
import com.example.exchangerates.model.CurrencyDB

abstract class IRepositoryLocal(dataBase: ExchangeRateDataBase) {
    abstract suspend fun getCurrency(): List<CurrencyDB>
    abstract suspend fun removeCurrency(currencyDB: CurrencyDB)
    abstract suspend fun addCurrency(currencyDB: CurrencyDB)
}