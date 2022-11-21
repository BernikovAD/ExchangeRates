package com.example.exchangerates.interfaces

interface FavoriteListener {
    fun onClickFavoriteAdd(currency: Pair<String, Double>)
}