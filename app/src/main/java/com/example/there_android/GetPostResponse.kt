package com.example.there_android

import com.google.gson.annotations.SerializedName

data class GetPostResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : Result ?= null
){
    data class Result(
        @SerializedName("content") val content : String,
        @SerializedName("imgUrl") val imgUrl : String,
        @SerializedName("likeCount") val likeCount : Int,
        @SerializedName("nickName") val nickname : String,
        @SerializedName("postIdx") val postIdx : Int,
        @SerializedName("profileImgUrl") val profileImgUrl : String,
        @SerializedName("getPostTagist") val getPostTageist : List<GetPostTag>
    ){
        data class GetPostTag(
            @SerializedName("name") val name : String
        )
    }
}
