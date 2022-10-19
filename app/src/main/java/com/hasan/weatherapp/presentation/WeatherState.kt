package com.hasan.weatherapp.presentation

import com.hasan.weatherapp.domain.weather.WeatherInfo

data class WeatherState(

    var weatherInfo: WeatherInfo? = null,
    val Loading: Boolean = true,
    val errorMassage: String? = null


)
