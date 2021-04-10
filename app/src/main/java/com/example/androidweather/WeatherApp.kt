package com.example.androidweather

import android.app.Application
import android.content.Context

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    companion object {

        lateinit var instance: WeatherApp
            private set

    }
}