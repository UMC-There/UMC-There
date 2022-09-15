package com.example.there_android.Chat

import kotlin.collections.List

interface ChatView {
    fun onChatSuccess(result: List<ChatResponse.Result>)
    fun onChatFailure(code : Int)
}