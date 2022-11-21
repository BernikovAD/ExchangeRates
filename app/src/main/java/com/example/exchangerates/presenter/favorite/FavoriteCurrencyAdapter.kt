package com.example.exchangerates.presenter.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.databinding.ItemFavoriteCurrencyBinding
import com.example.exchangerates.interfaces.DeleteListener
import com.example.exchangerates.model.CurrencyDB

class FavoriteCurrencyAdapter :
    androidx.recyclerview.widget.ListAdapter<CurrencyDB, ViewHolderDB>(
        DIFF_CALLBACK
    ) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CurrencyDB>() {
            override fun areItemsTheSame(oldItem: CurrencyDB, newItem: CurrencyDB) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: CurrencyDB, newItem: CurrencyDB) =
                oldItem.name == newItem.name
        }
    }

    private lateinit var listener: DeleteListener

    fun setListener(listener: DeleteListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDB {
        return ViewHolderDB(
            binding = ItemFavoriteCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )

    }

    override fun onBindViewHolder(holder: ViewHolderDB, position: Int) {
        holder.bind(getItem(position))
    }

}

class ViewHolderDB(val binding: ItemFavoriteCurrencyBinding, val listener: DeleteListener) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(value: CurrencyDB) {
        binding.value.text = value.value.toString()
        binding.textViewNameCurrency.text = value.name
        binding.imageRemove.setOnClickListener {
            listener.delete(value)
        }
    }
}