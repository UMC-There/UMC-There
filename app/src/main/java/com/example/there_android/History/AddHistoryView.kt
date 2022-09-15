package com.example.there_android.History

interface AddHistoryView {
    fun onAddHistorySuccess(code : Int, message:String)
    fun onAddHistoryFailure()
}