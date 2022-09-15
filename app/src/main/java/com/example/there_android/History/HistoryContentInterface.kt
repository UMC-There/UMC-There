package com.example.there_android.History

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HistoryContentInterface {
    @GET("/historys/{historyIdx}")
    fun getHistory(
        @Path("historyIdx") historyIdx : Int
    ): Call<HistoryContentResponse>
}