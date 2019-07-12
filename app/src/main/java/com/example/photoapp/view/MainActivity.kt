package com.example.photoapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photoapp.R
import com.example.photoapp.model.Photo
import com.example.photoapp.viewModel.PhotosViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var photos: List<Photo> = ArrayList()

    private var mainAdapter: MainAdapter? = null
    private lateinit var recyclerView: RecyclerView //allows for us to initialize the variable "later down the road"
    private lateinit var noPhotosTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()
        noPhotosTV = findViewById(R.id.tv_no_photos)

        val photosViewModel = ViewModelProviders.of(this).get(PhotosViewModel::class.java)

        photosViewModel.getPhotosRepository().observe(this, Observer {
            //            for ( photo in it.hits)
//                Log.d("MainActivity", photo.previewURL)
            if (it == null) {
                noPhotosTV.visibility = View.VISIBLE
                return@Observer
            }
            mainAdapter?.loadItems(it.hits)
            mainAdapter?.notifyDataSetChanged()
        })

    }

    private fun setUpRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)//this is "later down the road"
        recyclerView.layoutManager = LinearLayoutManager(this)

        mainAdapter = MainAdapter(this@MainActivity.photos, this@MainActivity)
        recyclerView.adapter = mainAdapter
    }

    override fun onClick(view: View?) {
        //onClick Method for mainAdapter
        val intent = Intent(this, DetailActivity::class.java)
        val photoViewHolder = view?.tag as MainAdapter.PhotoViewHolder
        intent.putExtra(
            DetailActivity.PHOTO,
            mainAdapter?.getPhoto(photoViewHolder.adapterPosition)
        )

        startActivity(intent)
    }
}
