package com.example.there_android.History

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetHistoryListInterface {
    @GET("/historys/posts/{postIdx}")
    fun getHistory(
        @Path("postIdx") postIdx : Int
    ): Call<GetHistoryListResponse>
}