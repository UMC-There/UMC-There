package com.example.there_android

import com.google.gson.annotations.SerializedName
import kotlin.collections.List

data class GetHistoryListResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : List<Result>?= null
){
    data class Result(
        @SerializedName("createAt") val createAt : String,
        @SerializedName("dayOfWeek") val datOfWeek : String,
        @SerializedName("historyIdx") val historyIdx : Int,
        @SerializedName("title") val title : String
    )
}
