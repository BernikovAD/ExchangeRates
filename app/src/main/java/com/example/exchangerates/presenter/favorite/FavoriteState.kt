package com.example.exchangerates.presenter.favorite

import com.example.dollarexchangerate.utils.IViewModelState
import com.example.exchangerates.model.CurrencyDB

sealed class FavoriteState : IViewModelState {
    object Loading : FavoriteState()
    data class Success(val list: List<CurrencyDB>) : FavoriteState()
    data class SuccessLoadedSymbol(val symbol: Map<String, String>) : FavoriteState()
}