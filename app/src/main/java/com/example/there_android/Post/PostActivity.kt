package com.example.there_android.Post

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.there_android.Comment.CommentActivity
import com.example.there_android.History.AddHistoryActivity
import com.example.there_android.History.GetHistoryListRequest
import com.example.there_android.History.HistoryFragment
import com.example.there_android.R
import com.example.there_android.databinding.ActivityPostBinding

class PostActivity :AppCompatActivity() , GetPostView {

    lateinit var binding: ActivityPostBinding

    lateinit var getPostRequest : GetPostRequest

    lateinit var getHistoryListRequest : GetHistoryListRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postIdx : Int = intent.getIntExtra("postIdx", 0)
        Log.d("intent", postIdx.toString())

        //addHistory 화면 전환
        binding.postAddHistoryIv.setOnClickListener {
            val intent = Intent(this, AddHistoryActivity::class.java)
            startActivity(intent)
        }

        binding.postCommentIv.setOnClickListener {
            val intent = Intent(this, CommentActivity::class.java)
            intent.putExtra("postIdx", postIdx)
            startActivity(intent)
        }

        binding.postBackIv.setOnClickListener{
            finish()
        }

        getContent(postIdx)
        loadData()
        openHistory(postIdx)
    }

    private fun openHistory(postIdx: Int){

        binding.postHistoryOpenIv.setOnClickListener{

            //postIdx 값 넘겨주기
            Log.d("CHECK", postIdx.toString())
            var bundle = Bundle()
            val historyFragment = HistoryFragment()
            bundle.putInt("postIdx", postIdx)
            historyFragment.arguments = bundle

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.post_history_fl, HistoryFragment())
            transaction.commit()

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

    private fun getContent(postIdx: Int){
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