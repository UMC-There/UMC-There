package com.example.there_android

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MyPageTable")
data class MyPageData(
    val accountId : String = "",
    val name : String = "",
    val intro : String = "",
    var follower: Int = 0,
    var follow: Int = 0,

    var portfolioTitle : String = ""
)

