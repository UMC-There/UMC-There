package com.example.there_android

import com.google.gson.annotations.SerializedName
import kotlin.collections.List

data class GetMessageResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : List<Result>
){
    data class Result(
        @SerializedName("userIdx") val userIdx : Int,
        @SerializedName("content") val content : String,
        @SerializedName("created_At") val created_At : String
        )
}