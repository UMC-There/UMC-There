package com.example.there_android

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Result? = null
){
    data class Result(
        @SerializedName("postIdx") val postIdx : Int
    )
}
