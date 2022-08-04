package com.example.there_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.there_android.databinding.ActivityAddpostBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPostActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddpostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddpostBinding.inflate(layoutInflater)
        setContentView(binding.root)
//닫기 버튼
        binding.addpostCloseIv.setOnClickListener{
            finish()
        }
//완료 버튼
        binding.addpostDoneTv.setOnClickListener{
            addPosting()
        }
    }

    private fun getWork(): Work {
        val title: String = binding.addpostPostnameEt.text.toString()
        val content: String = binding.addpostPostintroEt.text.toString()
        val hashtag: String = binding.addpostPosttagEt.text.toString()

        return Work(title, content, hashtag)
    }

    private fun addPosting() {
        val addPostService = getRetrofit().create(AddPostInterface::class.java)
        addPostService.addpost(getWork()).enqueue(object : Callback<AddPostResponse>{
            override fun onResponse(call: Call<AddPostResponse>, response: Response<AddPostResponse>) {
                Log.d("SIGNUP-ACT/RESPONSE", response.toString())
                val resp: AddPostResponse = response.body()!!
                if (resp.code == 200){
                    finish()
                }
            }

            override fun onFailure(call: Call<AddPostResponse>, t: Throwable) {
                Log.d("SIGNUP-ACT/ERROR", t.message.toString())
            }

        })
    }
}