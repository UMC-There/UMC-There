package com.example.there_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.there_android.databinding.ActivityPostBinding

class PostActivity :AppCompatActivity() , GetPostView{

    lateinit var binding: ActivityPostBinding

    lateinit var getPostRequest : GetPostRequest

    lateinit var getHistoryListRequest : GetHistoryListRequest

    private val postIdx : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.post_history_fl, HistoryFragment())
        transaction.commit()

        //addHistory 화면 전환
        binding.postAddHistoryIv.setOnClickListener {
            val intent = Intent(this, AddHistoryActivity::class.java)
            startActivity(intent)
        }

        binding.postBackIv.setOnClickListener{
            finish()
        }
        getContent()
        loadData()
        openHistory()
    }

    private fun openHistory(){
        //postIdx 값 넘겨주기
//        var bundle = Bundle()
//        bundle.putInt("postIdx", postIdx)

        binding.postHistoryOpenIv.setOnClickListener{
            binding.postHistoryOpenIv.visibility = View.INVISIBLE
            binding.postHistoryCloseIv.visibility = View.VISIBLE
            //binding.postHistoryFragment.visibility = View.VISIBLE
            binding.postHistoryFl.visibility = View.VISIBLE
            binding.postAddHistoryIv.visibility = View.VISIBLE
        }

        binding.postHistoryCloseIv.setOnClickListener{
            binding.postHistoryOpenIv.visibility = View.VISIBLE
            binding.postHistoryCloseIv.visibility = View.INVISIBLE
            //binding.postHistoryFragment.visibility = View.INVISIBLE
            binding.postHistoryFl.visibility = View.INVISIBLE
            binding.postAddHistoryIv.visibility = View.INVISIBLE
        }
    }

    private fun getContent(){
        getHistoryListRequest = GetHistoryListRequest(postIdx)
        getPostRequest = GetPostRequest(postIdx)
    }

    private fun loadData(){
        val getPostService = GetPostService()
        getPostService.setGetPostView(this)
        getPostService.getPost(getPostRequest)
    }

    override fun onGetPostSuccess(result: GetPostResponse.Result) {
        binding.postNicknameTv.text = result.nickname
        binding.postContextTv.text = result.content
        binding.postLikeNumTv.text = result.likeCount.toString()
        Glide.with(binding.postProfileIv.context).load(result.profileImgUrl).into(binding.postProfileIv)
        Glide.with(binding.postImageIv.context).load(result.imgUrl).into(binding.postImageIv)
    }

    override fun onGetPostFailure() {
        Log.d("GETPOST/FAILURE", "데이터를 불러올 수 없습니다.")
    }
}