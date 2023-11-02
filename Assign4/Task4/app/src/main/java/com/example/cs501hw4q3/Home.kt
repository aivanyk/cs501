package com.example.cs501hw4q3

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.animation.TranslateAnimation
import androidx.core.view.GestureDetectorCompat
import com.example.cs501hw4q3.databinding.ActivityHomeBinding



class Home : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var intent: Intent
    private lateinit var gesture: GestureDetectorCompat
    private lateinit var event: MotionEvent
    private lateinit var sensor: SensorManager
    private var shakeInterval: Long = 0
    private var shakeLevel = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        sensor = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        gesture = GestureDetectorCompat(this, FlingMover())
        sensor.registerListener(this, sensor.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),SensorManager.SENSOR_DELAY_UI)

    }
    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            if (System.currentTimeMillis() - shakeInterval > 10L) {
                var acceleration = event.values[0] * event.values[0] + event.values[1] * event.values[1] + event.values[2] * event.values[2]
                shakeLevel += acceleration.toInt()
                if (shakeLevel > 900) {
                    val animation = TranslateAnimation(0.0f, 140.0f, 20.0f, 20.0f)
                    animation.duration = 4000
                    binding.image.startAnimation(animation)
                }
                shakeInterval = System.currentTimeMillis()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    inner class FlingMover : GestureDetector.SimpleOnGestureListener(){
        override fun onFling(event1: MotionEvent, event2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            val x_distance = Math.abs(event2.x - event1.x)
            val y_distance = Math.abs(event2.y - event1.y)
            val moveJudgement = x_distance > y_distance
            if (moveJudgement) {
                if (event2.x - event1.x > 0 && x_distance > 10) {
                    startActivity(Intent(this@Home, East::class.java))
                } else if (event2.x - event1.x <= 0 && x_distance > 10) {
                    startActivity(Intent(this@Home, West::class.java))
                }
            }
                else if (event2.y - event1.y > 0 && y_distance > 10) {
                    startActivity(Intent(this@Home, South::class.java))
                } else if (event2.y - event1.y <= 0 && y_distance > 10) {
                    startActivity(Intent(this@Home, North::class.java))
                }
                return true
            }
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        gesture.onTouchEvent(event)
        return super.onTouchEvent(event)
    }
}