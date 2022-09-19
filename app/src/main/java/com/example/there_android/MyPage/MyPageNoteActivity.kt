package com.example.there_android.MyPage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.there_android.Auth.UserAuthRequest
import com.example.there_android.Auth.UserService
import com.example.there_android.databinding.ActivityMypageNoteBinding
import com.example.there_android.utils.GlobalApplication

class MyPageNoteActivity : AppCompatActivity(), NoteView{
    private lateinit var binding: ActivityMypageNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mypagenoteBackIv.setOnClickListener{
            finish()
        }
        binding.mypagenoteEditTv.setOnClickListener{
            editNote()
        }
    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    private fun getData() {
        val myPageService = MyPageService()
        myPageService.setNoteView(this)
        myPageService.getNote(GlobalApplication.spf.spfIdx!!)
    }
    private fun initData(result: NoteResult){
        if(result.selfIntroduction == "" || result.selfIntroduction== null){

        } else {
            binding.mypagenoteIntroEt.setText(result.selfIntroduction)
        }
        if(result.workIntroduction == "" || result.workIntroduction== null){

        } else {
            binding.mypagenoteWorkintroEt.setText(result.workIntroduction)
        }
        if(result.contact == "" || result.contact== null){

        } else {
            binding.mypagenoteContactEt.setText(result.contact)
        }
    }

    private fun postNote(): NoteRequest {
        val selfIntro: String = binding.mypagenoteIntroEt.text.toString()
        val workIntro: String = binding.mypagenoteWorkintroEt.text.toString()
        val contact: String = binding.mypagenoteContactEt.text.toString()

        return NoteRequest(selfIntro, workIntro, contact)
    }

    private fun editNote() {
        val myPageService = MyPageService()
        myPageService.setNoteView(this)
        myPageService.postNote(postNote(), GlobalApplication.spf.spfIdx!!)
        Log.d("NoteEdit", postNote().workIntroduction+ postNote().contact)
    }

    override fun onNoteSuccess(result: NoteResult) {
        initData(result)
        Log.d("Note-SUCCESS", result.toString())
    }

    override fun onNoteFailure(code: Int, message: String) {
        Log.d("Note-FAILURE", code.toString() + message)
    }

    override fun onPostNoteSuccess(result: String) {
        finish()
    }

    override fun onPostNoteFailure(code: Int, message: String) {
        Log.d("NoteEdit-FAILURE", code.toString() + message)
    }
}