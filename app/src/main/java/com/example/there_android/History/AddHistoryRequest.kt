package com.example.there_android.History

import com.google.gson.annotations.SerializedName
import java.io.File

data class AddHistoryRequest(
    @SerializedName("images") var images: Array<File>
)
