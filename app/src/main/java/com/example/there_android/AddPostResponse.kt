package com.example.there_android

import com.google.gson.annotations.SerializedName

data class AddPostResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: AddPostResult?,
)
data class AddPostResult(
    @SerializedName("postIdx") val postIdx : Int

)
