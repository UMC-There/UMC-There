package com.example.there_android.MyPage

interface MyPageView {
    fun onMyPageSuccess(result : MyPageResult)
    fun onMyPageFailure (code: Int, message: String)
}