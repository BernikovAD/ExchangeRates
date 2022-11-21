package com.example.exchangerates.domain.repository

import com.example.exchangerates.dataBase.ExchangeRateDataBase
import com.example.exchangerates.model.CurrencyDB
import javax.inject.Inject

class RepositoryLocal @Inject constructor(val dataBase: ExchangeRateDataBase) :
    IRepositoryLocal(dataBase) {
    override suspend fun getCurrency(): List<CurrencyDB> {
        return dataBase.exchangeRateDao().getRate()
    }

    override suspend fun removeCurrency(currencyDB: CurrencyDB) {
        dataBase.exchangeRateDao().deleteSaveCurrency(currencyDB)
    }

    override suspend fun addCurrency(currencyDB: CurrencyDB) {
        dataBase.exchangeRateDao().save(currencyDB)
    }
}