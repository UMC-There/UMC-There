package com.example.there_android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.there_android.databinding.FragmentPortfolioBinding

class PortfolioFragment : Fragment() {
    private lateinit var binding: FragmentPortfolioBinding
    private var myPageData = ArrayList<MyPageData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPortfolioBinding.inflate(inflater, container, false)
        //val portfolioJson = arguments?.getString("portfolio")
        //val portfolio = gson.fromJson(portfolioJson, MyPageData::class.java)
        //setViews(portfolio)

        //데이터 리스트
//        myPageData.apply {
//            add(MyPageData("there", "그곳", "소개글 입력하는 칸", 8, 8, "3"))
//            add(MyPageData("there2", "그곳2", "소개글 입력하는 칸", 9, 8, "4"))
//            add(MyPageData("there3", "그곳", "소개글 입력하는 칸", 10, 8, "UMC_there"))
//            add(MyPageData("there4", "그곳2", "소개글 입력하는 칸", 11, 8, "UMC_there"))
//        }

        return binding.root
    }

    private fun initRecyclerView(result: MyPageResult){
        //리사이클러뷰 어댑터
        val myPageWorkRVAdapter = MyPageWorkRVAdapter(requireContext(), result)
        binding.portfolioRecyclerview.adapter = myPageWorkRVAdapter
        binding.portfolioRecyclerview.layoutManager = GridLayoutManager(context, 3)
        //외부 객체 리스너 전달
        myPageWorkRVAdapter.setWorkClickListener(object : MyPageWorkRVAdapter.WorkClickListener{
            override fun onItemClick(postIdx : Int) {
                toWorkFragment(postIdx)
            }
        })
    }
    private fun toWorkFragment(postIdx : Int) {
        val intent = Intent(context,PostActivity::class.java)
        startActivity(intent)
    }
    //뷰 바인딩
    private fun setViews(portfolio: MyPageData) {
        //binding.myportfolioTitelTv.text = portfolio.portfolioTitle.toString()
    }
}