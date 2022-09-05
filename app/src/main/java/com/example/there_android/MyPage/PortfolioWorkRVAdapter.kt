package com.example.there_android.MyPage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.there_android.databinding.ItemWorkBinding

class PortfolioWorkRVAdapter(val context: Context, val result : List<PfolPostsResult>) : RecyclerView.Adapter<PortfolioWorkRVAdapter.ViewHolder>() {
    interface WorkClickListener {
        fun onItemClick(postIdx : Int)
    }

    private lateinit var workClickListener: WorkClickListener
    fun setWorkClickListener(itemClickListener: WorkClickListener) {
        workClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemWorkBinding = ItemWorkBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(result[position].imgUrl == "" || result[position].imgUrl== null){
        } else {
            //Log.d("image", result[position].postIdx.toString() + " : " + result [position].imgUrl )
            Glide.with(context).load(result[position].imgUrl).into(holder.workImg)
        }
        holder.binding.itemworksIv.setOnClickListener { workClickListener.onItemClick((result[position].postIdx)) }
    }

    override fun getItemCount(): Int = result.size

    //viewholder
    inner class ViewHolder(val binding: ItemWorkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val workImg : ImageView = binding.itemworksIv
    }

}