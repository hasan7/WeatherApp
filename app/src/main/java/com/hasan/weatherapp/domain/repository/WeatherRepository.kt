package com.hasan.weatherapp.domain.repository

import com.hasan.weatherapp.domain.util.Resource
import com.hasan.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherInfo(lat: Double, long: Double): Resource<WeatherInfo>

    suspend fun isLocationRequested(): Boolean?

    suspend fun insertIsLocationRequested(value:Boolean)

    suspend fun purgeAllData()

    suspend fun isOutdated(): Boolean

}