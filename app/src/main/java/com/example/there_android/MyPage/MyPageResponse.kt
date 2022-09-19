package com.example.there_android.MyPage

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

//팔로워,팔로잉 리스트
data class FollowResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<FollowerList>?,
)
data class FollowerList(
    @SerializedName(value = "nickName") val nickName: String?,
    @SerializedName(value = "profileImgUrl") val profileImgUrl: String?
)

//작가노트 조회
data class NoteResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: NoteResult?,
)
data class NoteResult(
    @SerializedName(value = "selfIntroduction") val selfIntroduction: String?,
    @SerializedName(value = "workIntroduction") val workIntroduction: String?,
    @SerializedName(value = "contact") val contact: String?,
    @SerializedName("statementIdx") val statementIdx: Int,
    @SerializedName("userIdx") val userIdx: Int,
)

//작가노트 작성
data class PostNoteResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String?,
)
//data class PostNoteResult(
//    @SerializedName("statementIdx") val statementIdx: Int,
//)