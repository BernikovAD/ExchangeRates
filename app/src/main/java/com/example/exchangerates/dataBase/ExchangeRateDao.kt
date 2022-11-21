package com.example.exchangerates.dataBase

import androidx.room.*
import com.example.exchangerates.model.CurrencyDB

@Dao
interface ExchangeRateDao {
    @Query("SELECT * FROM currencyDB")
    suspend fun getRate(): List<CurrencyDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(currencyDB: CurrencyDB)

    @Delete
    fun deleteSaveCurrency(currencyDB: CurrencyDB)
}