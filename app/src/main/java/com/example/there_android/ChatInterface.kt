package com.example.there_android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatInterface {
    @GET("chat/room/user/{userIdx}")
    fun getChat(
        @Path("userIdx") userIdx : String
    ):Call<ChatResponse>
}