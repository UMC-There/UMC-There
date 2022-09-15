package com.example.there_android.History

interface HistoryContentView {
    fun onHistoryContentSuccess(result : HistoryContentResponse.Result)
    fun onHistoryContentFailure()
}