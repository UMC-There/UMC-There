package com.example.there_android.Chat

import android.util.Log
import com.example.there_android.utils.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatService {
    private lateinit var chatView: ChatView

    val getChat : ChatInterface = getRetrofit().create(ChatInterface::class.java)

    fun setChatView(chatView: ChatView){
        this.chatView = chatView
    }

    fun getChat(request: ChatRequest){
        getChat.getChat(request.userIdx).enqueue(object : Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                val resp: ChatResponse = response.body()!!
                Log.d("GETCHAT/SUCCESS", resp.message)
                when(resp.code){
                    200, 1000 -> chatView.onChatSuccess(resp.result!!)
                    else -> chatView.onChatFailure(resp.code)
                }
            }

            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                Log.d("GETCHAT/FAILURE", t.message.toString())
            }
        })
    }
}