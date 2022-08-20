package com.example.there_android

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PortfolioService() {
    private lateinit var  portfolioView: PortfolioView

    fun setPortfolioView(portfolioView: PortfolioView){
        this.portfolioView = portfolioView
    }

    //사용자 포트폴리오 GET
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
}