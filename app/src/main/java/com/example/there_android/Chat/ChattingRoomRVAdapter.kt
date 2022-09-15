package com.example.there_android.Chat

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.there_android.databinding.ItemMyChattingBinding
import com.example.there_android.databinding.ItemYourChattingBinding

class ChattingRoomRVAdapter (val context : Context, val messageList : List<GetMessageResponse.Result>, val request : GetMessageRequest) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class ViewHolder1(private val binding: ItemMyChattingBinding) : RecyclerView.ViewHolder(binding.root){
        val myContext = binding.myChattingTextTv
        val myTime = binding.myChattingTimeTv

//        fun bind(message: GetMessageResponse.Result){
//            binding.myChattingTextTv.text = message.content
//            binding.myChattingTimeTv.text = message.created_At
//        }
    }

    inner class ViewHolder2(private val binding: ItemYourChattingBinding): RecyclerView.ViewHolder(binding.root){
        val yourContext = binding.yourChattingTextTv
        val yourTime = binding.yourChattingTimeIv
        //val yourProfile = binding.yourChattingProfileIv

//        fun bind(message: GetMessageResponse.Result){
//            binding.yourChattingTextTv.text = message.content
//            binding.yourChattingTimeIv.text = message.created_At
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1){
            val binding = ItemMyChattingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder1(binding)
        }
        else{
            val binding = ItemYourChattingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder2(binding)
        }
    }

    override fun getItemCount(): Int {
        Log.d("CHECK/SIZE", messageList.size.toString())
        return messageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        if (holder is ViewHolder1){
            holder.myContext.text = messageList[position].content
            holder.myTime.text = messageList[position].created_At
            Log.d("CHECK", "${messageList[position]}, ${getItemViewType(position)} ${holder.myContext.text}")

        }
        else if (holder is ViewHolder2){
            holder.yourContext.text = messageList[position].content
            holder.yourTime.text = messageList[position].created_At
            Log.d("CHECK", "${messageList[position]}, ${getItemViewType(position)} ${holder.yourContext.text}")

        }
    }

    override fun getItemViewType(position: Int): Int {

        return if (messageList[position].userIdx == request.senderIdx){
            1
        }else{
            2
        }
    }
}