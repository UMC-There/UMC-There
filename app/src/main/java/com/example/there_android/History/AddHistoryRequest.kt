package com.example.there_android.History

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.io.File

data class AddHistoryRequest(
    @SerializedName("images") var images: Array<File> ? = null,
    @SerializedName("jsonList") var jsonList : JSONObject
)
