package com.example.there_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.there_android.databinding.FragmentMypageWorksBinding


class MyPageWorksFragment : Fragment(){
    private lateinit var binding: FragmentMypageWorksBinding
    private var myPageData = ArrayList<MyPageData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageWorksBinding.inflate(inflater, container, false)

        //데이터 리스트
        myPageData.apply {
            add(MyPageData("there", "그곳", "소개글 입력하는 칸", 8, 8, "UMC_there"))
            add(MyPageData("there2", "그곳2", "소개글 입력하는 칸", 8, 8, "UMC_there"))
            add(MyPageData("there3", "그곳", "소개글 입력하는 칸", 8, 8, "UMC_there"))
            add(MyPageData("there4", "그곳2", "소개글 입력하는 칸", 8, 8, "UMC_there"))
        }
        //리사이클러뷰 어댑터
        val myWorkRVAdapter = MyWorkRVAdapter(myPageData)
        binding.mypageWorksRecyclerview.adapter = myWorkRVAdapter
        binding.mypageWorksRecyclerview.layoutManager = GridLayoutManager(context, 3)
        //외부 객체 리스너 전달
        myWorkRVAdapter.setPortfolioClickListener(object : MyWorkRVAdapter.WorkClickListener{
            override fun onItemClick(portfolio: MyPageData) {
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm , MyPortfolioFragment())
                    .commitAllowingStateLoss()
            }
        })

        return binding.root
    }
}