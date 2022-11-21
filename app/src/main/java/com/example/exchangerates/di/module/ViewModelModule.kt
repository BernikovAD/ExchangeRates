package com.example.dollarexchangerate.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.exchangerates.domain.repository.RepositoryDomain
import com.example.exchangerates.domain.repository.RepositoryLocal
import com.example.exchangerates.presenter.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun viewModelFactory(
        repositoryDomain: RepositoryDomain,
        repositoryLocal: RepositoryLocal
    ): ViewModelProvider.Factory {
        return ViewModelFactory(repositoryDomain, repositoryLocal)
    }
}