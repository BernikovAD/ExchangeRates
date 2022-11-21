package com.example.exchangerates.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exchangerates.model.CurrencyDB

@Database(entities = [CurrencyDB::class], version = 2)
abstract class ExchangeRateDataBase : RoomDatabase() {
    abstract fun exchangeRateDao(): ExchangeRateDao

    companion object {
        @Volatile
        private var database: ExchangeRateDataBase? = null

        @Synchronized
        fun getInstance(context: Context): ExchangeRateDataBase {
            return if (database == null) {
                database = Room.databaseBuilder(
                    context, ExchangeRateDataBase::class.java,
                    "database"
                ).build()
                database as ExchangeRateDataBase
            } else database as ExchangeRateDataBase
        }
    }
}