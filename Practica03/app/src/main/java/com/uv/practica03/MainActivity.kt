package com.uv.practica03

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val u1 = "Alex"
    private val p1 = "12345"
    private val u2 = "Changuin"
    private val p2 = "54321"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun iniciarActividad(view: View) {
        val intent = Intent(this, MainActivity2::class.java)
        val et_usuario = findViewById<EditText>(R.id.Usuario_Field)
        val et_pass = findViewById<EditText>(R.id.Contrasena_Field)
        val et_apodo = findViewById<EditText>(R.id.Apodo_Field)
        val usuario = et_usuario.text.toString()
        val pass = et_pass.text.toString()
        val apodo = et_apodo.text.toString()

        //Guardar apodo de shared Preferences
        val sh = getSharedPreferences("Shared", MODE_PRIVATE)
        val edit = sh.edit()

        edit.putString("apodo", apodo)
        edit.putString("usuario", usuario)


        Toast.makeText(applicationContext,usuario,Toast.LENGTH_SHORT).show()
        Toast.makeText(applicationContext,pass,Toast.LENGTH_SHORT).show()

        intent.putExtra("Usuario", usuario)
        intent.putExtra("Contrasena", pass)
        startActivity(intent)
    }

    fun login (view: View){
        val u = findViewById<EditText>(R.id.Usuario_Field).text.toString()
        val p = findViewById<EditText>(R.id.Contrasena_Field).text.toString()
        val apodo = findViewById<EditText>(R.id.Apodo_Field).text.toString()

        //Guardar apodo de shared Preferences
        val sh = getSharedPreferences("SharedPreferences", MODE_PRIVATE)
        val edit = sh.edit()

        edit.putString("apodo", apodo)
        edit.putString("usuario", u)

        edit.apply()


        if( (u == u1 && p == p1) || (u == u2 && p == p2)){
            val intent = Intent(this, MainActivity2::class.java).apply {
                putExtra("Usuario", u)
                putExtra("Contrasena", p)
            }
            startActivity(intent)
        }else{
            Toast.makeText(applicationContext,"El usuario no existe",Toast.LENGTH_SHORT).show()
        }
    }
}