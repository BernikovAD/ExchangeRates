package com.example.exchangerates.presenter.popular

import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dollarexchangerate.utils.BaseViewBindingFragment
import com.example.exchangerates.MyApplication
import com.example.exchangerates.databinding.FragmentPopularCurrencyBinding
import com.example.exchangerates.interfaces.FavoriteListener
import com.example.exchangerates.model.CurrencyDB
import com.example.exchangerates.presenter.ViewModelFactory
import com.example.picturesoftheday.settings.PrefConfing
import javax.inject.Inject

class PopularCurrencyFragment :
    BaseViewBindingFragment<FragmentPopularCurrencyBinding>(FragmentPopularCurrencyBinding::inflate) {

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var viewModel: ExchangeRateViewModel
    private val adapter = PopularCurrencyAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireContext().applicationContext as MyApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[ExchangeRateViewModel::class.java]
        binding.recyclerViewExchangeRate.adapter = adapter

        binding.imageFilter.setOnClickListener {
            findNavController().navigate(directions = PopularCurrencyFragmentDirections.fragmentFilter())
        }

        adapter.setListener(object : FavoriteListener {
            override fun onClickFavoriteAdd(currency: Pair<String, Double>) {
                Toast.makeText(
                    requireContext(),
                    "Currency ${currency.first} add to favorite",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.addLocalCurrency(
                    CurrencyDB(
                        name = currency.first,
                        value = currency.second
                    )
                )
            }


        })

        viewModel.observeState(viewLifecycleOwner) { state ->
            when (state) {
                ExchangeReteState.Loading -> {
                    binding.progressBarCurrency.visibility = View.VISIBLE
                    binding.recyclerViewExchangeRate.visibility = View.GONE
                }
                is ExchangeReteState.Success -> {
                    binding.progressBarCurrency.visibility = View.GONE
                    binding.recyclerViewExchangeRate.visibility = View.VISIBLE
                    if (!state.map.isNullOrEmpty()) {
                        setSort(state.map)
                    }
                }
                is ExchangeReteState.SuccessLoadedSymbol -> {
                    if (!state.symbol.isNullOrEmpty()) {
                        val list = mutableListOf<Pair<String, String>>()
                        state.symbol.forEach {
                            list.add(Pair(it.key, it.value))
                        }
                        val adapter =
                            ArrayAdapter(requireContext(), R.layout.simple_spinner_item, list)
                        binding.spinnerChoiceCurrency.adapter = adapter
                        binding.spinnerChoiceCurrency.onItemSelectedListener = object :
                            AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>,
                                view: View,
                                position: Int,
                                id: Long
                            ) {
                                val selectedItem =
                                    parent.getItemAtPosition(position) as Pair<String, String>
                                viewModel.getCurrency(selectedItem.first)
                            }

                            override fun onNothingSelected(parent: AdapterView<*>) {

                            }
                        }
                    }
                }
            }

        }
    }

    private fun setSort(list: Map<String, Double>) {
        val count = PrefConfing.load(requireContext())
        when (count) {
            1 -> sortAlphabeticallyAscending(list)
            2 -> sortAlphabeticallyDescending(list)
            3 -> sortValueAscending(list)
            4 -> sortValueDescending(list)
            5 -> {
                val list2 = mutableListOf<Pair<String, Double>>()
                list.toSortedMap().forEach {
                    list2.add(Pair(it.key, it.value))
                }
                adapter.submitList(list2)
            }
        }
    }

    private fun sortAlphabeticallyAscending(list: Map<String, Double>) {

        val list2 = mutableListOf<Pair<String, Double>>()
        list.toSortedMap().forEach {
            list2.add(Pair(it.key, it.value))
        }
        adapter.submitList(list2)
    }

    fun sortAlphabeticallyDescending(list: Map<String, Double>) {
        val list2 = mutableListOf<Pair<String, Double>>()
        list.toSortedMap().forEach {
            list2.add(Pair(it.key, it.value))
        }
        adapter.submitList(list2.reversed())
    }

    private fun sortValueAscending(list: Map<String, Double>) {
        list.toList().sortedBy { (_, value) -> value }.toMap()
    }

    private fun sortValueDescending(list: Map<String, Double>) {
        list.toList().sortedBy { (_, value) -> value }.toMap()
        Log.i("youTag", "${list.toList().sortedBy { (_, value) -> value }.toMap()}")
    }
}