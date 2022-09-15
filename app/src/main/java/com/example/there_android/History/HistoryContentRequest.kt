package com.example.there_android.History

import com.google.gson.annotations.SerializedName

data class HistoryContentRequest(
    @SerializedName("historyIdx") val historyIdx : Int
)
