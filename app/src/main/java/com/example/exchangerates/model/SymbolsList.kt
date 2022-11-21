package com.example.exchangerates.model

import com.google.gson.annotations.SerializedName

data class SymbolsList(
    @SerializedName("symbols") val symbols: HashMap<String, String>
)
