package com.example.there_android.Chat

import com.google.gson.annotations.SerializedName
import kotlin.collections.List

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
        @SerializedName("lastContent") val lastContent : String,
        @SerializedName("created_At") val created_At : String,
        @SerializedName("nickName") val nickName: String,
        @SerializedName("profileImgUrl") val profileImgUrl : String,
        @SerializedName("count") val count : Int
    )
}
