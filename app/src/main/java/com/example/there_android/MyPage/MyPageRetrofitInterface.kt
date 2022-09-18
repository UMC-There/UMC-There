package com.example.there_android.MyPage

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MyPageRetrofitInterface {
    @GET("/users/feed/{userIdx}")
    fun getUserData(
        @Path("userIdx") userIdx : Int
    ): Call<MyPageResponse>

    @GET("/follow/{userIdx}/followerList")
    fun getFollowerData(
        @Path("userIdx") userIdx : Int
    ): Call<FollowResponse>

    @GET("/follow/{userIdx}/followingList")
    fun getFollowingData(
        @Path("userIdx") userIdx : Int
    ): Call<FollowResponse>
}