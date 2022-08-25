package com.example.there_android

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.there_android.databinding.ItemAddhistoryImageBinding

class AddHistoryRVAdapter(val context: Context, private var images: List<String>) : RecyclerView.Adapter<AddHistoryRVAdapter.ViewHolder>(){
    inner class ViewHolder(private val binding: ItemAddhistoryImageBinding):RecyclerView.ViewHolder(binding.root){
        val image = binding.itemAddhistoryImageIv

        fun bind(image: String){
            Glide.with(binding.itemAddhistoryImageIv.context).load(image).into(binding.itemAddhistoryImageIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAddhistoryImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(images.isNotEmpty()) images.size else 1
        Log.d("CHECK", images.size.toString())
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (images.isEmpty()){
            Glide.with(context)
                .load(R.drawable.img_addhistory_no_image_big)
                .into(holder.image)
        }
        else{
            holder.bind(images[position])
        }
    }
}