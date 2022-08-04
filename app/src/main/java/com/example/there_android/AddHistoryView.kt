package com.example.there_android

interface AddHistoryView {
    fun onAddHistorySuccess(code : Int, message:String)
    fun onAddHistoryFailure()
}