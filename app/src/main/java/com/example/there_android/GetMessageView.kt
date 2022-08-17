package com.example.there_android

import com.gmail.bishoybasily.stomp.lib.StompClient
import okhttp3.OkHttpClient

interface GetMessageView {
    fun onGetMessageSuccess(result : GetMessageResponse.Result)
    fun onGetMessageFailure()
}