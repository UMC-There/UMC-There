package com.example.there_android

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.there_android.databinding.ItemAddhistoryImageBinding

class MultiImageAdapter(private val items : ArrayList<Uri>, val context: Context): RecyclerView.Adapter<MultiImageAdapter.ViewHolder>(){
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Glide.with(context).load(item)
            .into(holder.image)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemAddhistoryImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemAddhistoryImageBinding) : RecyclerView.ViewHolder(binding.root){
        var image = binding.itemAddhistoryImageIv
        fun bind(){
            //binding.itemAddhistoryImageIv
        }
    }
}