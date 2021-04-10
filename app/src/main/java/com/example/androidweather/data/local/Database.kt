package com.example.androidweather.data.local

import androidx.room.Room
import com.example.androidweather.WeatherApp
import com.example.androidweather.data.model.Item
import com.example.androidweather.data.model.Main

interface Database {

    suspend fun getAllWeather(): List<Item>

    suspend fun deleteAll()

    suspend fun insertWeather(item: List<Weather>)

}

class DatabaseImpl : Database {

    private val database =
        Room.databaseBuilder(WeatherApp.instance, AppDatabase::class.java, "database").build()

    override suspend fun getAllWeather(): List<Item> {
        return database.appDatabaseDao().getAllWeather().map {
            Item(
                dt = it.dt,
                main = Main(it.temp,it.pressure,it.humidity),
                weather = listOf(com.example.androidweather.data.model.Weather(it.main,it.description,it.icon))
            )
        }
    }

    override suspend fun deleteAll() {
        database.appDatabaseDao().deleteAll()
    }

    override suspend fun insertWeather(item: List<Weather>) {
        database.appDatabaseDao().insertWeather(item)
    }
}


object WeatherDatabase {
    val database: Database = DatabaseImpl()
}