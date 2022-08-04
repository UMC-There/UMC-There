package com.example.there_android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.there_android.databinding.ActivityAddpostBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class AddPostActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddpostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddpostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addpostDoneTv.setOnClickListener{
            addPosting()
        }
    }

    private fun getWork(): Work {
        val title: String = binding.addpostPostnameEt.text.toString()
        val content: String = binding.addpostPostintroEt.text.toString()
        val tag: String = binding.addpostPosttagEt.text.toString()

        return Work(title, content, tag)
    }

    private fun addPosting() {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.addpost(getWork()).enqueue(object : Callback<AuthResponse>{
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("SIGNUP-ACT/RESPONSE", response.toString())
                val resp: AuthResponse = response.body()!!
                if (resp.code == 0){
                    finish()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("SIGNUP-ACT/ERROR", t.message.toString())
            }

        })
    }
}