package com.example.exchangerates.presenter.favorite

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.dollarexchangerate.utils.BaseViewBindingFragment
import com.example.exchangerates.MyApplication
import com.example.exchangerates.databinding.FragmentFavoriteCurrencyBinding
import com.example.exchangerates.interfaces.DeleteListener
import com.example.exchangerates.model.CurrencyDB
import com.example.exchangerates.presenter.ViewModelFactory
import javax.inject.Inject

class FavoriteCurrencyFragment :
    BaseViewBindingFragment<FragmentFavoriteCurrencyBinding>(FragmentFavoriteCurrencyBinding::inflate) {


    private lateinit var viewModel: FavoriteViewModel
    private val adapter = FavoriteCurrencyAdapter()
    @Inject
    lateinit var factory: ViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireContext().applicationContext as MyApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        binding.recyclerViewExchangeRateFavorite.adapter = adapter
        adapter.setListener(object : DeleteListener {
            override fun delete(currencyDB: CurrencyDB) {
                viewModel.delete(currencyDB)
            }
        })
        viewModel.observeState(viewLifecycleOwner) { state ->
            when (state) {
                FavoriteState.Loading -> Unit
                is FavoriteState.Success -> {
                    if (!state.list.isNullOrEmpty()) {
                        adapter.submitList(state.list)
                    }

                }
                is FavoriteState.SuccessLoadedSymbol -> Unit
            }
        }
    }
}