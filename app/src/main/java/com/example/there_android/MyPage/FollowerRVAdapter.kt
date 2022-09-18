package com.example.there_android.MyPage

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.there_android.databinding.ItemFollowBinding
import com.example.there_android.databinding.ItemWorkBinding

class FollowerRVAdapter(val context: Context, val result : List<FollowerList>) : RecyclerView.Adapter<FollowerRVAdapter.ViewHolder>() {
    interface FollowerClickListener {
        fun onItemClick(follower: FollowerList)
        fun onRemoveFollower(position: Int)
    }
    fun removeItem(position: Int){
        //albumList.removeAt(position)
        notifyDataSetChanged()
    }
    private lateinit var followerClickListener: FollowerClickListener
    fun setFollowerClickListener(itemClickListener: FollowerClickListener) {
        followerClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemFollowBinding = ItemFollowBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(result[position])
        holder.binding.itemfollow.setOnClickListener{ followerClickListener.onItemClick((result[position]))}
    }

    override fun getItemCount(): Int = result.size

    //viewholder
    inner class ViewHolder(val binding: ItemFollowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: FollowerList){
            binding.itemfollowAccountnameTv.text = follower.nickName
            if(follower.profileImgUrl == "" || follower.profileImgUrl== null){

            } else {
                Log.d("image", follower.nickName + " : " + follower.profileImgUrl )
                Glide.with(context).load(follower.profileImgUrl).into(binding.itemfollowFollowerImgIv)
            }
        }
    }
}