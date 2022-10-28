package com.hasan.weatherapp.data.database.entity
//
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import com.hasan.weatherapp.domain.weather.WeatherData
//import com.hasan.weatherapp.domain.weather.WeatherType
//import java.time.LocalDateTime
//
//@Entity
//data class WeatherEntity(
//    val time: LocalDateTime,
//    val temperatureCelsius: Double,
//    val pressure: Double,
//    val windSpeed: Double,
//    val humidity: Double,
//    val weatherType: WeatherType,
//    @PrimaryKey val id: Int? = null
//) {
//    fun toWordInfo(): WeatherData {
//        return WeatherData(
//           time = time,
//            temperatureCelsius = temperatureCelsius,
//            pressure = pressure,
//            windSpeed = windSpeed,
//            humidity = humidity,
//            weatherType = weatherType
//
//        )
//    }
//}
