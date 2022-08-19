package com.example.there_android

import android.service.autofill.UserData
import com.google.gson.annotations.SerializedName

data class MyPageResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: MyPageResult?,
)
data class MyPageResult(
    @SerializedName("getUserPosts") val userPosts: List<UserPosts>,
    @SerializedName("getUserRes") val user: User,
)
data class UserPosts(
    @SerializedName(value = "imgUrl") val imgUrl: String,
    @SerializedName(value = "postIdx") val postIdx: Int,
)
data class User(
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "followerIdx") val followerIdx: Int,
    @SerializedName(value = "followeeIdx") val followeeIdx: Int,
    @SerializedName(value = "info") val info: String,
    @SerializedName(value = "name") val name: String?,
    @SerializedName(value = "nickName") val nickName: String?,
    @SerializedName(value = "profileImgUrl") val profileImgUrl: String,
    @SerializedName(value = "userIdx") val userIdx: Int,
)