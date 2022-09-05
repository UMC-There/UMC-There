package com.example.there_android.MyPage

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PortfolioRetrofitInterface {
    @GET("/portfolios/user/{userIdx}")
    fun getUserPfols(
        @Path("userIdx") userIdx : Int
    ): Call<PortfolioResponse>

    @GET("/portfolios/pf/{portfolioIdx}")
    fun getPfolPosts(
        @Path("portfolioIdx") portfolioIdx : Int
    ): Call<PfolPostsResponse>
}