package com.example.there_android.Comment

import android.util.Log
import com.example.there_android.utils.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class GetCommentService {

    private lateinit var getCommentView: GetCommentView

    var getCommentRetrofit : GetCommentInterface = getRetrofit().create(GetCommentInterface::class.java)

    fun setGetCommentView(getCommentView: GetCommentView){
        this.getCommentView = getCommentView
    }

    fun getComment(request: GetCommentRequest){
        getCommentRetrofit.getComment(request.postIdx).enqueue(object : Callback<GetCommentResponse>{
            override fun onResponse(
                call: Call<GetCommentResponse>,
                response: Response<GetCommentResponse>
            ) {
                val resp = response.body()!!
                when(resp.code){
                    200 -> getCommentView.onGetCommentSuccess(resp.result!!)
                    else -> getCommentView.onGetCommentFailure()
                }
            }

            override fun onFailure(call: Call<GetCommentResponse>, t: Throwable) {
                Log.d("GETCOMMENT/FAILURE", t.message.toString())
            }
        })
    }
}