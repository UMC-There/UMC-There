package com.example.there_android.History

import com.google.gson.annotations.SerializedName

data class HistoryContentResponse(
    @SerializedName("code") val code : Int,
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : Result
){
    data class Result(
        @SerializedName("content") val content : String,
        @SerializedName("createdAt") val createdAt : String,
        @SerializedName("dayOfWeek") val dayOfWeek : String,
        @SerializedName("historyIdx") val historyIdx : Int,
        @SerializedName("title") val title : String,
        @SerializedName("getHistoryPicturesRes") val getHistoryPicturesRes : List<Picture>
    ){
        data class Picture(
            @SerializedName("imgUrl") val imgUrl : String,
            @SerializedName("pictureIdx") val pictureIdx : Int
            )
    }
}
