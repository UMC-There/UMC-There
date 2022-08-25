package com.example.there_android

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.there_android.databinding.ActivityAddpostBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPostActivity : AppCompatActivity(), AddPostView {
    private lateinit var binding : ActivityAddpostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddpostBinding.inflate(layoutInflater)
        setContentView(binding.root)
//닫기 버튼
        binding.addpostCloseIv.setOnClickListener{
            val builder =  AlertDialog.Builder(this)
                .setMessage("해당 페이지를 떠나시겠습니까? 작성 페이지를 떠나면 작성 중인 글이 저장되지 않습니다.")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, which ->
                        finish()
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, which ->
                    })
            builder.show()
        }
//완료 버튼
        binding.addpostDoneTv.setOnClickListener{
            addPosting()
        }
    }

    private fun getPost(): AddPost {
        val content: String = binding.addpostPostintroEt.text.toString()
        val hashtag: String = binding.addpostPosttagEt.text.toString()
        val postImg: String = binding.addpostImageIv.resources.toString()

        return AddPost(postImg, PostContent(content,hashtag))
    }

    private fun addPosting(){
        val addPostService = AddPostService()
        addPostService.setAddPostView(this)
        Log.d("AddPost", getPost().toString())
        addPostService.addPost(GlobalApplication.spf.spfIdx!!, getPost())
    }

    override fun onAddPostSuccess() {
        finish()
    }

    override fun onAddPostFailure(code: Int, message: String) {
        Log.d("AddPost-FAILURE", code.toString() + message)
    }


//    private fun addPosting() {
//        val addPostService = getRetrofit().create(AddPostInterface::class.java)
//        addPostService.addpost(getPost()).enqueue(object : Callback<AddPostResponse>{
//            override fun onResponse(call: Call<AddPostResponse>, response: Response<AddPostResponse>) {
//                Log.d("SIGNUP-ACT/RESPONSE", response.toString())
//                val resp: AddPostResponse = response.body()!!
//                if (resp.code == 200){
//                    finish()
//                }
//            }
//
//            override fun onFailure(call: Call<AddPostResponse>, t: Throwable) {
//                Log.d("SIGNUP-ACT/ERROR", t.message.toString())
//            }
//
//        })
//    }
}