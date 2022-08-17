package com.example.there_android

import com.google.gson.annotations.SerializedName

data class GetPostRequest(
    @SerializedName("postIdx") val postIdx : Int
)