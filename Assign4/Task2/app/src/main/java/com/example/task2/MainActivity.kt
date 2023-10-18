package com.example.task2

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.task2.databinding.ActivityMainBinding
import kotlin.math.abs

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mSensorManager : SensorManager
    private var mAccelerometer : Sensor ?= null

    var resume = false
    var threshold = 10
    var lastToastTime: Long = 0


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onSensorChanged(event: SensorEvent?) {
//        Log.d(TAG, "$resume AND ${event != null}")
//        if (event != null && resume)
        if (event != null){
            Log.d(TAG, "Listening......")

            if (event.sensor.type == Sensor.TYPE_LINEAR_ACCELERATION) {

                if (abs(event.values[0]) >= threshold || abs(event.values[1]) >= threshold || abs(event.values[2]) >= threshold) {
                    val currentTime = SystemClock.uptimeMillis()
                    val elapsedTime = currentTime - lastToastTime

                    // Set a minimum interval between toasts (e.g., 2 seconds)
                    val minInterval = 2000

                    if (elapsedTime >= minInterval) {
                        Toast.makeText(
                            this,
                            "The phone's accelerator is greater than " + threshold.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        lastToastTime = currentTime
                    }

                }
                Log.d(TAG, "The phone's accelerator is ${event.values[0]} ${event.values[1]} ${event.values[2]}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)

        binding.Threshold.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                binding.valueDisplay.text = progress.toString()
                threshold = progress
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
            }
        })

    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }

    fun resumeReading(view: View) {
        this.resume = true
    }

    fun pauseReading(view: View) {
        this.resume = false
    }

}
