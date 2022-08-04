package com.example.there_android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.there_android.databinding.ItemPortfolioBinding

class MyPageRVAdapter(private val portfolioList: ArrayList<MyPageData>) : RecyclerView.Adapter<MyPageRVAdapter.ViewHolder>(){
    interface PortfolioClickListener{
        fun onItemClick(portfolio: MyPageData) // 포트폴리오를 클릭했을 때
    }
    private lateinit var portfolioClickListener : PortfolioClickListener
    fun setPortfolioClickListener(itemClickListener: PortfolioClickListener){
        portfolioClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyPageRVAdapter.ViewHolder {
        val binding: ItemPortfolioBinding = ItemPortfolioBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageRVAdapter.ViewHolder, position: Int) {
        holder.bind(portfolioList[position])
        holder.binding.itemportfolio.setOnClickListener{ portfolioClickListener.onItemClick((portfolioList[position]))}
    }

    override fun getItemCount(): Int = portfolioList.size

    //viewholder
    inner class ViewHolder(val binding: ItemPortfolioBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(portfolio: MyPageData){
            binding.itemportfolioTitelTv.text = portfolio.portfolioTitle
        }
    }
}