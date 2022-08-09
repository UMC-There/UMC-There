package com.example.there_android

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.there_android.databinding.ItemChatlistBinding

class ChatRVAdapter(val context: Context, val chatList: ArrayList<ChatData>) : RecyclerView.Adapter<ChatRVAdapter.ViewHolder>() {

    var datalist = mutableListOf<ChatData>()

    //viewholder 데이터가 틀 안에 들어갈 수 있게 하는 기능
    inner class ViewHolder(private val binding: ItemChatlistBinding): RecyclerView.ViewHolder(binding.root){
        val profile = binding.chatProfileIv
        val name = binding.chatNameTv
        val text = binding.chatTextTv
        val time = binding.chatTimeTv
        val count = binding.chatCountTv

        fun bind(chatData: ChatData){
            if(chatData.profile == ""){
                Glide.with(ChatFragment()).load(R.drawable.img_chat_profile).into(binding.chatProfileIv)
            }
            else{
                Glide.with(ChatFragment()).load(chatData.profile).into(binding.chatProfileIv)
            }
            binding.chatNameTv.text = chatData.nickname
            binding.chatTextTv.text = chatData.text
            binding.chatTimeTv.text = chatData.time.toString()
            binding.chatCountTv.text = chatData.count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val binding = ItemChatlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() : Int = datalist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(datalist[position])
    }
}