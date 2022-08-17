package com.example.there_android

interface GetPostView {
    fun onGetPostSuccess(result : GetPostResponse.Result)
    fun onGetPostFailure()
}