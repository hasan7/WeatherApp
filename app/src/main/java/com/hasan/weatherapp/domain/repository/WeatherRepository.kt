package com.hasan.weatherapp.domain.repository

import androidx.room.RoomDatabase
import com.hasan.weatherapp.domain.util.Resource
import com.hasan.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherInfo(lat: Double, long: Double): Resource<WeatherInfo>

}