package com.example.there_android

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.there_android.databinding.ItemMyChattingBinding
import com.example.there_android.databinding.ItemYourChattingBinding

class ChattingRVAdapter(val context: Context, val arrayList: ArrayList<ChattingModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal lateinit var preferences: SharedPreferences

    fun addItem(item : ChattingModel){
        if (arrayList != null){
            arrayList.add(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1){ //viewtype이 1인 경우, 내 채팅 레이아웃
            val binding = ItemMyChattingBinding.inflate(LayoutInflater.from(context), parent, false)
            return Holder(binding)
        }
        else{
            val binding = ItemYourChattingBinding.inflate(LayoutInflater.from(context), parent, false)
            return Holder2(binding)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holder){
            (holder as Holder).text?.setText(arrayList.get(position).script)
            (holder as Holder).time?.setText(arrayList.get(position).time)
        }
        else if (holder is Holder2){
            (holder as Holder2).text?.setText(arrayList.get(position).script)
            (holder as Holder2).text?.setText(arrayList.get(position).time)
        }
    }

    inner class Holder(private val binding: ItemMyChattingBinding) : RecyclerView.ViewHolder(binding.root){
        val text = binding.myChattingTextTv
        val time = binding.myChattingTimeTv
    }

    inner class Holder2(private val binding: ItemYourChattingBinding) : RecyclerView.ViewHolder(binding.root){
        val text = binding.yourChattingTextTv
        val time = binding.yourChattingTimeIv
        val profile = binding.yourChattingProfileIv
    }

    override fun getItemViewType(position: Int): Int {
        preferences = context.getSharedPreferences("USERSIGN", Context.MODE_PRIVATE)

        return if(arrayList.get(position).name == preferences.getString("name", "")){
            1
        }
        else{
            2
        }
    }
}