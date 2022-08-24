package com.example.there_android

interface AddPostView {
    fun onAddPostSuccess()
    fun onAddPostFailure(code: Int, message: String)
}