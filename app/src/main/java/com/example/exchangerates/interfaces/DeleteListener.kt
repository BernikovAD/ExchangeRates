package com.example.exchangerates.interfaces

import com.example.exchangerates.model.CurrencyDB

interface DeleteListener {
    fun delete(currencyDB: CurrencyDB)
}