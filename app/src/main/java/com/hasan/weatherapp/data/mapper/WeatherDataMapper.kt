package com.hasan.weatherapp.data.mapper

import com.hasan.weatherapp.data.remote.WeatherDataDto
import com.hasan.weatherapp.data.remote.WeatherDto
import com.hasan.weatherapp.domain.weather.WeatherData
import com.hasan.weatherapp.domain.weather.WeatherInfo
import com.hasan.weatherapp.domain.weather.WeatherType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherDataDto.ToWeatherMap(): Map<Int, List<WeatherData>> {

    return time.mapIndexed { index, time ->
    val temperature = temperatures[index]
    val weatherCode = weatherCodes[index]
    val windSpeed = windSpeeds[index]
    val pressure = pressures[index]
    val humidity = humidities[index]
        WeatherData(
            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temperatureCelsius = temperature,
            pressure = pressure,
            windSpeed = windSpeed,
            humidity = humidity,
            weatherType = WeatherType.fromWMO(weatherCode)
        )

    }.groupBy {
    it.time.dayOfMonth
        }

    }

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.ToWeatherMap()
    val now = LocalDateTime.now()
    val dayOfMonth = LocalDate.now().dayOfMonth
    val currentWeatherData = weatherDataMap[dayOfMonth]?.find {
        val hour = if(now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData,
    )
}

//suspend fun WeatherDao.toWeatherInfo(): WeatherInfo {
//
//
////   return gettWeatherInfos().mapIndexed { index, weatherData ->
////       val temperature = gettWeatherInfos()[index].temperatureCelsius
////       val weatherCode = gettWeatherInfos()[index].weatherType
////       val windSpeed = gettWeatherInfos()[index].windSpeed
////       val pressure = gettWeatherInfos()[index].pressure
////       val humidity = gettWeatherInfos()[index].humidity
////       val time = gettWeatherInfos()[index].time
////       WeatherData(
////           time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
////           temperatureCelsius = temperature,
////           pressure = pressure,
////           windSpeed = windSpeed,
////           humidity = humidity,
////           weatherType = WeatherType.fromWMO(weatherCode)
////       )
////   }.groupBy {  }
////    return time.mapIndexed { index, time ->
////        val temperature = temperatures[index]
////        val weatherCode = weatherCodes[index]
////        val windSpeed = windSpeeds[index]
////        val pressure = pressures[index]
////        val humidity = humidities[index]
////        WeatherData(
////            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
////            temperatureCelsius = temperature,
////            pressure = pressure,
////            windSpeed = windSpeed,
////            humidity = humidity,
////            weatherType = WeatherType.fromWMO(weatherCode)
////        )
////
////    }.groupBy {
////        it.time.dayOfMonth
////    }
////
//
//    val data = mapOf(Pair(LocalDateTime.now().dayOfMonth, gettWeatherInfos()))
//
//    return WeatherInfo(
//        currentWeatherDataListDB = data,
//        currentWeatherData = null,
//        weatherDataPerDay = null
//    )
//}