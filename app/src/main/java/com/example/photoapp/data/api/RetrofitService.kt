package com.example.photoapp.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {

    var loggingInterceptor = HttpLoggingInterceptor()
    val httpClient = OkHttpClient.Builder()

    init {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        // add loggingInterceptor as last interceptor, Add other interceptors before it
        httpClient.addInterceptor(loggingInterceptor)
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pixabay.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}