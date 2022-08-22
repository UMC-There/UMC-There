package com.example.there_android

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.there_android.databinding.ItemWorkBinding

class MyPageWorkRVAdapter(val context: Context, val result : MyPageResult) : RecyclerView.Adapter<MyPageWorkRVAdapter.ViewHolder>() {
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
    ): MyPageWorkRVAdapter.ViewHolder {
        val binding: ItemWorkBinding = ItemWorkBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageWorkRVAdapter.ViewHolder, position: Int) {
        if(result.userPosts[position].imgUrl == "" || result.userPosts[position].imgUrl== null){

        } else {
            Log.d("image", result.userPosts[position].postIdx.toString() + " : " + result .userPosts[position].imgUrl )
            //Glide 라이브러리로 url을 이미지로 업로드
            Glide.with(context).load(result.userPosts[position].imgUrl).into(holder.workImg)
        }
        holder.binding.itemworksIv.setOnClickListener { workClickListener.onItemClick((result.userPosts[position].postIdx)) }
//        holder.itemView.setOnClickListener {
//            Log.d("Click the Item", "open")
//            val intent = Intent(holder.itemView?.context, PostActivity::class.java)
//            ContextCompat.startActivity(holder.itemView.context, intent, null)
//        }
    }

    override fun getItemCount(): Int = result.userPosts.size

    //viewholder
    inner class ViewHolder(val binding: ItemWorkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val workImg : ImageView = binding.itemworksIv
    }

}