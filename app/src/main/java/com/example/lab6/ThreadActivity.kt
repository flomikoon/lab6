package com.example.lab6

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class ThreadActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    private lateinit var mSettings: SharedPreferences

    private var backgroundThread: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSettings=getSharedPreferences("myPref", Context.MODE_PRIVATE)
        secondsElapsed=mSettings.getInt("seconds",0)
    }

    override fun onResume() {
        super.onResume()
        backgroundThread = Thread {
            try {
                while (!Thread.currentThread().isInterrupted) {
                    Thread.sleep(1000)
                    textSecondsElapsed.post {
                        textSecondsElapsed.text = "Seconds elapsed: " + secondsElapsed++
                    }
                }
            } catch (e: InterruptedException){}
        }.apply { start() }
    }

    override fun onPause() {
        super.onPause()
        mSettings.edit().putInt("seconds",secondsElapsed).apply()
        backgroundThread?.interrupt()
    }
}
