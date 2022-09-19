package com.example.there_android.MyPage

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "NoteTable")
data class NoteRequest (
    @SerializedName(value = "selfIntroduction") val selfIntroduction: String?,
    @SerializedName(value = "workIntroduction") val workIntroduction: String?,
    @SerializedName(value = "contact") val contact: String?,
)
