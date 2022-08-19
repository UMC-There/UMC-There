package com.example.there_android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
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
        val myPageWorkRVAdapter = MyPageWorkRVAdapter(myPageData)
        binding.mypageWorksRecyclerview.adapter = myPageWorkRVAdapter
        binding.mypageWorksRecyclerview.layoutManager = GridLayoutManager(context, 3)
        //외부 객체 리스너 전달
        myPageWorkRVAdapter.setWorkClickListener(object : MyPageWorkRVAdapter.WorkClickListener{
            override fun onItemClick(work: MyPageData) {
                toWorkFragment(work)
            }
        })

        return binding.root
    }
    private fun toWorkFragment(work: MyPageData) {
        val intent = Intent(context,PostActivity::class.java)
        startActivity(intent)
    }
}