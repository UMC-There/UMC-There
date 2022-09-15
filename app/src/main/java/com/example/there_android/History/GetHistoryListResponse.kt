package com.example.there_android.History

import com.google.gson.annotations.SerializedName
import kotlin.collections.List

data class GetHistoryListResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : List<Result>
){
    data class Result(
        @SerializedName("historyIdx") val historyIdx : Int,
        @SerializedName("title") val title : String,
        @SerializedName("createdAt") val createdAt : String,
        @SerializedName("dayOfWeek") val datOfWeek : String

    )
}
