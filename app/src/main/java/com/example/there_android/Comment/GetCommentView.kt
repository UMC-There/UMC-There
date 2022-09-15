package com.example.there_android.Comment

interface GetCommentView {
    fun onGetCommentSuccess(result : List<GetCommentResponse.Result>)
    fun onGetCommentFailure()
}