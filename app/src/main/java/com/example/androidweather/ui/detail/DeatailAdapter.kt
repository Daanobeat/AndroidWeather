package com.example.androidweather.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidweather.databinding.ItemDetailBinding

class DeatailAdapter : ListAdapter<Pair<String, String>, DetailViewHolder>(object :
    DiffUtil.ItemCallback<Pair<String, String>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String, String>,
        newItem: Pair<String, String>
    ): Boolean = oldItem.first == newItem.first

    override fun areContentsTheSame(
        oldItem: Pair<String, String>,
        newItem: Pair<String, String>
    ): Boolean = oldItem.first == newItem.first && oldItem.second == newItem.second
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


class DetailViewHolder(private val binding: ItemDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Pair<String, String>) {
        binding.tvKey.text = data.first
        binding.tvValue.text = data.second
    }
}