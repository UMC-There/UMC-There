package com.example.there_android

import com.google.gson.annotations.SerializedName

data class GetMessageRequest(
    @SerializedName("receiverIdx") var receiverIdx : Int,
    @SerializedName("roomIdx") var roomIdx : Int,
    @SerializedName("senderIdx") var senderIdx : Int
)
