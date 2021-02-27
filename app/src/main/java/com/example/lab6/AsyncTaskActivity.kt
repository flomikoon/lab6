package com.example.lab6

import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.databinding.ActivityMainBinding

class AsyncTaskActivity: AppCompatActivity() {
    var secondsElapsed: Int = 0
    private lateinit var mSettings: SharedPreferences
    private lateinit var timer: AsyncTimer
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
        timer = AsyncTimer(binding)
        timer.execute()
    }

    override fun onPause() {
        super.onPause()
        mSettings.edit().putInt("seconds",secondsElapsed).apply()
        timer.cancel(true)
    }

    inner class AsyncTimer(private val binding: ActivityMainBinding): AsyncTask<Void , Void , Void>(){
        override fun doInBackground(vararg params: Void?): Void? {
            while (!isCancelled) {
                Thread.sleep(1000)
                publishProgress()
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
            binding.textSecondsElapsed.text = "Second elapsed: " + secondsElapsed++
        }

    }

}
