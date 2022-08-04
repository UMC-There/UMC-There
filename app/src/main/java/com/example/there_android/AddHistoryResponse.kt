package com.example.there_android

import com.google.gson.annotations.SerializedName

data class AddHistoryResponse(
    @SerializedName("code") var code : Int,
    @SerializedName("isSuccess") var isSuccess : Boolean,
    @SerializedName("message") var message : String,
    @SerializedName("result") var result : Result? = null
){
    data class Result(
        @SerializedName("historyIdx") var historyIdx : Int
    )
}
