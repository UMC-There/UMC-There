package com.example.there_android.Post

import android.util.Log
import com.example.there_android.utils.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPostService {
    private lateinit var getPostView: GetPostView

    private val getPostRetrofit : GetPostInterface = getRetrofit().create(GetPostInterface::class.java)

    fun setGetPostView(getPostView: GetPostView){
        this.getPostView = getPostView
    }

    fun getPost(request : GetPostRequest){
        getPostRetrofit.getPost(request.postIdx).enqueue(object : Callback<GetPostResponse>{
            override fun onResponse(
                call: Call<GetPostResponse>,
                response: Response<GetPostResponse>
            ) {
                val resp : GetPostResponse = response.body()!!
                Log.d("GETPOST/SUCCESS", resp.message)
                when(resp.code){
                    1000 -> getPostView.onGetPostSuccess(resp.result!!)
                    else -> getPostView.onGetPostFailure()
                }
            }

            override fun onFailure(call: Call<GetPostResponse>, t: Throwable) {
                Log.d("GETPOST/FAILURE", t.message.toString())
            }
        })
    }
}