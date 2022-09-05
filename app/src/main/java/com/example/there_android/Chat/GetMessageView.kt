package com.example.there_android.Chat

interface GetMessageView {
    fun onGetMessageSuccess(result : List<GetMessageResponse.Result>)
    fun onGetMessageFailure()
}