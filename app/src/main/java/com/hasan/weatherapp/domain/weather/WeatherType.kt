package com.hasan.weatherapp.domain.weather

import androidx.annotation.DrawableRes
import com.hasan.weatherapp.R

sealed class WeatherType(
    val code: Int,
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
) {
    object ClearSky : WeatherType(
        code = 0,
        weatherDesc = "Clear sky",
        iconRes = R.drawable.ic_sunny
    )
    object MainlyClear : WeatherType(
        code = 1,
        weatherDesc = "Mainly clear",
        iconRes = R.drawable.ic_cloudy
    )
    object PartlyCloudy : WeatherType(
        code = 2,
        weatherDesc = "Partly cloudy",
        iconRes = R.drawable.ic_cloudy
    )
    object Overcast : WeatherType(
        code = 3,
        weatherDesc = "Overcast",
        iconRes = R.drawable.ic_cloudy
    )
    object Foggy : WeatherType(
        code = 45,
        weatherDesc = "Foggy",
        iconRes = R.drawable.ic_very_cloudy
    )
    object DepositingRimeFog : WeatherType(
        code = 48,
        weatherDesc = "Depositing rime fog",
        iconRes = R.drawable.ic_very_cloudy
    )
    object LightDrizzle : WeatherType(
        code = 51,
        weatherDesc = "Light drizzle",
        iconRes = R.drawable.ic_rainshower
    )
    object ModerateDrizzle : WeatherType(
        code = 53,
        weatherDesc = "Moderate drizzle",
        iconRes = R.drawable.ic_rainshower
    )
    object DenseDrizzle : WeatherType(
        code = 55,
        weatherDesc = "Dense drizzle",
        iconRes = R.drawable.ic_rainshower
    )

    object DenseFreezingDrizzle : WeatherType(
        code = 57,
        weatherDesc = "Dense freezing drizzle",
        iconRes = R.drawable.ic_snowyrainy
    )
    object SlightRain : WeatherType(
        code = 61,
        weatherDesc = "Slight rain",
        iconRes = R.drawable.ic_rainy
    )
    object ModerateRain : WeatherType(
        code = 63,
        weatherDesc = "Rainy",
        iconRes = R.drawable.ic_rainy
    )
    object HeavyRain : WeatherType(
        code = 65,
        weatherDesc = "Heavy rain",
        iconRes = R.drawable.ic_rainy
    )
    object LightFreezingDrizzle : WeatherType(
        code = 66,
        weatherDesc = "Slight freezing drizzle",
        iconRes = R.drawable.ic_snowyrainy
    )
    object HeavyFreezingRain: WeatherType(
        code = 67,
        weatherDesc = "Heavy freezing rain",
        iconRes = R.drawable.ic_snowyrainy
    )
    object SlightSnowFall: WeatherType(
        code = 71,
        weatherDesc = "Slight snow fall",
        iconRes = R.drawable.ic_snowy
    )
    object ModerateSnowFall: WeatherType(
        code = 73,
        weatherDesc = "Moderate snow fall",
        iconRes = R.drawable.ic_heavysnow
    )
    object HeavySnowFall: WeatherType(
        code = 75,
        weatherDesc = "Heavy snow fall",
        iconRes = R.drawable.ic_heavysnow
    )
    object SnowGrains: WeatherType(
        code = 77,
        weatherDesc = "Snow grains",
        iconRes = R.drawable.ic_heavysnow
    )
    object SlightRainShowers: WeatherType(
        code = 80,
        weatherDesc = "Slight rain showers",
        iconRes = R.drawable.ic_rainshower
    )
    object ModerateRainShowers: WeatherType(
        code = 81,
        weatherDesc = "Moderate rain showers",
        iconRes = R.drawable.ic_rainshower
    )
    object ViolentRainShowers: WeatherType(
        code = 82,
        weatherDesc = "Violent rain showers",
        iconRes = R.drawable.ic_rainshower
    )
    object SlightSnowShowers: WeatherType(
        code = 85,
        weatherDesc = "Light snow showers",
        iconRes = R.drawable.ic_snowy
    )
    object HeavySnowShowers: WeatherType(
        code = 86,
        weatherDesc = "Heavy snow showers",
        iconRes = R.drawable.ic_snowy
    )
    object ModerateThunderstorm: WeatherType(
        code = 95,
        weatherDesc = "Moderate thunderstorm",
        iconRes = R.drawable.ic_thunder
    )
    object SlightHailThunderstorm: WeatherType(
        code = 96,
        weatherDesc = "Thunderstorm with slight hail",
        iconRes = R.drawable.ic_rainythunder
    )
    object HeavyHailThunderstorm: WeatherType(
        code = 99,
        weatherDesc = "Thunderstorm with heavy hail",
        iconRes = R.drawable.ic_rainythunder
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}