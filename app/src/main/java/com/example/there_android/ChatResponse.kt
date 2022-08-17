package com.example.there_android

import com.google.gson.annotations.SerializedName

data class ChatResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : List<Result>
){
    data class Result(
        @SerializedName("roomIdx") val roomIdx : Int,
        @SerializedName("senderIdx") val senderIdx: Int,
        @SerializedName("receiverIdx") val receiverIdx : Int,
        @SerializedName("nickName") val nickName: String,
        @SerializedName("profileImgUrl") val profileImgUrl : String,
        @SerializedName("count") val count : Int
    )
}
