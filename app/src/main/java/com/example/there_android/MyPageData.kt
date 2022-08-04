package com.example.there_android

data class MyPageData(
    val accountId : String = "",
    val name : String = "",
    val intro : String = "",
    var follower: Int = 0,
    var follow: Int = 0,

    var portfolioTitle : String = ""
)
