package com.example.there_android

interface ChatView {
    fun onChatSuccess(result: List<ChatResponse.Result>)
    fun onChatFailure()
}