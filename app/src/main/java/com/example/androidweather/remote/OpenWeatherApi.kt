package com.example.androidweather.remote

import com.example.androidweather.data.model.Item
import com.example.androidweather.data.model.WeatherResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

//    @GET("data/2.5/forecast?q=Hollabrunn&appid=943324b67600e3a04cbc0376e6fabc20&units=metric")
    @GET("data/2.5/forecast?&appid=943324b67600e3a04cbc0376e6fabc20&units=metric")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): WeatherResponse
}