package com.example.there_android.Post

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetPostInterface {
    @GET("/posts/{postIdx}")
    fun getPost(
        @Path("postIdx") postIdx : Int
    ): Call<GetPostResponse>
}