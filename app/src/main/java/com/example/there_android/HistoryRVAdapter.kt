package com.example.there_android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.there_android.databinding.ItemHistoryBinding

class HistoryRVAdapter {

    var datalist = mutableListOf<HistoryData>()

    //viewholder 데이터가 틀 안에 들어갈 수 있게 하는 기능
    inner class ViewHolder(private val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(historyData: HistoryData){
            binding.historyTitleTv.text = historyData.title
            binding.historyDateTv.text = historyData.date
            //recyclerview 이미지
            binding.historyContentTv.text = historyData.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() : Int = datalist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(datalist[position])
    }
}