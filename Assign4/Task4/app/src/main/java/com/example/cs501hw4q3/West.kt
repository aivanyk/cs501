package com.example.cs501hw4q3

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cs501hw4q3.databinding.ActivityWestBinding

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.animation.TranslateAnimation

class West : AppCompatActivity(), SensorEventListener {
    private lateinit var sensor: SensorManager
    private var shakeInterval: Long = 0
    private var shakeLevel = 0
    private lateinit var binding: ActivityWestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        sensor = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        binding = ActivityWestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        sensor.registerListener(this, sensor.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),SensorManager.SENSOR_DELAY_UI)

    }
    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            if (System.currentTimeMillis() - shakeInterval >= 1000L) {
                val acceleration = event.values[0] * event.values[0] + event.values[1] * event.values[1] + event.values[2] * event.values[2]
                shakeLevel += acceleration.toInt()
                if (shakeLevel > 350) {
                    val animation = TranslateAnimation(0.0f, 140.0f, 20.0f, 20.0f)
                    animation.duration = 4000
                    binding.image.startAnimation(animation)
                }
                shakeInterval = System.currentTimeMillis()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
}