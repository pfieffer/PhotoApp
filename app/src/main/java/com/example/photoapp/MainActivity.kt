package com.example.photoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photoapp.api.PhotoRetriever
import com.example.photoapp.model.Photo
import com.example.photoapp.model.PhotoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var photos: List<Photo>? = null

    var mainAdapter: MainAdapter? = null
    lateinit var recyclerView: RecyclerView //allows for us to initialize the variable "later down the road"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView) as RecyclerView//this is "later down the road"
        recyclerView.layoutManager = LinearLayoutManager(this)

        var retriever = PhotoRetriever()
        Log.d("MainActivity", "HERE")

        val callback = object : Callback<PhotoList> {

            override fun onResponse(call: Call<PhotoList>, response: Response<PhotoList>) {
                response.isSuccessful.let {

                    Log.d("MainActivity", response.message())
                    this@MainActivity.photos = response.body()?.hits
                    mainAdapter = MainAdapter(this@MainActivity.photos!!, this@MainActivity)
                    recyclerView.adapter = mainAdapter
                }
            }

            override fun onFailure(call: Call<PhotoList>, t: Throwable) {
                Log.e("MainActivity", "Problem calling the api", t)
            }

        }

        retriever.getPhotos(callback) //making the call

    }

    override fun onClick(view: View?) {
        //onClick Method for mainAdapter
    }
}
