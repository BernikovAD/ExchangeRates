package com.example.exchangerates.presenter.popular

import androidx.lifecycle.viewModelScope
import com.app.peshkariki.Fragment.base.BaseViewModel
import com.example.exchangerates.domain.repository.RepositoryDomain
import com.example.exchangerates.domain.repository.RepositoryLocal
import com.example.exchangerates.model.CurrencyDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExchangeRateViewModel @Inject constructor(
    private val repositoryDomain: RepositoryDomain,
    private val repositoryLocal: RepositoryLocal
) :
    BaseViewModel<ExchangeReteState>(ExchangeReteState.Loading) {

    init {
        getSymbol()
    }

    fun getCurrency(base: String) {
        state.value = ExchangeReteState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repositoryDomain.getCurrencyExchangeRate(base)
            if (responce.isSuccessful)
                state.postValue(responce.body()?.rates?.let { ExchangeReteState.Success(it) })
        }
    }

    private fun getSymbol() {
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repositoryDomain.getSymbols()
            if (responce.isSuccessful)
                state.postValue(responce.body()?.symbols?.let {
                    ExchangeReteState.SuccessLoadedSymbol(
                        it
                    )
                })
        }
    }

    fun addLocalCurrency(currencyDB: CurrencyDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryLocal.addCurrency(currencyDB)
        }
    }
}