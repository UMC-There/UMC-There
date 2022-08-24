package com.example.there_android

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.there_android.databinding.FragmentMypageBinding
import com.google.android.material.tabs.TabLayoutMediator

class MyPageFragment : Fragment(), MyPageView {
    private lateinit var binding: FragmentMypageBinding
    private val tabIconArray = arrayListOf(R.drawable.btn_mypage_grid, R.drawable.btn_mypage_list)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)

        binding.mypageProfileimgIv.background = ShapeDrawable(OvalShape())
        binding.mypageProfileimgIv.clipToOutline = true

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

        val current = arguments?.getInt("currentview")
        Log.d("current", current.toString())
        if(current == 2) {
            binding.mypageViewpager.setCurrentItem(current, false)
        }
//sticky 스크롤뷰
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

    override fun onStart() {
        super.onStart()
        getData()
    }

//    private fun getJwt() {
//        val jwt = GlobalApplication.spf.spfJwt
//        val userIdx = GlobalApplication.spf.spfIdx
//        getData(userIdx)
//    }

    private fun getData() {
        val myPageService = MyPageService()
        myPageService.setMyPageView(this)
        myPageService.getMyData(GlobalApplication.spf.spfIdx!!)
    }
    private fun initData(result: MyPageResult){
        binding.mypageNicknameTv.text = result.user.nickName
        binding.mypageFollowerTv.text = result.user.followerIdx.toString()
        binding.mypageFolloweeTv.text = result.user.followeeIdx.toString()
        binding.mypageIntroTv.text = result.user.info
        if(result.user.profileImgUrl == "" || result.user.profileImgUrl== null){

        } else {
            Glide.with(this).load(result.user.profileImgUrl).into(binding.mypageProfileimgIv)
        }
    }

    override fun onMyPageSuccess(result: MyPageResult) {
        initData(result)
        Log.d("MyData-SUCCESS", result.userPosts.toString())
    }

    override fun onMyPageFailure(code: Int, message: String) {
        Log.d("MyData-RESPONSE", code.toString() + message)
    }
}