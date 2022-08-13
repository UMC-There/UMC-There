package com.example.there_android

import com.google.gson.annotations.SerializedName

data class ChatResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : List<Result>
){
    data class Result(
        @SerializedName("count") val count : Int,
        @SerializedName("nickName") val nickName: String,
        @SerializedName("profileImgUrl") val profileImgUrl : String,
        @SerializedName("roomIdx") val roomIdx : Int
    )
}
