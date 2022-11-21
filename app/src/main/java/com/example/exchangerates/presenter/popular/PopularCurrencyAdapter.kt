package com.example.exchangerates.presenter.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.databinding.ItemPopularCurrencyBinding
import com.example.exchangerates.interfaces.FavoriteListener

class PopularCurrencyAdapter : ListAdapter<Pair<String, Double>, ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pair<String, Double>>() {
            override fun areItemsTheSame(
                oldItem: Pair<String, Double>,
                newItem: Pair<String, Double>
            ) =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: Pair<String, Double>,
                newItem: Pair<String, Double>
            ) =
                oldItem.first == newItem.first
        }
    }

    private lateinit var listener: FavoriteListener

    fun setListener(listener: FavoriteListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return ViewHolder(
            binding = ItemPopularCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ViewHolder(val binding: ItemPopularCurrencyBinding, val listener: FavoriteListener) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(value: Pair<String, Double>) {
        binding.value.text = value.second.toString()
        binding.textViewNameCurrency.text = value.first
        binding.imageFavorite.setOnClickListener {
            listener.onClickFavoriteAdd(value)
        }
    }
}