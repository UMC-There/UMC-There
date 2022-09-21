package com.example.there_android.Comment

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.there_android.R
import com.example.there_android.databinding.ActivityCommentBinding

class CommentActivity : AppCompatActivity(), GetCommentView{

    lateinit var binding : ActivityCommentBinding

    private lateinit var request: GetCommentRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        val binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postIdx = intent.getIntExtra("postIdx", 0)
        request = GetCommentRequest(postIdx)

        loadData()

        binding.commentBackIv.setOnClickListener {
            //back 버튼 누르면 activity 종료
            finish()
        }

    }

    private fun setAdapter(commentList : List<GetCommentResponse.Result>, request: GetCommentRequest){
        val mAdapter = CommentRVAdapter(this, commentList, request)
        binding.commentCommentRv.adapter = mAdapter
        binding.commentCommentRv.layoutManager = LinearLayoutManager(this)
    }

    private fun loadData(){
        val getCommentService = GetCommentService()
        getCommentService.setGetCommentView(this)
        getCommentService.getComment(request)
    }

    override fun onGetCommentSuccess(result: List<GetCommentResponse.Result>) {
        Log.d("SUCCESS/SERVER", "서버 연결에 성공하였습니다.")
        setAdapter(result, request)
    }

    override fun onGetCommentFailure() {
        Log.d("FAILURE/SERVER", "서버 연결에 실패하였습니다")
    }
}