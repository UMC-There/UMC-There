package com.example.there_android

import com.google.gson.annotations.SerializedName

data class ChatRequest(
    @SerializedName("userIdx") val userIdx: Int
)
