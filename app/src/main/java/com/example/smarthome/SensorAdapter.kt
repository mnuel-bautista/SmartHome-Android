package com.example.smarthome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class SensorAdapter(
    private val values : List<Sensor>
): ListAdapter<Sensor, SensorAdapter.SensorViewHolder>(SensorDiffCallback) {

    inner class SensorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(sensor: Sensor) {
            itemView.findViewById<TextView>(R.id.sensor_id_text).text = sensor.id.toString()
            itemView.findViewById<TextView>(R.id.sensor_text).text = sensor.sensor
            itemView.findViewById<TextView>(R.id.value_text).text = sensor.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sensor_item, parent, false)
        return SensorViewHolder(view)
    }

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        holder.bind(values[position])
    }

}

private val SensorDiffCallback = object : DiffUtil.ItemCallback<Sensor>() {
    override fun areItemsTheSame(oldItem: Sensor, newItem: Sensor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Sensor, newItem: Sensor): Boolean {
        return oldItem == newItem
    }
}