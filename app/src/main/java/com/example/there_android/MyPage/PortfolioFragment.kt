package com.example.there_android.MyPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.there_android.MainActivity
import com.example.there_android.Post.PostActivity
import com.example.there_android.R
import com.example.there_android.databinding.FragmentPortfolioBinding
import com.google.gson.Gson

class PortfolioFragment : Fragment(), PfolPostView {
    private lateinit var binding: FragmentPortfolioBinding
    //private var myPageData = ArrayList<MyPageData>()
    val gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPortfolioBinding.inflate(inflater, container, false)

        binding.portfolioBackIv.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MyPageFragment().apply {
                    arguments = Bundle().apply {
                        putInt("currentview", 2)
                    }
                })
                .commitAllowingStateLoss()

        }

        return binding.root
    }
    override fun onStart() {
        super.onStart()
        val portfolioJson = arguments?.getString("portfolio")
        val portfolio = gson.fromJson(portfolioJson, PortfolioResult::class.java)
        Log.d("portfoliojson", portfolio.title.toString())
        binding.portfolioTitelTv.text = portfolio.title
        binding.portfolioWorksTv.text = portfolio.post_count.toString()
        getPfolPost(portfolio.portfolioIdx)
    }
    private fun getPfolPost(portfolioIdx: Int) {
        val portfolioService = PortfolioService()
        portfolioService.setPfolPostView(this)
        portfolioService.getPfolPost(portfolioIdx)
    }

    private fun initRecyclerView(result: List<PfolPostsResult>){
        //리사이클러뷰 어댑터
        val portfolioWorkRVAdapter = PortfolioWorkRVAdapter(requireContext(), result)
        binding.portfolioRecyclerview.adapter = portfolioWorkRVAdapter
        binding.portfolioRecyclerview.layoutManager = GridLayoutManager(context, 3)
        //외부 객체 리스너 전달
        portfolioWorkRVAdapter.setWorkClickListener(object :
            PortfolioWorkRVAdapter.WorkClickListener {
            override fun onItemClick(postIdx: Int) {
                toWorkFragment(postIdx)
            }
        })
    }
    private fun toWorkFragment(postIdx: Int) {
        val intent = Intent(context, PostActivity::class.java)
        intent.putExtra("postIdx", postIdx)
        startActivity(intent)
    }

    override fun onPfolPostSuccess(result: List<PfolPostsResult>) {
        initRecyclerView(result)
        Log.d("MyPfolPost-SUCCESS", result.toString())
    }

    override fun onPfolPostFailure(code: Int, message: String) {
        Log.d("MyPfolPost-RESPONSE", code.toString() + message)
    }

}