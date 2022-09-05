package com.example.there_android.Post

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "AddPostTable")
data class AddPost(
    @SerializedName(value = "images")val images: String?,
    @SerializedName(value = "jsonList")val jsonList: PostContent,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
data class PostContent(
    @SerializedName(value = "content")val content: String?,
    @SerializedName(value = "hastag")val hastag: String?,
)


