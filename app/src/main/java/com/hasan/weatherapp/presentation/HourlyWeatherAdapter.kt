package com.hasan.weatherapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hasan.weatherapp.R
import com.hasan.weatherapp.databinding.ListItemBinding
import com.hasan.weatherapp.domain.weather.WeatherData
import java.time.format.DateTimeFormatter

class HourlyWeatherAdapter : RecyclerView.Adapter<HourlyWeatherAdapter.ViewHolder>() {

    var data = listOf<WeatherData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount():Int = data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data[position]

        holder.binding.time.text = item.time.format(DateTimeFormatter.ofPattern("HH:mm"))
        holder.binding.type.setImageResource(item.weatherType.iconRes)
        holder.binding.temp.text = item.temperatureCelsius.toString()+"°C"

    }

    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

//    class ViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(itemView){
//
//        val time: TextView = itemView.findViewById(R.id.time)
////        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
////        val quality: TextView = itemView.findViewById(R.id.quality_string)
////        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)
//
//    }
}