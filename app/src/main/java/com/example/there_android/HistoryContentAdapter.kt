package com.example.there_android

import android.content.Context
import android.os.Binder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.there_android.databinding.ItemHistoryBinding

class HistoryContentAdapter(val context : Context, val result: HistoryContentResponse.Result) :
    RecyclerView.Adapter<HistoryContentAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemHistoryBinding) :RecyclerView.ViewHolder(binding.root){
        val content = binding.historyContentTv

        fun bind(result : HistoryContentResponse.Result){
            binding.historyContentTv.text = result.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(result)
    }
}