package com.example.picturesoftheday.settings

import android.content.Context
import android.content.SharedPreferences


object PrefConfing {
    private const val FILE = "sort"
    private const val KEY = "key_sort"
    fun save(context: Context, total: Int) {
        val pref: SharedPreferences = context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt(KEY, total)
        editor.apply()
    }

    fun load(context: Context): Int {
        val pref: SharedPreferences = context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
        return pref.getInt(KEY, 0)
    }
}