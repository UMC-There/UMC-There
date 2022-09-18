package com.example.there_android.MyPage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.there_android.databinding.ActivityJoinBinding
import com.example.there_android.databinding.ActivityMypageEditBinding

class MyPageEditActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMypageEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}