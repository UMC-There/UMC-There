package com.example.there_android

interface HistoryContentView {
    fun onHistoryContentSuccess(result : HistoryContentResponse.Result)
    fun onHistoryContentFailure()
}