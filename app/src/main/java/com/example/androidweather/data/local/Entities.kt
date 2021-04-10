package com.example.androidweather.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey
    val dt: Long,
    //MAIN
    val temp: Double,
    val pressure: Double,
    val humidity: Double,
    //WEATHER
    val main: String,
    val description: String,
    val icon: String
)