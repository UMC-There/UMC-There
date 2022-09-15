package com.example.there_android.Home

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : Result?= null
){
    data class Result(
        @SerializedName("additionalProp1") val addtionalProp1 : String
    )
}
