package com.example.there_android

import com.google.gson.annotations.SerializedName

data class GetMessageResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("Result") val result : Result
){
    data class Result(
        @SerializedName("additionalProp1") val additionalProp1 : List<AdditionalProp>,
        @SerializedName("additionalProp2") val additionalProp2: List<AdditionalProp>,
    ){
        data class AdditionalProp(
            @SerializedName("userIdx") val userIdx : Int,
            @SerializedName("content") val content : String,
            @SerializedName("created_At") val created_At : String
        )
    }
}
