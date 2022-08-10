package com.example.there_android

interface GetMessageView {
    fun onGetMessageSuccess(response : GetMessageResponse)
    fun onGetMessageFailure()
}