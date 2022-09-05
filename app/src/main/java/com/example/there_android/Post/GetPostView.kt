package com.example.there_android.Post

interface GetPostView {
    fun onGetPostSuccess(result : GetPostResponse.Result)
    fun onGetPostFailure()
}