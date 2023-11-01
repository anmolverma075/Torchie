 package com.example.flashlight

import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flashlight.databinding.ActivityMainBinding


 class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            if(binding.button.text.toString() == "Turn On"){
                binding.button.setText("Turn Off")
                binding.flashlight.setImageResource(R.drawable.flash_on)
                changeLightState(true)
            }
            else{
                binding.button.setText("Turn On")
                binding.flashlight.setImageResource(R.drawable.flashlight)
                changeLightState(false)
            }
        }
    }

     private fun changeLightState(state: Boolean) {
         if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){ //optional statement
             val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager // as CameraManager is used for type casting
             var camId : String? = null
             try{
                 camId = cameraManager.cameraIdList[0] //back camera has it's id on 1st idx
                 //implementing this in try catch as sometime the camera module maybe crashed
                 cameraManager.setTorchMode(camId , state)
             } catch (e : CameraAccessException){
                 e.printStackTrace()
             }
         }
     }

     override fun onStart() {
         super.onStart()
         binding.button.setText("Turn On")
     }
 }