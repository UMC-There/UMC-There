package com.example.there_android.MyPage

import android.util.Log
import com.example.there_android.utils.networkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageService() {
    private lateinit var  myPageView: MyPageView
    private lateinit var  followView: FollowView

    fun setMyPageView(myPageView: MyPageView){
        this.myPageView = myPageView
    }
    fun setFollowView(followView: FollowView){
        this.followView = followView
    }

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

    //팔로워 리스트
    fun getFollowerList(userIdx : Int) {
        val myPageService = networkModule.getRetrofit().create(MyPageRetrofitInterface::class.java)

        myPageService.getFollowerData(userIdx).enqueue(object : Callback<FollowResponse> {

            override fun onResponse(
                call: Call<FollowResponse>,
                response: Response<FollowResponse>
            ) {
                if (response.isSuccessful && response.code() == 200) {
                    val followResponse: FollowResponse = response.body()!!

                    Log.d("Follow-RESPONSE", followResponse.toString())

                    when (val code = followResponse.code) {
                        1000 -> {
                            followView.onFollowSuccess(followResponse.result!!)
                            //myPageWorkView.onMyPageWorkSuccess(myPageResponse.result!!)
                        }
                        else -> {
                            followView.onFollowFailure(code, followResponse.message)
                            //myPageWorkView.onMyPageWorkFailure(code, myPageResponse.message)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<FollowResponse>, t: Throwable) {
               followView.onFollowFailure(500, "서버 연결에 실패")
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
