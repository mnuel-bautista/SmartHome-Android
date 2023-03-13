package com.example.smarthome

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Request.Method.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val reciclerView =
            findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.sensor_recycler_view)

        val addValueButton = findViewById<Button>(R.id.add_value_button)

        addValueButton.setOnClickListener {
            val sensor = findViewById<EditText>(R.id.sensor_text_field).text.toString()
            val value = findViewById<EditText>(R.id.value_text_field).text.toString()
            addSensorValue(sensor, value)
        }

        val adapter: SensorAdapter = SensorAdapter(
            listOf(
                Sensor(1, "Sensor 1", "20"),
                Sensor(2, "Sensor 2", "30"),
                Sensor(3, "Sensor 3", "40"),
                Sensor(4, "Sensor 4", "50"),
                Sensor(5, "Sensor 5", "60"),
                Sensor(6, "Sensor 6", "70"),
                Sensor(7, "Sensor 7", "80"),
                Sensor(8, "Sensor 8", "90"),
                Sensor(9, "Sensor 9", "100"),
                Sensor(10, "Sensor 10", "110"),
                Sensor(11, "Sensor 11", "120"),
                Sensor(12, "Sensor 12", "130"),
                Sensor(13, "Sensor 13", "140"),
                Sensor(14, "Sensor 14", "150"),
                Sensor(15, "Sensor 15", "160"),
                Sensor(16, "Sensor 16", "170"),
                Sensor(17, "Sensor 17", "180"),
                Sensor(18, "Sensor 18", "190"),
                Sensor(19, "Sensor 19", "200"),
                Sensor(20, "Sensor 20", "210"),
                Sensor(21, "Sensor 21", "220"),
                Sensor(22, "Sensor 22", "230"),
                Sensor(23, "Sensor 23", "240"),
                Sensor(24, "Sensor 24", "250"),
                Sensor(25, "Sensor 25", "260"),
                Sensor(26, "Sensor 26", "270"),
                Sensor(27, "Sensor 27", "280"),
                Sensor(28, "Sensor 28", "290"),
                Sensor(29, "Sensor 29", "300"),
                Sensor(30, "Sensor 30", "310"),
                Sensor(31, "Sensor 31", "320"),
                Sensor(32, "Sensor 32", "330"),
                Sensor(33, "Sensor 33", "340"),
                Sensor(34, "Sensor 34", "350"),
                Sensor(35, "Sensor 35", "360"),
                Sensor(36, "Sensor 36", "370"),
                Sensor(37, "Sensor 37", "380"),
                Sensor(38, "Sensor 38", "390")
            )
        )

        reciclerView.adapter = adapter
        reciclerView.layoutManager = LinearLayoutManager(this)

    }


    private fun getSensorValues() {
        val url = Uri.parse("http://172.17.122.180/sensors")
            .buildUpon()
            .build().toString()

        val request = object: JsonArrayRequest(GET, url, null, { res ->
            response(res)
            Toast.makeText(this, "Se agregó el valor", Toast.LENGTH_SHORT)
                .show()
        }, {
            Toast.makeText(this, "Error en la petición", Toast.LENGTH_SHORT)
                .show()
        }) {

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer " + getSharedPreferences("user", MODE_PRIVATE).getString("token", "")
                return headers
            }

        }

        MySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun response(res: JSONArray) {

    }

    private fun addSensorValue(sensor: String, value: String) {
        val url = Uri.parse("http://172.17.122.180/sensors")
            .buildUpon()
            .build().toString()

        val body = JSONObject().apply {
            put("sensor", sensor)
            put("value", value)
        }

        val request = JsonObjectRequest(POST, url, body, { res ->
            Toast.makeText(this, "Conectado", Toast.LENGTH_SHORT)
                .show()
        }, {
            Toast.makeText(this, "Error en la petición", Toast.LENGTH_SHORT)
                .show()
        })
    }
}