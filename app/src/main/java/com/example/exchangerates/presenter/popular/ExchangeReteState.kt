package com.example.exchangerates.presenter.popular

import com.example.dollarexchangerate.utils.IViewModelState

sealed class ExchangeReteState : IViewModelState {
    object Loading : ExchangeReteState()
    data class Success(val map: Map<String, Double>) : ExchangeReteState()
    data class SuccessLoadedSymbol(val symbol: Map<String, String>) : ExchangeReteState()
}