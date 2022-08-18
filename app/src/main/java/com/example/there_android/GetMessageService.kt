package com.example.there_android

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetMessageService {
    private lateinit var getMessageView: GetMessageView

    private val getMessageRetrofit : GetMessageInterface = getRetrofit().create(GetMessageInterface::class.java)

    fun setMessageView(getMessageView: GetMessageView){
        this.getMessageView = getMessageView
    }

    fun getMessage(request: GetMessageRequest){
        getMessageRetrofit.getMessage(request.receiverIdx, request.roomIdx, request.senderIdx).enqueue(object : Callback<GetMessageResponse> {
            override fun onResponse(
                call: Call<GetMessageResponse>,
                response: Response<GetMessageResponse>
            ) {
                val resp : GetMessageResponse = response.body()!!
                Log.d("GETMESSAGE/SUCCESS", resp.message)
                Log.d("CHECK", resp.result.toString())
                when(resp.code){
                    200, 1000 -> getMessageView.onGetMessageSuccess(resp.result)
                    else -> getMessageView.onGetMessageFailure()
                }
            }

            override fun onFailure(call: Call<GetMessageResponse>, t: Throwable) {
                Log.d("GETMESSAGE/FAILURE", t.message.toString())
            }
        })
    }
}