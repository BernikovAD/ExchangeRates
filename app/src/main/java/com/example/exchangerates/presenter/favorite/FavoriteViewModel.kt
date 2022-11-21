package com.example.exchangerates.presenter.favorite

import androidx.lifecycle.viewModelScope
import com.app.peshkariki.Fragment.base.BaseViewModel
import com.example.exchangerates.domain.repository.RepositoryDomain
import com.example.exchangerates.domain.repository.RepositoryLocal
import com.example.exchangerates.model.CurrencyDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val repositoryDomain: RepositoryDomain,
    private val repositoryLocal: RepositoryLocal
) :
    BaseViewModel<FavoriteState>(FavoriteState.Loading) {

    init {
        getCurrencyDB()
    }

    fun getCurrencyDB() {
        state.value = FavoriteState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repositoryLocal.getCurrency()
            state.postValue(FavoriteState.Success(responce))
        }
    }

    fun delete(currencyDB: CurrencyDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryLocal.removeCurrency(currencyDB)
            state.postValue(FavoriteState.Success(repositoryLocal.getCurrency()))
        }

    }
}