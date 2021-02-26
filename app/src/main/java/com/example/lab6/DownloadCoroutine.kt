package com.example.lab6

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.databinding.DownloadBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.lang.Exception
import java.net.URL

class DownloadCoroutine: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DownloadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {
                val image = downloadImage(URL)
                launch(Dispatchers.Main){
                    binding.imageView2.setImageBitmap(image)
                }
            }
            }
        }

    private fun downloadImage(url: String): Bitmap? {
        var image: Bitmap? = null
        try {
            val inputStream: InputStream = URL(url).openStream()
            image = BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception){
            Log.e("Error" , e.message.toString())
        }
        return image
    }
}