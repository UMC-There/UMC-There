package com.example.there_android

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.there_android.databinding.ItemHistoryBinding

class HistoryRVAdapter(val context : Context, val result : List<GetHistoryListResponse.Result>): RecyclerView.Adapter<HistoryRVAdapter.ViewHolder>()  {

    //viewholder 데이터가 틀 안에 들어갈 수 있게 하는 기능
    inner class ViewHolder(private val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root){
        val title = binding.historyTitleTv
        val date = binding.historyDateTv
        val day = binding.historyDayTv

        fun bind(historyResult : GetHistoryListResponse.Result){
            binding.historyTitleTv.text = historyResult.title
            binding.historyDateTv.text = historyResult.createAt
            binding.historyDayTv.text = historyResult.datOfWeek

            binding.historyHeaderCl.setOnClickListener {
                if(binding.historyContentCl.visibility == View.INVISIBLE){
                    binding.historyContentCl.visibility = View.VISIBLE
                }
                else{
                    binding.historyContentCl.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() : Int{
        Log.d("CHECK", result.size.toString())
        return result.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(result[position])
    }
}