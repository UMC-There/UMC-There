package com.example.there_android.Comment

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.there_android.R
import com.example.there_android.databinding.ActivityCommentBinding

class CommentActivity : AppCompatActivity(), GetCommentView{

    lateinit var binding : ActivityCommentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        val binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onGetCommentSuccess() {
        TODO("Not yet implemented")
    }

    override fun onGetCommentFailure() {
        TODO("Not yet implemented")
    }
}