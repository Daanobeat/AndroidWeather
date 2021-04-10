package com.example.androidweather.ui.overview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.androidweather.data.model.Item
import com.example.androidweather.databinding.ItemWeatherBinding
import com.example.androidweather.util.Extensions.parseSecondsToDate
import io.reactivex.subjects.PublishSubject

private const val BASE_URL = "http://openweathermap.org/img/wn/"

class OverviewAdapter : ListAdapter<Item, ViewHolder>(object :
    DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem.dt == newItem.dt

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = newItem == oldItem
}) {
    val clickSubject = PublishSubject.create<Item>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ItemViewHolder.createViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(getItem(position)) {
                clickSubject.onNext(it)
            }
        }
    }

}

class ItemViewHolder(private val binding: ItemWeatherBinding) : ViewHolder(binding.root) {
    fun bind(item: Item, onItemClick: (Item) -> Unit) {
        binding.root.setOnClickListener { onItemClick.invoke(item) }
        binding.tvDate.text = item.dt.parseSecondsToDate()
        binding.tvTemperature.text = item.main.temp.toString() + "°C"
        println(BASE_URL+item.weather[0].icon+"@2x.png")
        binding.ivIcon.load(BASE_URL+item.weather[0].icon+"@2x.png")
    }


    companion object {
        fun createViewHolder(viewGroup: ViewGroup): ItemViewHolder = ItemViewHolder(
            ItemWeatherBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }
}