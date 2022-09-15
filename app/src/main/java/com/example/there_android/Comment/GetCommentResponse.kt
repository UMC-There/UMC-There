package com.example.there_android.Comment

import com.google.gson.annotations.SerializedName

data class GetCommentResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : List<Result> ?= null
){
    data class Result(
        @SerializedName("content") val content : String,
        @SerializedName("created_At") val created_At : String,
        @SerializedName("nickName") val nickName : String,
        @SerializedName("profileImgUrl") val profileImgUrl : String
    )
}
