package com.hasan.weatherapp.presentation

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasan.weatherapp.domain.location.LocationTracker
import com.hasan.weatherapp.domain.repository.WeatherRepository
import com.hasan.weatherapp.domain.util.Resource
import com.hasan.weatherapp.domain.weather.WeatherData
import com.hasan.weatherapp.domain.weather.WeatherInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import javax.inject.Inject


@HiltViewModel
class ViewModel @Inject constructor(
    val locationTracker: LocationTracker,
    val repository: WeatherRepository,

) : ViewModel() {



    private var _weatherState = MutableStateFlow(WeatherState())
    val weatherState = _weatherState.asStateFlow()



      fun getWeatherBylocation() {

         viewModelScope.launch {

             locationTracker.getCurrentLocation()?.let {
               when(val result= repository.getWeatherInfo(it.latitude, it.longitude)){

                   is Resource.Success-> {

                       _weatherState.update {
                           it.copy(
                               weatherInfo = result.data,
                               Loading = false,
                               errorMassage = null
                           )
                       }

                   }
                   is Resource.Error -> {
                       _weatherState.update {
                           it.copy(
                               weatherInfo = null,
                               Loading = false,
                               errorMassage =result.message
                                )
                       }
                   }
               }
             }
         }
     }
}