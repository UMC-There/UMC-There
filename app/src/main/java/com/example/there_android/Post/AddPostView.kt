package com.example.there_android.Post

interface AddPostView {
    fun onAddPostSuccess()
    fun onAddPostFailure(code: Int, message: String)
}