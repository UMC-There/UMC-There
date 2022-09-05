package com.example.there_android.History

import com.google.gson.annotations.SerializedName

data class GetHistoryListRequest(
    @SerializedName("postIdx") val postIdx : Int
)
