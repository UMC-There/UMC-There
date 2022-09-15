package com.example.there_android.MyPage

interface PortfolioView {
    fun onPortfolioSuccess(result : List<PortfolioResult>)
    fun onPortfolioFailure (code: Int, message: String)
}

interface PfolPostView {
    fun onPfolPostSuccess(result : List<PfolPostsResult>)
    fun onPfolPostFailure (code: Int, message: String)
}