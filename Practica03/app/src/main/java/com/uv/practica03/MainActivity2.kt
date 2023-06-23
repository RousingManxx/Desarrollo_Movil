package com.uv.practica03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val sh = getSharedPreferences("SharedPreferences", MODE_PRIVATE)
        val apodo = sh.getString("apodo","")

        val usuario = intent.getStringExtra("Usuario")
        val pass = intent.getStringExtra("Contrasena")

        Toast.makeText(applicationContext,"El usuario existe", Toast.LENGTH_SHORT).show()

        val txt_bienvenida = findViewById<TextView>(R.id.vista_u)
        txt_bienvenida.setText("Bienvenido tu usuario es: "+usuario)

        val txt_bienvenida_pass = findViewById<TextView>(R.id.vista_p)
        txt_bienvenida_pass.setText("Bienvenido tu constraseña es: "+pass)

        val txt_bienvenida_apodo = findViewById<TextView>(R.id.vista_ap)
        txt_bienvenida_apodo.setText("Bienvenido tu apodo es: "+apodo)
    }
}