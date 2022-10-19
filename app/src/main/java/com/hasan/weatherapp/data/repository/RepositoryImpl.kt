package com.hasan.weatherapp.data.repository

import com.hasan.weatherapp.data.mapper.toWeatherInfo
import com.hasan.weatherapp.data.remote.WeatherApi
import com.hasan.weatherapp.domain.repository.WeatherRepository
import com.hasan.weatherapp.domain.util.Resource
import com.hasan.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherInfo(lat: Double, long: Double): Resource<WeatherInfo> {

        return try {
            Resource.Success(
                data = weatherApi.getWeatherData(lat = lat, long = long).toWeatherInfo()
            )
        }catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "error occurred55555555555555555")

        }
    }
}