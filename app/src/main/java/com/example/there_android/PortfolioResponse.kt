package com.example.there_android

import com.google.gson.annotations.SerializedName

//포트폴리오 리스트 조회
data class PortfolioResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<PortfolioResult>,
)
data class PortfolioResult(
    @SerializedName(value = "portfolioIdx") val portfolioIdx: Int,
    @SerializedName(value = "post_count") val post_count: Int,
    @SerializedName(value = "title") val title: String?,
)

//포트폴리오 내 포스트 조회
data class PfolPostsResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<PfolPostsResult>,
)
data class PfolPostsResult(
    @SerializedName(value = "contentIdx") val contentIdx: Int,
    @SerializedName(value = "postIdx") val postIdx: Int,
    @SerializedName(value = "imgUrl") val imgUrl: String?,
)
