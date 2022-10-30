package com.hasan.weatherapp.domain.weather

import androidx.room.Entity
import androidx.room.PrimaryKey

import java.time.LocalDateTime

@Entity
data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
) {
//    fun toWeatherEntity(): WeatherEntity {
//        return  WeatherEntity(
//            time = time,
//            temperatureCelsius = temperatureCelsius,
//            pressure = pressure,
//            windSpeed = windSpeed,
//            humidity = humidity,
//            weatherType = weatherType
//        )
//    }
}
