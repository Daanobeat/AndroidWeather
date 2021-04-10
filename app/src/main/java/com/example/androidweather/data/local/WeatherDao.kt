package com.example.androidweather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather")
    suspend fun getAllWeather(): List<Weather>

    @Query("DELETE FROM weather")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(item: List<Weather>)

}