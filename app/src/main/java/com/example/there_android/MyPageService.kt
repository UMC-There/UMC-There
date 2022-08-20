package com.example.there_android

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageService() {
    private lateinit var  myPageView: MyPageView
    //private lateinit var  myPageWorkView: MyPageWorkView

    fun setMyPageView(myPageView: MyPageView){
        this.myPageView = myPageView
    }
//    fun setMyPageWorkView(myPageWorkView: MyPageWorkView){
//        this.myPageWorkView = myPageWorkView
//    }
    //사용자 정보, post GET
    fun getMyData(userIdx : Int) {
        val myPageService = networkModule.getRetrofit().create(MyPageRetrofitInterface::class.java)

        myPageService.getUserData(userIdx).enqueue(object : Callback<MyPageResponse> {
            override fun onResponse(call: Call<MyPageResponse>, response: Response<MyPageResponse>) {
                if (response.isSuccessful && response.code() == 200) {
                    val myPageResponse: MyPageResponse = response.body()!!

                    Log.d("MyData-RESPONSE", myPageResponse.toString())

                    when (val code = myPageResponse.code) {
                        1000 -> {
                            myPageView.onMyPageSuccess(myPageResponse.result!!)
                            //myPageWorkView.onMyPageWorkSuccess(myPageResponse.result!!)
                        }
                        else -> {
                            myPageView.onMyPageFailure(code, myPageResponse.message)
                            //myPageWorkView.onMyPageWorkFailure(code, myPageResponse.message)
                        }
                        }
                    }
                }

            override fun onFailure(call: Call<MyPageResponse>, t: Throwable) {
                myPageView.onMyPageFailure(500, "서버 연결에 실패")
                //myPageWorkView.onMyPageWorkFailure(500, "서버 연결에 실패")
            }
        })
    }

    //사용자 포스트 GET
//    fun getUserPosts(userIdx : Int) {
//        val myPageService = networkModule.getRetrofit().create(MyPageRetrofitInterface::class.java)
//
//        myPageService.getUserData(userIdx).enqueue(object : Callback<MyPageResponse> {
//            override fun onResponse(call: Call<MyPageResponse>, response: Response<MyPageResponse>) {
//                if (response.isSuccessful && response.code() == 200) {
//                    val myPageResponse: MyPageResponse = response.body()!!
//
//                    Log.d("MyPosts-RESPONSE", myPageResponse.toString())
//
//                    when (val code = myPageResponse.code) {
//                        1000 -> myPageWorkView.onMyPageWorkSuccess(myPageResponse.result!!)
//                        else -> myPageWorkView.onMyPageWorkFailure(code, myPageResponse.message)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<MyPageResponse>, t: Throwable) {
//                myPageWorkView.onMyPageWorkFailure(500, "서버 연결에 실패")
//            }
//        })
//    }


}
