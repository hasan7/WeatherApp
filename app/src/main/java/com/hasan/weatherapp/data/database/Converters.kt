package com.hasan.weatherapp.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.hasan.weatherapp.data.util.JsonParser
import com.hasan.weatherapp.domain.weather.WeatherData
import com.hasan.weatherapp.domain.weather.WeatherType

import com.squareup.moshi.Types
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
//    @TypeConverter
//    fun fromWeatherDataJson(json: String): Map<Int, List<WeatherEntity>>? {
//        return jsonParser.fromJson(
//            json,
//            Types.newParameterizedType(MutableMap::class.java,Int::class.java,
//                Types.newParameterizedType(ArrayList::class.java, WeatherEntity::class.javaObjectType)))?: emptyMap()
//    }
//
//    // TODO: maybe just fuckin remove the map since the list works so we loop and insert all of the map interies and each will be diffrant using time
//    @TypeConverter
//    fun fromWeatherDataJson(map: Map<Int, List<WeatherEntity>>): String {
//        return jsonParser.toJson(
//            map,
//            Types.newParameterizedType(MutableMap::class.java,Int::class.java,
//                Types.newParameterizedType(ArrayList::class.java, WeatherEntity::class.javaObjectType))) ?: "[]"
//    }


//
//    @TypeConverter
//    fun toMWeatherDataJson(weatherData: List<WeatherEntity>): String {
//        return jsonParser.toJson(
//            weatherData,
//            Types.newParameterizedType(List::class.java, WeatherEntity::class.javaObjectType)
//        ) ?: "[]"
//    }

    @TypeConverter
    fun fromWeatherTypeNumber(num: Int): WeatherType {
        return WeatherType.fromWMO(num)
    }

    @TypeConverter
    fun toMWeatherDataNumber(weathertype: WeatherType): Int {
        return weathertype.code
    }

    @TypeConverter
    fun fromLocalDateTimeString(str: String): LocalDateTime {
        return LocalDateTime.parse(str, DateTimeFormatter.ISO_DATE_TIME)
    }

    @TypeConverter
    fun toLocalDateTimeString(localDateTime: LocalDateTime): String {
        return localDateTime.toString()
    }


}