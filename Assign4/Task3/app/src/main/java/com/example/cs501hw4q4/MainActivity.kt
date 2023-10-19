package com.example.cs501hw4q4

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Camera
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.text.set
import com.example.cs501hw4q4.databinding.ActivityMainBinding
import java.lang.reflect.Parameter

class MainActivity : AppCompatActivity() {
    private lateinit var camera: CameraManager
    private lateinit var binding: ActivityMainBinding
    private lateinit var switch: Switch
    private lateinit var cameraID : String
    private lateinit var cameraIDList : Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        camera = getSystemService(CAMERA_SERVICE) as CameraManager
        cameraIDList = camera.cameraIdList
        binding = ActivityMainBinding.inflate(layoutInflater)
        switch = binding.switchBar
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        switch.setOnCheckedChangeListener{ _, on ->
            if(on){
                flashLightAvailability(true)
                Toast.makeText(this,"flashlight on",Toast.LENGTH_SHORT).show()
            }else{
                flashLightAvailability(false)
                Toast.makeText(this,"flashlight off",Toast.LENGTH_SHORT).show()
            }
        }
        binding.command.setOnClickListener{
            if(binding.command.text.toString() == "ON"){
                binding.switchBar.isChecked=true
                Toast.makeText(this,"flashlight on",Toast.LENGTH_SHORT).show()
            }
            if(binding.command.text.toString() == "OFF"){
                binding.switchBar.isChecked=false
                Toast.makeText(this,"flashlight off",Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun flashLightAvailability(onOff: Boolean){
        if(this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            for (cameraSeq in cameraIDList) {
                var isFlashLightAvailable = camera.getCameraCharacteristics(cameraSeq).get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
                if (isFlashLightAvailable == true) {
                    Toast.makeText(this,cameraSeq,Toast.LENGTH_SHORT).show()
                    cameraID = cameraSeq
                    camera.setTorchMode(cameraID, onOff)
                    break
                }
            }
        }
    }

}