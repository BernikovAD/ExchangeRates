package com.example.dollarexchangerate.di.module

import android.content.Context
import com.example.exchangerates.dataBase.ExchangeRateDao
import com.example.exchangerates.dataBase.ExchangeRateDataBase
import com.example.exchangerates.domain.repository.RepositoryLocal
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun provideRepositoryDataBase(dataBase: ExchangeRateDataBase): RepositoryLocal {
        return RepositoryLocal(dataBase = dataBase)
    }

    @Provides
    @Singleton
    fun provideCurrencyDao(dataBase: ExchangeRateDataBase): ExchangeRateDao {
        return dataBase.exchangeRateDao()
    }

    @Provides
    @Singleton
    fun provideDataBase(context: Context): ExchangeRateDataBase {
        return ExchangeRateDataBase.getInstance(context)
    }
}