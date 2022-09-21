package com.example.there_android.Comment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.there_android.R
import com.example.there_android.databinding.ItemCommentBinding

class CommentRVAdapter (val context : Context, val commentList : List<GetCommentResponse.Result>, val request: GetCommentRequest) : RecyclerView.Adapter<CommentRVAdapter.ViewHolder>(){

    inner class ViewHolder(private val binding : ItemCommentBinding) : RecyclerView.ViewHolder(binding.root){
        val name = binding.itemCommentNameTv
        val profileImg = binding.itemCommentProfileIv
        val content = binding.itemCommnetContentTv
        val time = binding.itemCommentTimeTv

        fun bind(commentData : GetCommentResponse.Result){
            binding.itemCommentNameTv.text = commentData.nickName
            binding.itemCommnetContentTv.text = commentData.content
            binding.itemCommentTimeTv.text = commentData.created_At + "시간"
            if(commentData.profileImgUrl == ""){
                Glide.with(binding.itemCommentProfileIv.context).load(R.drawable.img_basic_profile).into(binding.itemCommentProfileIv)
            }
            else{
                Glide.with(binding.itemCommentProfileIv.context).load(commentData.profileImgUrl).into(binding.itemCommentProfileIv)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentList[position])
    }
}