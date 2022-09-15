package com.example.there_android.Comment

import com.google.gson.annotations.SerializedName

data class GetCommentRequest(
    @SerializedName("postIdx") val postIdx : Int
)
