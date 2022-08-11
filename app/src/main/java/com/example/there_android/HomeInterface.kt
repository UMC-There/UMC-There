package com.example.there_android

import retrofit2.Call
import retrofit2.http.GET

interface HomeInterface {
    @GET("/posts/rankingAndfollowerPostList")
    fun getHome(): Call<HomeResponse>
}