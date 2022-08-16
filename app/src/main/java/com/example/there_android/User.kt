package com.example.there_android

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserTable")
data class User(
    @SerializedName(value = "name")val name: String?,
    @SerializedName(value = "email")val email: String,
    @SerializedName(value = "password")val password: String,
    @SerializedName(value = "checkpwd")val checkpwd: String?,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
