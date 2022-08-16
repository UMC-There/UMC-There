package com.example.there_android

import com.gmail.bishoybasily.stomp.lib.StompClient
import okhttp3.OkHttpClient

interface GetMessageView {
    fun onGetMessageSuccess(result : List<GetMessageResponse.Result>)
    fun onGetMessageFailure()
    abstract fun StompClient(okHttpClient: OkHttpClient, reconnectAfter: Long, url: String): StompClient
}