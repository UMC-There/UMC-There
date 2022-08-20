package com.example.there_android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PortfolioRetrofitInterface {
    @GET("/portfolio/user/{userIdx}")
    fun getUserPfols(
        @Path("userIdx") userIdx : Int
    ): Call<PortfolioResponse>

//    @GET("/portfolio/{portfolioIdx}")
//    fun getPfolPosts(
//        @Path("portfolioIdx") portfolioIdx : Int
//    ): Call<PfolPostsResponse>
}