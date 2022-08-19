package com.example.there_android

interface MyPageView {
    fun onMyPageSuccess(result : MyPageResult)
    fun onMyPageFailure (code: Int, message: String)
}