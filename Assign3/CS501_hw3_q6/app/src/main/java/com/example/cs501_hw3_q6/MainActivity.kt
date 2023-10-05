package com.example.cs501_hw3_q6

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.cs501_hw3_q6.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    /*
    * Log in activity
    * */
    private lateinit var binding: ActivityMainBinding

    // The launcher of the flash card activity
    private val flashCardLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result -> }

    override fun onCreate(savedInstanceState: Bundle?) {
        var username = ""
        var password = ""

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginSubmit.setOnClickListener{
            username = binding.loginName.text.toString()
            password = binding.loginPassword.text.toString()
            if(username == "User" && password == "12345"){
                //login name : User ; password: 12345
                Toast.makeText(this,"Welcome" + username, Toast.LENGTH_SHORT).show()
                val intent = FlashcardActivity.newIntent(this@MainActivity)
                flashCardLauncher.launch(intent)
            }
            else{
                Toast.makeText(this,"Incorrect Username/password", Toast.LENGTH_SHORT).show()
            }
        };
    }
}