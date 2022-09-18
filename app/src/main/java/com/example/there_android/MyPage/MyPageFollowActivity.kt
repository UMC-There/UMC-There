package com.example.there_android.MyPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.there_android.Auth.LoginActivity
import com.example.there_android.MainActivity
import com.example.there_android.R
import com.example.there_android.databinding.ActivityMypageEditBinding
import com.example.there_android.databinding.ActivityMypageFollowBinding
import com.google.android.material.tabs.TabLayoutMediator

class MyPageFollowActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMypageFollowBinding
    private val followtext = arrayListOf("팔로워", "팔로잉")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageFollowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mypagefollowBackIv.setOnClickListener{
            finish()
        }

        //viewpager 어댑터 연결, 탭레이아웃 연결
        val AdapterFollow = FollowVPAdapter(this)
        binding.mypagefollowViewpager.adapter= AdapterFollow
        TabLayoutMediator(binding.mypagefollowTablayout, binding.mypagefollowViewpager){
                tab, position -> tab.text = followtext[position]
        }.attach()

//        val current = arguments?.getInt("currentview")
//        Log.d("current", current.toString())
//        if(current == 2) {
//            binding.mypageViewpager.setCurrentItem(current, false)
//        }
    }

}