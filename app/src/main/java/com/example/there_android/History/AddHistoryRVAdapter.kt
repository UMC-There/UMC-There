package com.example.there_android.History

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.there_android.R
import com.example.there_android.databinding.ItemAddhistoryImageBinding

class AddHistoryRVAdapter(val context: Context, private var images: List<com.esafirm.imagepicker.model.Image>) : RecyclerView.Adapter<AddHistoryRVAdapter.ViewHolder>(){
    inner class ViewHolder(private val binding: ItemAddhistoryImageBinding):RecyclerView.ViewHolder(binding.root){
        val image = binding.itemAddhistoryImageIv

        fun bind(image: com.esafirm.imagepicker.model.Image){
            Glide.with(binding.itemAddhistoryImageIv.context).load(image).into(binding.itemAddhistoryImageIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAddhistoryImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return images.size
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