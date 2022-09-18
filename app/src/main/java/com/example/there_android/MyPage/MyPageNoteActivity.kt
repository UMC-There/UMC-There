package com.example.there_android.MyPage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.there_android.databinding.ActivityMypageEditBinding
import com.example.there_android.databinding.ActivityMypageNoteBinding

class MyPageNoteActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMypageNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}