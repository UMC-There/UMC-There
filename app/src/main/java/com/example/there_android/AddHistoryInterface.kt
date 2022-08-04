package com.example.there_android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AddHistoryInterface {
    @POST("/historys")
    fun postHistory(
        @Body addHistoryRequest: AddHistoryRequest
    ): Call<AddHistoryResponse>
}