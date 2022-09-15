package com.example.there_android.Auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRetrofitInterface {
    @POST("/users/join")
    fun join(@Body userAuth: UserAuth): Call<UserResponse>

    @POST("/users/login")
    fun login(@Body userAuth: UserAuth): Call<UserResponse>
}