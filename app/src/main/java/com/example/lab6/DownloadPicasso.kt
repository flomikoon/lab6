package com.example.lab6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.databinding.DownloadBinding
import com.squareup.picasso.Picasso

class DownloadPicasso: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DownloadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            Picasso.get().load(URL).into(binding.imageView2)
        }
    }
}