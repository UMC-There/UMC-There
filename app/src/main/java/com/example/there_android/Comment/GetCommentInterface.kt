package com.example.there_android.Comment

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetCommentInterface {
    @GET("/comments/{postIdx}")
    fun getComment(
        @Path("postIdx") postIdx : Int
    ): Call<GetCommentResponse>
}