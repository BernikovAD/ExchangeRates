package com.example.exchangerates.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exchangerates.domain.repository.RepositoryDomain
import com.example.exchangerates.domain.repository.RepositoryLocal
import com.example.exchangerates.presenter.favorite.FavoriteViewModel
import com.example.exchangerates.presenter.popular.ExchangeRateViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repositoryDomain: RepositoryDomain,
    private val repositoryLocal: RepositoryLocal
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when {
            modelClass.isAssignableFrom(ExchangeRateViewModel::class.java) ->
                ExchangeRateViewModel(repositoryDomain, repositoryLocal) as T
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) ->
                FavoriteViewModel(repositoryDomain, repositoryLocal) as T
            else -> throw  IllegalArgumentException("Unknown ViewModel class")
        }
    }

}