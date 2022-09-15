package com.example.there_android.Post

import android.util.Log
import com.example.there_android.utils.networkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPostService {
    private lateinit var addPostView: AddPostView
    fun setAddPostView(addPostView: AddPostView){
        this.addPostView = addPostView
    }
    
    fun addPost(userIdx: Int, addPost: AddPost){
        val addPostService = networkModule.getRetrofit().create(AddPostRetrofitInterface::class.java)

        addPostService.postPost(userIdx, addPost).enqueue(object : Callback<AddPostResponse> {
            override fun onResponse(
                call: Call<AddPostResponse>,
                response: Response<AddPostResponse>
            ) {
                if (response.isSuccessful && response.code() == 200) {
                    val addPostResponse: AddPostResponse = response.body()!!

                    Log.d("AddPost-RESPONSE", addPostResponse.toString())

                    when (val code = addPostResponse.code) {
                        1000 -> {
                            addPostView.onAddPostSuccess()
                            Log.d("AddPost-SUCCESS", addPostResponse.toString())
                        }
                        else -> {
                            addPostView.onAddPostFailure(code, addPostResponse.message)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<AddPostResponse>, t: Throwable) {
                addPostView.onAddPostFailure(500, "서버 연결에 실패")
            }
        })
    }    
   
    
}