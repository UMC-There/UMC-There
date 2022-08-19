package com.example.there_android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URLs = "https://recordinthere.shop/"

fun getRetrofit(): Retrofit {

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URLs)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit
}