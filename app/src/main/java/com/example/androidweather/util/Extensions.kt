package com.example.androidweather.util

import java.text.SimpleDateFormat
import java.util.*

object Extensions {

    fun Long.parseSecondsToDate(): String{
        try {
            val sdf = SimpleDateFormat("dd.MM.yyyy H:00")
            val netDate = Date(this * 1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}