package com.example.lab6

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class CoroutineActivity: AppCompatActivity() {
    var secondsElapsed: Int = 0
    private lateinit var mSettings: SharedPreferences
    private lateinit var job: Job
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mSettings=getSharedPreferences("myPref", Context.MODE_PRIVATE)
        secondsElapsed=mSettings.getInt("seconds",0)
    }

    override fun onResume() {
        super.onResume()
        job = GlobalScope.launch(Dispatchers.Main){
            while (true){
                delay(1000)
                binding.textSecondsElapsed.text = "Second elapsed: " + secondsElapsed++
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mSettings.edit().putInt("seconds",secondsElapsed).apply()
        job.cancel()
    }
}