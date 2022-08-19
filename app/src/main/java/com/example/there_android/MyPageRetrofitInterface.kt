package com.example.there_android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MyPageRetrofitInterface {
    @GET("/users/{userIdx}")
    fun getMyData(
        @Path("userIdx") userIdx : Int
    ): Call<MyPageResponse>

    @GET("/users/feed/{userIdx}")
    fun getUserPosts(
        @Path("userIdx") userIdx : Int
    ): Call<MyPageResponse>
}