package com.example.there_android.MyPage

import com.example.there_android.Post.AddPost
import com.example.there_android.Post.AddPostResponse
import retrofit2.Call
import retrofit2.http.*

interface MyPageRetrofitInterface {
    //유저 피드 조회
    @GET("/users/feed/{userIdx}")
    fun getUserData(
        @Path("userIdx") userIdx : Int
    ): Call<MyPageResponse>

    //팔로우 조회
    @GET("/follow/{userIdx}/followerList")
    fun getFollowerData(
        @Path("userIdx") userIdx : Int
    ): Call<FollowResponse>
    //팔로잉 조회
    @GET("/follow/{userIdx}/followingList")
    fun getFollowingData(
        @Path("userIdx") userIdx : Int
    ): Call<FollowResponse>

    //작가노트 조회
    @GET("/statements/users/{userIdx}")
    fun getNoteData(
        @Path("userIdx") userIdx : Int
    ): Call<NoteResponse>
    //작가노트 작성
    @PATCH("/statements/users/{userIdx}")
    fun postNote(
        @Path("userIdx") userIdx : Int,
        @Body noteRequest: NoteRequest
    ): Call<PostNoteResponse>
}