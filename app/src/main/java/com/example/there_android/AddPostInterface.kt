package com.example.there_android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AddPostInterface {
    @POST("/posts/user")
    fun addpost(@Body work: Work) : Call<AddPostResponse>
}