package com.example.androidweather.ui.overview

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.androidweather.R
import com.example.androidweather.databinding.ActivityMainBinding
import com.example.androidweather.manager.PermissionManager


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navController by lazy { findNavController(R.id.navHost) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

    override fun onBackPressed() {
        if (!navController.popBackStack()) super.onBackPressed()
    }
}
