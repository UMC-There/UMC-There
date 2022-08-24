//package com.example.there_android
//
//import android.content.Context
//import android.os.Binder
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.there_android.databinding.ItemHistoryBinding
//
//class HistoryContentRVAdapter(val context : Context, val result: HistoryContentResponse.Result) : RecyclerView.Adapter<HistoryContentRVAdapter.ViewHolder>{
//    inner class ViewHolder(private val binding: ItemHistoryBinding) :RecyclerView.ViewHolder(binding.root){
//        val content = binding.historyContentTv
//
//        fun bind(result : HistoryContentResponse.Result){
//            binding.historyContentTv.text = result.content
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//
//    }
//
//    override fun getItemCount(): Int {
//        return
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//    }
//}