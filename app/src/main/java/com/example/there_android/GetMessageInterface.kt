package com.example.there_android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetMessageInterface {
    @GET("/chat/room/{roomIdx}/user/{senderIdx}/{receiverIdx}")
    fun getMessage(
        @Path("receiver") receiver : Int,
        @Path("roomIdx") roomIdx : Int,
        @Path("senderIdx") senderIdx : Int
    ): Call<GetMessageResponse>
}