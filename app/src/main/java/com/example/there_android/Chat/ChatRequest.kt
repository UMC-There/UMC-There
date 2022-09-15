package com.example.there_android.Chat

import com.google.gson.annotations.SerializedName

data class ChatRequest(
    @SerializedName("userIdx") val userIdx: Int
)
