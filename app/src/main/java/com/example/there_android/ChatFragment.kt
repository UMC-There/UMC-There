package com.example.there_android

import android.graphics.Insets.add
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.there_android.databinding.FragmentChatBinding

class ChatFragment : Fragment(), ChatView {

    lateinit var  binding: FragmentChatBinding

//    var chatList = arrayListOf<ChatData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadData()

        binding = FragmentChatBinding.inflate(inflater, container, false)


        return binding.root
    }

    private fun setAdapter(chatList : ArrayList<ChatData>){
        val mAdapter = this.context?.let { ChatRVAdapter(it, chatList) }
        binding.chatRecyclerview.adapter = mAdapter
        binding.chatRecyclerview.layoutManager = LinearLayoutManager(this.context)
        binding.chatRecyclerview.setHasFixedSize(true)
    }

    private fun getContent(): ChatRequest{
        //임의의 값
        val userIdx : Int = 2
        return ChatRequest(userIdx)
    }

    private fun loadData(){
        val chatService = ChatService()
        chatService.setChatView(this)
        chatService.getChat(getContent())
    }

    override fun onChatSuccess(result: List<ChatResponse.Result>) {
        fun getList(): ArrayList<ChatData> {
            var chatList = arrayListOf<ChatData>()
            for (result in result) {
                var count = result.count
                var nickname = result.getUserInfoRes?.nickname
                var profileImgUrl = result.getUserInfoRes?.profileImgUrl
                var roomIdx = result.roomIdx

                chatList.add(ChatData(nickname!!, 9, "text", count, profileImgUrl!!))
            }
            return chatList
        }
        setAdapter(getList())
    }

    override fun onChatFailure() {
        Log.d("CHAT/SERVER", "not connected")
    }


}