package com.example.there_android

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.there_android.databinding.ItemPortfolioBinding
class MyPagePortfolioRVAdapter(val context: Context, val result :List<PortfolioResult>) : RecyclerView.Adapter<MyPagePortfolioRVAdapter.ViewHolder>(){
    interface PortfolioClickListener{
        fun onItemClick(portfolio: PortfolioResult) // 포트폴리오를 클릭했을 때
    }
    private lateinit var portfolioClickListener : PortfolioClickListener
    fun setPortfolioClickListener(itemClickListener: PortfolioClickListener){
        portfolioClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyPagePortfolioRVAdapter.ViewHolder {
        val binding: ItemPortfolioBinding = ItemPortfolioBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPagePortfolioRVAdapter.ViewHolder, position: Int) {
        holder.bind(result[position])

        holder.binding.itemportfolio.setOnClickListener{ portfolioClickListener.onItemClick((result[position]))}
    }

    override fun getItemCount(): Int = result.size

    //viewholder
    inner class ViewHolder(val binding: ItemPortfolioBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(portfolio: PortfolioResult){
            binding.itemportfolioTitelTv.text = portfolio.title
            binding.itemportfolioWorksTv.text = portfolio.post_count.toString()
            if(portfolio.pfolimgUrl == "" || portfolio.pfolimgUrl== null){

            } else {
                Log.d("image", portfolio.portfolioIdx.toString() + " : " + portfolio.pfolimgUrl )
                //Glide 라이브러리로 url을 이미지로 업로드
                Glide.with(context).load(portfolio.pfolimgUrl).into(binding.itemportfolioIv)
            }
        }
    }
}
//class MyPagePortfolioRVAdapter(val context: Context, val result : MyPageResult) : RecyclerView.Adapter<MyPagePortfolioRVAdapter.ViewHolder>(){
//    interface PortfolioClickListener{
//        fun onItemClick(portfolioIdx: Int) // 포트폴리오를 클릭했을 때
//    }
//    private lateinit var portfolioClickListener : PortfolioClickListener
//    fun setPortfolioClickListener(itemClickListener: PortfolioClickListener){
//        portfolioClickListener = itemClickListener
//    }
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyPagePortfolioRVAdapter.ViewHolder {
//        val binding: ItemPortfolioBinding = ItemPortfolioBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: MyPagePortfolioRVAdapter.ViewHolder, position: Int) {
//        holder.bind(result.userPosts[position])
//
//        holder.binding.itemportfolio.setOnClickListener{ portfolioClickListener.onItemClick((result.userPosts[position].postIdx))}
//    }
//
//    override fun getItemCount(): Int = result.userPosts.size
//
//    //viewholder
//    inner class ViewHolder(val binding: ItemPortfolioBinding) : RecyclerView.ViewHolder(binding.root){
//        fun bind(result: UserPosts){
//            binding.itemportfolioTitelTv.text = result.imgUrl.toString()
//            binding.itemportfolioWorksTv.text = result.postIdx.toString()
//        }
//    }
//}
