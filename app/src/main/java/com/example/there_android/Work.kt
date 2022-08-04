package com.example.there_android

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "WorkTable")
data class Work(
    @SerializedName(value = "title")val title: String,
    //@SerializedName(value = "imgUrl")val imgUrl: String,
    @SerializedName(value = "content")val content: String,
    @SerializedName(value = "hashtag")val hashtag: String,
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
