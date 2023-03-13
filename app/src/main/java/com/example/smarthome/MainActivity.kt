package com.example.smarthome

import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var userNameTextField: TextInputEditText
    private lateinit var passwordTextField: TextInputEditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userNameTextField = findViewById<TextInputLayout>(R.id.textInputLayout).editText as TextInputEditText
        passwordTextField = findViewById<TextInputLayout>(R.id.textInputLayout3).editText as TextInputEditText
        loginButton = findViewById(R.id.button2)

        loginButton.setOnClickListener {
            login()
        }
    }



    fun login() {

        val url = Uri.parse("http://172.17.122.180/login")
            .buildUpon()
            .build().toString()

        val args = JSONObject().apply {
            put("username", userNameTextField.text.toString())
            put("password", passwordTextField.text.toString())
        }

        val request = JsonObjectRequest(Request.Method.POST, url, args, { res ->
            response(res)
            Toast.makeText(this, "Conectado", Toast.LENGTH_SHORT)
                .show()
        }, {
            Toast.makeText(this, "Error en la petición", Toast.LENGTH_SHORT)
                .show()
        })

        MySingleton.getInstance(applicationContext).addToRequestQueue(request)
    }

    private fun response(res: JSONObject) {
        val token = res.getString("token")
        val preferences = getSharedPreferences("user", MODE_PRIVATE)
        preferences.setUserToken(token)
    }



}


fun SharedPreferences.setUserToken(token: String) {
    val editor = this.edit()
    editor.putString("token", token)
    editor.apply()
}

fun SharedPreferences.getUserToken(): String? {
    return this.getString("token", null)
}