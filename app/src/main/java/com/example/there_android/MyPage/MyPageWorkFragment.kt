package com.example.there_android.MyPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.there_android.Post.PostActivity
import com.example.there_android.databinding.FragmentMypageWorkBinding
import com.example.there_android.utils.GlobalApplication


class MyPageWorkFragment : Fragment(), MyPageView {
    private lateinit var binding: FragmentMypageWorkBinding
    private var myPageData = ArrayList<MyPageData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageWorkBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onStart() {
        super.onStart()
        getUserPost()
    }

    private fun getUserPost(){
        val myPageService = MyPageService()
        myPageService.setMyPageView(this)
        myPageService.getMyData(GlobalApplication.spf.spfIdx!!)
    }

    private fun initRecyclerView(result: MyPageResult){
        //리사이클러뷰 어댑터
        val myPageWorkRVAdapter = MyPageWorkRVAdapter(requireContext(), result)
        binding.mypageWorksRecyclerview.adapter = myPageWorkRVAdapter
        binding.mypageWorksRecyclerview.layoutManager = GridLayoutManager(context, 3)
        //외부 객체 리스너 전달
        myPageWorkRVAdapter.setWorkClickListener(object : MyPageWorkRVAdapter.WorkClickListener {
            override fun onItemClick(postIdx: Int) {
                toWorkFragment(postIdx)
            }
        })
    }
    private fun toWorkFragment(postIdx: Int) {
        val intent = Intent(context, PostActivity::class.java)
        intent.putExtra("postIdx", postIdx)
        Log.d("MyPostidx", postIdx.toString())
        startActivity(intent)
    }

    override fun onMyPageSuccess(result: MyPageResult) {
        initRecyclerView(result)
        Log.d("MyPost-SUCCESS", result.userPosts.toString())
    }

    override fun onMyPageFailure(code: Int, message: String) {
        Log.d("MyPost-RESPONSE", code.toString() + message)
    }
}