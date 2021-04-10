package com.example.androidweather.ui.overview.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.androidweather.R
import com.example.androidweather.data.local.Weather
import com.example.androidweather.data.local.WeatherDatabase
import com.example.androidweather.data.model.Item
import com.example.androidweather.databinding.FragmentOverviewBinding
import com.example.androidweather.remote.Remote
import com.example.androidweather.ui.detail.Detail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OverviewView : Fragment() {
    private val viewModel: OverviewViewModel by viewModels()
    private lateinit var binding: FragmentOverviewBinding
    private val adapter = OverviewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        binding.rvOverview.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listData.observe(viewLifecycleOwner, { list ->
            adapter.submitList(list)
        })
        adapter.clickSubject.subscribe { findNavController().navigate(OverviewViewDirections.actionOverviewToDetail(it)) }
    }
}

class OverviewViewModel() : ViewModel(){

    private var _isIndicatorVisible = MutableLiveData<Boolean>()
    val isIndicatorVisible: LiveData<Boolean> get() =  _isIndicatorVisible

    private var _listData = MutableLiveData<List<Item>>()
    val listData: LiveData<List<Item>> get() = _listData

    init {
        getWeatherData()
    }
    private fun getWeatherData(){
        viewModelScope.launch(Dispatchers.IO){
            WeatherDatabase.database.insertWeather(Remote.api.getWeather().list.map {
                Weather(
                    dt = it.dt,
                    temp = it.main.temp,
                    pressure = it.main.pressure,
                    humidity = it.main.humidity,
                    main = it.weather[0].main,
                    description = it.weather[0].description,
                    icon = it.weather[0].icon
                )
            })
            _listData.postValue(WeatherDatabase.database.getAllWeather())
//            _listData.postValue(Remote.api.getWeather().list)
        }
    }


}