package com.example.androidweather.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidweather.WeatherApp

@Database(
    entities = [Weather::class],
    version = 1,
    exportSchema = false
)


abstract class AppDatabase: RoomDatabase(){
    abstract fun appDatabaseDao(): WeatherDao
}
