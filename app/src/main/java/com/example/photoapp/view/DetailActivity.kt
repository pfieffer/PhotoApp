package com.example.photoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.photoapp.R
import com.example.photoapp.model.Photo

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val photo = intent.getSerializableExtra(PHOTO) as Photo?

        photo?.webformatURL.let {
            Glide.with(this).load(photo?.webformatURL)
                .into(imageView)
        }

        //lambda
        imageView.setOnClickListener {
            finish()
        }
    }

    companion object {
        val PHOTO = "PHOTO"
    }
}
