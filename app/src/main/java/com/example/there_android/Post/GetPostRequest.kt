package com.example.there_android.Post

import com.google.gson.annotations.SerializedName

data class GetPostRequest(
    @SerializedName("postIdx") val postIdx : Int
)