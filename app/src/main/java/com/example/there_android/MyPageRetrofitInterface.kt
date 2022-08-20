package com.example.there_android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MyPageRetrofitInterface {
    @GET("/users/feed/{userIdx}")
    fun getUserData(
        @Path("userIdx") userIdx : Int
    ): Call<MyPageResponse>
}