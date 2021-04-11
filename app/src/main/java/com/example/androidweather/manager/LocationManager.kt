package com.example.androidweather.manager

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import com.example.androidweather.WeatherApp

object LocationManager {
    lateinit var locationManager: LocationManager

    fun getLocation(callback: (Pair<Double,Double>) -> Unit){
        locationManager = WeatherApp.instance.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1f,object : LocationListener{
            override fun onLocationChanged(location: Location) {
                callback(Pair(location.latitude,location.longitude))
                locationManager.removeUpdates(this)
            }
        })
    }
}