package com.example.there_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.there_android.databinding.FragmentMyprofileBinding
import com.google.android.material.tabs.TabLayoutMediator

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyprofileBinding
    private val tabIconArray = arrayOf(R.drawable.btn__mypage_works, R.drawable.btn__mypage_portfolio)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyprofileBinding.inflate(inflater, container, false)

        //추가 기록하기 버튼 이동
//        binding.myprofileAddBtn.setOnClickListener {
//            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main, AddRecordFragment()).commitAllowingStateLoss()
//        }

        //tablayout (viewpager2)
        val viewPager = binding.myaprofileViewpager
        val tabLayout = binding.myprofileTablayout
        viewPager.adapter = VPAdapterMyPage(this)
        TabLayoutMediator(tabLayout, viewPager){
            tab, position -> tab.setIcon(tabIconArray[position])
        }.attach()

        return binding.root
    }
}