package com.uv.practica06

import android.content.Context
import android.graphics.Color
import android.hardware.*
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uv.practica06.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sm: SensorManager
    private var sensor: Sensor? = null
    private lateinit var sable: MediaPlayer
    private lateinit var chubaca: MediaPlayer
    private var banA = true
    private var banB = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Se construye un objeto que permite acceder a los sensores del dispositivo
        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        //Especificar el sensor que se va a usar
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        //Inicializar sonidos
        sable = MediaPlayer.create(this,R.raw.sable)
        chubaca = MediaPlayer.create(this,R.raw.chubaca)

        if (sensor == null)
            binding.txtMensaje.setText("El dispositivo no cuenta con el sensor Acelerometro")

        setContentView(binding.root)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        var layout = binding.layout

        val x = event!!.values[0]
        val y = event!!.values[1]
        val z = event!!.values[2]
        binding.txtMensaje.setText("X: $x Y: $y Z: $z")

        if (x <= 6 && x >= -6) {
            layout.setBackgroundColor(Color.GREEN)
            try {
                sable.stop()
                sable.prepare()
                banA = true
                chubaca.stop()
                chubaca.prepare()
                banB = true
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }else if(x > 6){
            layout.setBackgroundColor(Color.RED)
            binding.txtMensaje.setText("SABLE")
            if (banA){
                sable.start()
            }
            banA = false
        }else if (x < -6) {
            layout.setBackgroundColor(Color.BLUE)
            binding.txtMensaje.setText("CHEWEBACCA")
            if (banB){
                chubaca.start()
            }
            banB= false
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onResume() {
        super.onResume()
        sensor?.also { aceleometro ->
            sm.registerListener(this, aceleometro, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sm.unregisterListener(this)
    }
}