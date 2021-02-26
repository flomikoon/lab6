package com.example.lab6

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.databinding.DownloadBinding
import java.io.InputStream
import java.lang.Exception
import java.net.URL

const val URL = "https://i.ibb.co/8cvn9LZ/dinosaur-5995333-1920.png"
class DownloadAsync: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DownloadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            downloadImage(binding).execute(URL)
        }
    }

    inner class downloadImage(private val binding: DownloadBinding): AsyncTask<String , Void , Bitmap>(){
        override fun doInBackground(vararg params: String?): Bitmap? {
            val urls= params.first()
            var image: Bitmap? = null

            try {
                val inputStream: InputStream = URL(urls).openStream()
                image = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception){
                Log.e("Error" , e.message.toString())
            }
            return image
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            binding.imageView2.setImageBitmap(result)
        }
    }
}