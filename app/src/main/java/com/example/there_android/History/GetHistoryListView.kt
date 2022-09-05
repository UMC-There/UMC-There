package com.example.there_android.History

import kotlin.collections.List

interface GetHistoryListView {
    fun onGetHistorySuccess(result : List<GetHistoryListResponse.Result>)
    fun onGetHistoryFailure()
}