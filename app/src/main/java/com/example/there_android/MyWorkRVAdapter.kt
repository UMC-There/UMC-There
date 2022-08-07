package com.example.there_android

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.there_android.databinding.ItemWorkBinding

class MyWorkRVAdapter(private val portfolioList: ArrayList<MyPageData>) : RecyclerView.Adapter<MyWorkRVAdapter.ViewHolder>() {
    interface WorkClickListener {
        fun onItemClick(portfolio: MyPageData) // 포트폴리오를 클릭했을 때
    }

    private lateinit var workClickListener: WorkClickListener
    fun setPortfolioClickListener(itemClickListener: WorkClickListener) {
        workClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): MyWorkRVAdapter.ViewHolder {
        val binding: ItemWorkBinding =
            ItemWorkBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyWorkRVAdapter.ViewHolder, position: Int) {
        holder.bind(portfolioList[position])
//        holder.binding.itemworksIv.setOnClickListener { workClickListener.onItemClick((portfolioList[position])) }
        holder.itemView.setOnClickListener {
            Log.d("Click the Item", "open")
            val intent = Intent(holder.itemView?.context, PostActivity::class.java)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int = portfolioList.size

    //viewholder
    inner class ViewHolder(val binding: ItemWorkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(portfolio: MyPageData) {
            //binding.itemworksIv.text = portfolio.portfolioTitle
        }
    }
}