package com.example.there_android.MyPage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.there_android.MainActivity
import com.example.there_android.R
import com.example.there_android.databinding.FragmentMypageFollowerBinding
import com.example.there_android.utils.GlobalApplication
import com.google.gson.Gson

class FollowerFragment : Fragment(), FollowView {
    private lateinit var binding: FragmentMypageFollowerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageFollowerBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onStart() {
        super.onStart()
        getFollower()
    }

    private fun getFollower() {
        val myPageService = MyPageService()
        myPageService.setFollowView(this)
        myPageService.getFollowerList(GlobalApplication.spf.spfIdx!!)
    }

    private fun initRecyclerView(result: List<FollowerList>){
        val followerRVAdapter = FollowerRVAdapter(requireContext(), result)
        binding.mypageFollowRecyclerview.adapter = followerRVAdapter
        binding.mypageFollowRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        //외부 객체 리스너 전달
        followerRVAdapter.setFollowerClickListener(object :
            FollowerRVAdapter.FollowerClickListener {
            override fun onItemClick(follower: FollowerList) {
                toFollowerFragment(follower)
            }

            override fun onRemoveFollower(position: Int) {

            }
        })
    }
    private fun toFollowerFragment(follower: FollowerList) {

    }


    override fun onFollowSuccess(result: List<FollowerList>) {
        initRecyclerView(result)
        Log.d("Follower-SUCCESS", result.toString())
    }

    override fun onFollowFailure(code: Int, message: String) {
        Log.d("Follower-RESPONSE", code.toString() + message)
    }
}