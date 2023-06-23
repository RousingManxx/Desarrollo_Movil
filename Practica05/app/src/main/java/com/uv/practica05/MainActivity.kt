package com.uv.practica05

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.uv.practica05.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val texto = binding.tvMensaje
        val queue = Volley.newRequestQueue(this)
        val url = "https://jsonplaceholder.typicode.com/users"


//          Hacer solicitudes a una url
//        val url = "https://www.google.com"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                response -> texto.text = "Response is: ${response.substring(0, 500)}"
//            },
//            { texto.text = "That didn't work!" })
//        queue.add(stringRequest)

//        Hacer solicitudes a un api recibiendo objetos json
        val jsonRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                val users = mutableListOf<String>()
                for (i in 0 until response.length()){
                        val obj = JSONObject(response.get(i).toString())
//                        texto.append("Nombre: " + obj.getString("name") + "Direccion: " + obj.getString("email")+ "\n")
                        val user = "Nombre: " + obj.getString("name") + "Direccion: " + obj.getString("email")
                    users.add(user)
                    addAdapter(users)
                }
            },
            { error ->
                texto.text = "That didn't work!"
            }
        )
        queue.add(jsonRequest)
    }

    fun addAdapter(json: MutableList<String>){
        val arrayAdapter = ArrayAdapter(this, R.layout.simple_list_item_1,json)
        binding.list.adapter = arrayAdapter
    }
}