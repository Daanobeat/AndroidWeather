package com.example.androidweather.manager

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.androidweather.WeatherApp

object PermissionManager {
    private lateinit var  requestPermissionLauncher: ActivityResultLauncher<String>


    fun requestPermission(fragment: Fragment, callback: (Boolean) -> Unit) {
        requestPermissionLauncher = fragment.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {isGranted: Boolean ->
            callback(isGranted)
        }
    }

    fun checkForPermission(permission: String, callback: () -> Unit){
        when{
            ContextCompat.checkSelfPermission(
                WeatherApp.instance,
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {
                callback()

            }else -> {
            //REQUEST
            requestPermissionLauncher.launch(permission)
            }
        }
    }
}