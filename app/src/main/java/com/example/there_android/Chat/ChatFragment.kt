package com.example.there_android.Chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.there_android.utils.GlobalApplication
import com.example.there_android.databinding.FragmentChatBinding
import com.example.there_android.databinding.ItemChatlistBinding
import kotlin.collections.List

class ChatFragment : Fragment(), ChatView {

    lateinit var  binding: FragmentChatBinding

    private val userIdx : Int = GlobalApplication.spf.spfIdx!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadData()

        Log.d("CHECK", userIdx.toString())
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun setAdapter(chatList : List<ChatResponse.Result>){
        //val mAdapter = ChatRVAdapter(chatList)
        //val mAdapter = this.context?.let { ChatRVAdapter(it, chatList) }
        val mAdapter = ChatRVAdapter(this.requireContext(), chatList)
        binding.chatRecyclerview.adapter = mAdapter
        binding.chatRecyclerview.layoutManager = LinearLayoutManager(this.context)
        binding.chatRecyclerview.setHasFixedSize(true)
    }

    private fun getContent(): ChatRequest {
        return ChatRequest(userIdx)
    }

    private fun loadData(){
        val chatService = ChatService()
        chatService.setChatView(this)
        chatService.getChat(getContent())
    }


    override fun onChatSuccess(result: List<ChatResponse.Result>) {
        setAdapter(result)
    }


    override fun onChatFailure(code : Int) {
        Log.d("CHAT/SERVER", code.toString())
    }


}