package com.example.there_android

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PortfolioService() {
    private lateinit var  portfolioView: PortfolioView
    private lateinit var  pfolPostView: PfolPostView

    fun setPortfolioView(portfolioView: PortfolioView){
        this.portfolioView = portfolioView
    }
    fun setPfolPostView(pfolPostView: PfolPostView){
        this.pfolPostView = pfolPostView
    }

    //사용자 포트폴리오 리스트 GET
    fun getUserPfol(userIdx : Int) {
        val portfolioService = networkModule.getRetrofit().create(PortfolioRetrofitInterface::class.java)

        portfolioService.getUserPfols(userIdx).enqueue(object : Callback<PortfolioResponse> {
            override fun onResponse(call: Call<PortfolioResponse>, response: Response<PortfolioResponse>) {
                if (response.isSuccessful && response.code() == 200) {
                    val portfolioResponse: PortfolioResponse = response.body()!!

                    Log.d("MyPortfolio-RESPONSE", portfolioResponse.toString())

                    when (val code = portfolioResponse.code) {
                        1000 -> portfolioView.onPortfolioSuccess(portfolioResponse.result)
                        else -> portfolioView.onPortfolioFailure(code, portfolioResponse.message)
                    }
                }
            }

            override fun onFailure(call: Call<PortfolioResponse>, t: Throwable) {
                Log.d("MyPortfolio-FAILURE", "서버 연결에 실패")
                portfolioView.onPortfolioFailure(500, "서버 연결에 실패")
            }
        })
    }

    //포트폴리오 내 포스트 리스트 GET
    fun getPfolPost(portfolioIdx : Int) {
        val portfolioService = networkModule.getRetrofit().create(PortfolioRetrofitInterface::class.java)

        portfolioService.getPfolPosts(portfolioIdx).enqueue(object : Callback<PfolPostsResponse> {
            override fun onResponse(call: Call<PfolPostsResponse>, response: Response<PfolPostsResponse>) {
                if (response.isSuccessful && response.code() == 200) {
                    val pfolPostResponse: PfolPostsResponse = response.body()!!

                    Log.d("MyPfolPost-RESPONSE", pfolPostResponse.toString())

                    when (val code = pfolPostResponse.code) {
                        1000 -> pfolPostView.onPfolPostSuccess(pfolPostResponse.result)
                        else -> pfolPostView.onPfolPostFailure(code, pfolPostResponse.message)
                    }
                }
            }

            override fun onFailure(call: Call<PfolPostsResponse>, t: Throwable) {
                Log.d("MyPfolPost-FAILURE", "서버 연결에 실패")
                pfolPostView.onPfolPostFailure(500, "서버 연결에 실패")
            }
        })
    }
}