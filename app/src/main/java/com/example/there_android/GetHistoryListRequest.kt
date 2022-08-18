package com.example.there_android

import com.google.gson.annotations.SerializedName

data class GetHistoryListRequest(
    @SerializedName("postIdx") val postIdx : Int
)
