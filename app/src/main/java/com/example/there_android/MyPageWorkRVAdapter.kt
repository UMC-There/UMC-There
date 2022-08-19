package com.example.there_android

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.there_android.databinding.ItemWorkBinding

class MyPageWorkRVAdapter(private val workList: ArrayList<MyPageData>) : RecyclerView.Adapter<MyPageWorkRVAdapter.ViewHolder>() {
    interface WorkClickListener {
        fun onItemClick(work: MyPageData) // 포트폴리오를 클릭했을 때
    }

    private lateinit var workClickListener: WorkClickListener
    fun setWorkClickListener(itemClickListener: WorkClickListener) {
        workClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): MyPageWorkRVAdapter.ViewHolder {
        val binding: ItemWorkBinding =
            ItemWorkBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageWorkRVAdapter.ViewHolder, position: Int) {
//        if(result.songs[position].coverImgUrl == "" || result.songs[position].coverImgUrl == null){
//
//        } else {
//            Log.d("image",result.songs[position].coverImgUrl )
////Glide 라이브러리로 url을 이미지로 업로드
//            Glide.with(context).load(result.songs[position].coverImgUrl).into(holder.coverImg)
//        }
        holder.binding.itemworksIv.setOnClickListener { workClickListener.onItemClick((workList[position])) }
//        holder.itemView.setOnClickListener {
//            Log.d("Click the Item", "open")
//            val intent = Intent(holder.itemView?.context, PostActivity::class.java)
//            ContextCompat.startActivity(holder.itemView.context, intent, null)
//        }
    }

    override fun getItemCount(): Int = workList.size

    //viewholder
    inner class ViewHolder(val binding: ItemWorkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val workImg : ImageView = binding.itemworksIv
    }
}