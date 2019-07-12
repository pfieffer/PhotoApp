package com.example.photoapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.photoapp.R
import com.example.photoapp.model.Photo
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val photo = intent.getSerializableExtra(PHOTO) as Photo?

        photo?.webformatURL.let {
            Glide.with(this).load(photo?.webformatURL)
                .into(detail_image_view)
        }

        //lambda
        detail_image_view.setOnClickListener {
            finish()
        }
    }

    companion object {
        val PHOTO = "PHOTO"
    }
}
