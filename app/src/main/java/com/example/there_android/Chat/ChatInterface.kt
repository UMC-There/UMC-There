package com.example.there_android.Chat

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatInterface {
    @GET("/chat/room/user/{userIdx}")
    fun getChat(
        @Path("userIdx") userIdx : Int
    ):Call<ChatResponse>
}