package com.example.there_android

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRetrofitInterface {
    @POST("/users/join")
    fun join(@Body user: User): Call<UserResponse>

    @POST("/users/login")
    fun login(@Body user: User): Call<UserResponse>
}