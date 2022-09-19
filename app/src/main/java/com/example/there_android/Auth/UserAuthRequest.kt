package com.example.there_android.Auth

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserTable")
data class UserAuthRequest(
    @SerializedName(value = "nickName")val nickName: String?,
    @SerializedName(value = "email")val email: String,
    @SerializedName(value = "password")val password: String,
    @SerializedName(value = "checkpwd")val checkpwd: String?,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
