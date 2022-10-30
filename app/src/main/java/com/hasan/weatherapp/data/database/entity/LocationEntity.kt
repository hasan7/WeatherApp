package com.hasan.weatherapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationEntity(

    @PrimaryKey
    val isLocationRequested: Boolean? = null

)