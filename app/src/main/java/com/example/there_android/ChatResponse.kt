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
        @SerializedName("roomIdx") val roomIdx : Int,
        @SerializedName("getUserInfoRes") val getUserInfoRes : GetUserInfoRes?= null
    ){
        data class GetUserInfoRes(
            @SerializedName("nickname") val nickname : String,
            @SerializedName("profileImgUrl") val profileImgUrl : String
        )
    }
}
