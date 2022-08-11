package com.example.there_android

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService {
    private lateinit var homeView : HomeView

    val getHomeRetrofit = getRetrofit().create(HomeInterface::class.java)

    fun setHomeView(homeView: HomeView){
        this.homeView = homeView
    }

    fun getHome(){
        getHomeRetrofit.getHome().enqueue(object : Callback<HomeResponse>{
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                val resp : HomeResponse = response.body()!!
                Log.d("GETHOME/SUCCESS", resp.message)
                when (resp.code){
                    200 -> homeView.onHomeViewSuccess(resp)
                    else -> homeView.onHomeViewFailure()
                }
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                Log.d("GETHOME/FAILURE", t.message.toString())
            }
        })
    }
}