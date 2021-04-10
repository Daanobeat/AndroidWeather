package com.example.androidweather.data.model

import java.io.Serializable

data class WeatherResponse(
    val list: List<Item>
)

data class Item(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>
) : Serializable

data class Main(
    val temp: Double,
    val pressure: Double,
    val humidity: Double
) : Serializable

data class Weather(
    val main: String,
    val description: String,
    val icon: String
) : Serializable