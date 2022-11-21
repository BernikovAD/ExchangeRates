package com.example.dollarexchangerate.di.module

import android.content.Context
import com.example.exchangerates.presenter.MainActivity
import com.example.exchangerates.presenter.favorite.FavoriteCurrencyFragment
import com.example.exchangerates.presenter.popular.PopularCurrencyFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [NetworkModule::class, ViewModelModule::class, DataBaseModule::class]
)
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(fragment: PopularCurrencyFragment)
    fun inject(fragment: FavoriteCurrencyFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}

