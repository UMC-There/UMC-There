package com.example.there_android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetHistoryListInterface {
    @GET("/history/{postIdx}")
    fun getHistory(
        @Path("postIdx") postIdx : Int
    ): Call<GetHistoryListResponse>
}