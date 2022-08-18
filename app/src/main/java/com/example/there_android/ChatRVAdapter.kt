package com.example.there_android

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.there_android.databinding.ItemChatlistBinding
import kotlin.collections.List

class ChatRVAdapter(val context: Context, val chatList: List<ChatResponse.Result>) : RecyclerView.Adapter<ChatRVAdapter.ViewHolder>() {

    //viewholder 데이터가 틀 안에 들어갈 수 있게 하는 기능
    inner class ViewHolder(private val binding: ItemChatlistBinding): RecyclerView.ViewHolder(binding.root){
        val profile = binding.chatProfileIv
        val name = binding.chatNameTv
        val text = binding.chatTextTv
        val time = binding.chatTimeTv
        val count = binding.chatCountTv

        fun bind(chatData: ChatResponse.Result){
            Log.d("Recyclervew/Adapter", "bind")
            if(chatData.profileImgUrl == ""){
                Glide.with(binding.chatProfileIv.context).load(R.drawable.img_chat_profile).into(binding.chatProfileIv)
            }
            else{
                Glide.with(binding.chatProfileIv.context).load(chatData.profileImgUrl).into(binding.chatProfileIv)
            }
            binding.chatNameTv.text = chatData.nickName
            binding.chatTextTv.text = chatData.lastContent
            binding.chatTimeTv.text = chatData.created_At
            binding.chatCountTv.text = chatData.count.toString()

            itemView.setOnClickListener {
                val intent = Intent(context, ChattingRoomActivity::class.java)
                intent.putExtra("roomIdx", chatData.roomIdx)
                intent.putExtra("senderIdx", chatData.senderIdx)
                intent.putExtra("receiverIdx", chatData.receiverIdx)
                Log.d("CHECK/INTENT/ChatRVA", "${chatData.receiverIdx}, ${chatData.roomIdx}, ${chatData.senderIdx}")
                intent.run { context.startActivity(intent) }
//                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val binding = ItemChatlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(chatList[position])
    }

}