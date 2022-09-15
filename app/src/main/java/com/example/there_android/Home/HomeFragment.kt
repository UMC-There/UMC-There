package com.example.there_android.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.there_android.MainActivity
import com.example.there_android.Post.AddPostActivity
import com.example.there_android.R
import com.example.there_android.databinding.FragmentHomeBinding

class HomeFragment: Fragment(), HomeView {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //loadHome()
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.homeAlarmIv.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm, AlarmFragment()).commitAllowingStateLoss()
        }

        binding.homeAddCl.setOnClickListener {
            val intent = Intent(this.context, AddPostActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }

    fun loadHome(){
        val homeService = HomeService()
        homeService.setHomeView(this)
        homeService.getHome()
    }

    override fun onHomeViewSuccess(response: HomeResponse) {
        TODO("Not yet implemented")
    }

    override fun onHomeViewFailure() {
        TODO("Not yet implemented")
    }
}