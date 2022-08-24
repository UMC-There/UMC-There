package com.example.there_android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AddPostRetrofitInterface {
    @POST("/posts/users/{userIdx}")
    fun postPost(
        @Path("userIdx") userIdx : Int,
        @Body addPost: AddPost
    ): Call<AddPostResponse>
}