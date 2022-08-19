package com.example.there_android

interface MyPageWorkView {
    fun onMyPageWorkSuccess(result : MyPageResult)
    fun onMyPageWorkFailure (code: Int, message: String)
}