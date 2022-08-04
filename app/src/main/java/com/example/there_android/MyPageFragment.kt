package com.example.there_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.there_android.databinding.FragmentMypageBinding
import com.google.android.material.tabs.TabLayoutMediator

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMypageBinding
    private val tabIconArray = arrayListOf(R.drawable.btn_mypage_grid, R.drawable.btn_mypage_list)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)



        //페이지 이동
        binding.mypageAddIv.setOnClickListener {
            val intent = Intent(context, AddPostActivity::class.java)
            startActivity(intent)
            //(activity as MainActivity).startActivity(intent)
        }


        //viewpager 어댑터 연결, 탭레이아웃 연결
        val AdapterMypage = MyPageVPAdapter(this)
        binding.mypageViewpager.adapter= AdapterMypage
        TabLayoutMediator(binding.mypageTablayout, binding.mypageViewpager){
            tab, position -> tab.setIcon(tabIconArray[position])
        }.attach()
//        val viewPager = binding.mypageViewpager
//        val tabLayout = binding.mypageTablayout
//        viewPager.adapter = VPAdapterMyPage(this)
//        TabLayoutMediator(tabLayout, viewPager){
//            tab, position -> tab.setIcon(tabIconArray[position])
//        }.attach()


//        binding.mypageScrollview.run {
//            header = binding.mypageTablayout
//            stickListener = { _ ->
//                Log.d("LOGGER_TAG", "stickListener")
//            }
//            freeListener = { _ ->
//                Log.d("LOGGER_TAG", "freeListener")
//            }
//        }
            return binding.root
    }
}