package com.example.hw3question5

import android.app.assist.AssistContent
import androidx.appcompat.app.AppCompatActivity
import com.example.hw3question5.databinding.ActivityMainBinding
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val converter =  Converter()

        binding.celsiusSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                binding.celsius.text = progress.toString()
                binding.fahrenheitSeekBar.progress = converter.toFahrenheit(progress)
                binding.fahrenheit.text = binding.fahrenheitSeekBar.progress.toString()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {

            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                if(seek.progress<=20) {
                    Snackbar.make(binding.root,"I wish it were warmer.",Snackbar.LENGTH_SHORT).show()
                }else{
                    Snackbar.make(binding.root,"I wish it were colder.",Snackbar.LENGTH_SHORT).show()
                }
            }
        })

        binding.fahrenheitSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                binding.fahrenheit.text = progress.toString()
                binding.celsiusSeekBar.progress = converter.toCelsius(progress)
                binding.celsius.text = binding.celsiusSeekBar.progress.toString()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {

            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                if(seek.progress<=32) {
                    seek.progress = 32
                    binding.fahrenheit.text = seek.progress.toString()
                }
                if(seek.progress<=68) {
                    Snackbar.make(binding.root,"I wish it were warmer.",Snackbar.LENGTH_SHORT).show()
                }else{
                    Snackbar.make(binding.root,"I wish it were colder.",Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }



}

class Converter {
    fun  toCelsius(fahrenheit : Int): Int {
        return (fahrenheit-32)*5/9
    }

    fun  toFahrenheit(celsius : Int): Int {
        return celsius*9/5+32
    }
}