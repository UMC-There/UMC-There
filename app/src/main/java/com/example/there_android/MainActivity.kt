package com.example.there_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import com.example.there_android.Chat.ChatFragment
import com.example.there_android.Home.HomeFragment
import com.example.there_android.MyPage.MyPageFragment
import com.example.there_android.Search.SearchFragment
import com.example.there_android.databinding.ActivityMainBinding
import com.example.there_android.databinding.FragmentMypageBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var binding2: FragmentMypageBinding

    private var backKeyPressTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        val toast = Toast.makeText(this, "뒤로 가기를 한 번 더 누르면 종료됩니다", Toast.LENGTH_SHORT)
        if (System.currentTimeMillis() > backKeyPressTime + 2500) {
            backKeyPressTime = System.currentTimeMillis()
            toast.show()
            return
        }
        if (System.currentTimeMillis() <= backKeyPressTime + 2500) {
            toast.cancel()
            finishAffinity()
        }
    }

    private fun initBottomNavigation(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()
        binding.mainBnv.itemIconTintList = null

        binding.mainBnv.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.chatFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ChatFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.mypageFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MyPageFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.etcFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, EtcFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}