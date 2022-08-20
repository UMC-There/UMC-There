package com.example.there_android

import com.google.gson.annotations.SerializedName

data class MyPageDataResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: MyPageDataResult?,
)
data class MyPageDataResult(
    @SerializedName(value = "email")val email: String,
    @SerializedName(value = "followerIdx")val followerIdx: Int,
    @SerializedName(value = "followeeIdx")val followeeIdx:  Int,
    @SerializedName(value = "info")val info: String,
    @SerializedName(value = "name")val name: String?,
    @SerializedName(value = "nickName")val nickName: String?,
    @SerializedName(value = "profileImgUrl")val profileImgUrl: String,
    @SerializedName(value = "userIdx")val userIdx:  Int,
)
