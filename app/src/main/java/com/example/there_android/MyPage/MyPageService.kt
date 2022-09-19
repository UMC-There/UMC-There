package com.example.there_android.MyPage

import android.util.Log
import com.example.there_android.Auth.UserAuthRequest
import com.example.there_android.Auth.UserResponse
import com.example.there_android.Auth.UserRetrofitInterface
import com.example.there_android.utils.networkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageService() {
    private lateinit var  myPageView: MyPageView
    private lateinit var  followView: FollowView
    private lateinit var  noteView: NoteView

    fun setMyPageView(myPageView: MyPageView){
        this.myPageView = myPageView
    }
    fun setFollowView(followView: FollowView){
        this.followView = followView
    }
    fun  setNoteView(noteView: NoteView){
        this.noteView = noteView
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
                        }
                        else -> {
                            followView.onFollowFailure(code, followResponse.message)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<FollowResponse>, t: Throwable) {
               followView.onFollowFailure(500, "서버 연결에 실패")
            }
        })
    }

    //팔로잉 리스트
    fun getFollowingList(userIdx : Int) {
        val myPageService = networkModule.getRetrofit().create(MyPageRetrofitInterface::class.java)

        myPageService.getFollowingData(userIdx).enqueue(object : Callback<FollowResponse> {
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
                        }
                        else -> {
                            followView.onFollowFailure(code, followResponse.message)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<FollowResponse>, t: Throwable) {
                followView.onFollowFailure(500, "서버 연결에 실패")
            }
        })
    }

    //작가노트 조회
    fun getNote(userIdx : Int) {
        val myPageService = networkModule.getRetrofit().create(MyPageRetrofitInterface::class.java)

        myPageService.getNoteData(userIdx).enqueue(object : Callback<NoteResponse> {
            override fun onResponse(
                call: Call<NoteResponse>,
                response: Response<NoteResponse>
            ) {
                if (response.isSuccessful && response.code() == 200) {
                    val noteResponse: NoteResponse = response.body()!!

                    Log.d("Note-RESPONSE", noteResponse.toString())

                    when (val code = noteResponse.code) {
                        1000 -> {
                            noteView.onNoteSuccess(noteResponse.result!!)
                        }
                        else -> {
                            noteView.onNoteFailure(code, noteResponse.message)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<NoteResponse>, t: Throwable) {
                noteView.onNoteFailure(500, "서버 연결에 실패")
            }
        })
    }

    fun postNote(noteRequest: NoteRequest, userIdx : Int) {
        val myPageService = networkModule.getRetrofit().create(MyPageRetrofitInterface::class.java)

        myPageService.postNote(userIdx, noteRequest).enqueue(object : Callback<PostNoteResponse> {
            override fun onResponse(
                call: Call<PostNoteResponse>,
                response: Response<PostNoteResponse>
            ) {
                if (response.isSuccessful && response.code() == 200) {
                    val postNoteResponse : PostNoteResponse = response.body()!!

                    Log.d("Note-RESPONSE", postNoteResponse.toString())

                    when (val code = postNoteResponse.code) {
                        1000 -> {
                            noteView.onPostNoteSuccess(postNoteResponse.result!!)
                        }
                        else -> {
                            noteView.onPostNoteFailure(code, postNoteResponse.message)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PostNoteResponse>, t: Throwable) {
                noteView.onPostNoteFailure(500, "서버 연결에 실패")
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
