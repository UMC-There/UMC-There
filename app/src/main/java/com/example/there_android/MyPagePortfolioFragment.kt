package com.example.there_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.there_android.databinding.FragmentMypagePortfolioBinding
import com.google.gson.Gson

class MyPagePortfolioFragment : Fragment() {
    private lateinit var binding: FragmentMypagePortfolioBinding
    private var myPageData = ArrayList<MyPageData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypagePortfolioBinding.inflate(inflater, container, false)

        //데이터 리스트
        myPageData.apply {
            add(MyPageData("there", "그곳", "소개글 입력하는 칸", 8, 8, "UMC_there"))
            add(MyPageData("there2", "그곳2", "소개글 입력하는 칸", 8, 8, "UMC_there"))
            add(MyPageData("there3", "그곳", "소개글 입력하는 칸", 8, 8, "UMC_there"))
            add(MyPageData("there4", "그곳2", "소개글 입력하는 칸", 8, 8, "UMC_there"))
        }

        //리사이클러뷰 어댑터
        val myPageRVAdapter = MyPageRVAdapter(myPageData)
        binding.mypagePortfolioRecyclerview.adapter = myPageRVAdapter
        binding.mypagePortfolioRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        //외부 객체 리스너 전달
        myPageRVAdapter.setPortfolioClickListener(object : MyPageRVAdapter.PortfolioClickListener{
            override fun onItemClick(portfolio: MyPageData) {
                toPortfolioFragment(portfolio)
            }
        })

        return binding.root
    }
    private fun toPortfolioFragment(portfolio: MyPageData) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, MyPortfolioFragment()).apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val portfolioJson = gson.toJson(portfolio)
                    putString("portfolio", portfolioJson)
                }
            }.commitAllowingStateLoss()
    }

}
