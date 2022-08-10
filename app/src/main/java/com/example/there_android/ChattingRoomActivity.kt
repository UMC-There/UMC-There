package com.example.there_android

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.there_android.databinding.ActivityChattingroomBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChattingRoomActivity:AppCompatActivity() , GetMessageView{
    internal lateinit var preferences: SharedPreferences
    lateinit var binding: ActivityChattingroomBinding

    var arrayList : ArrayList<ChattingModel> = arrayListOf()
    val mAdapter = ChattingRVAdapter(this, arrayList)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = ActivityChattingroomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences = getSharedPreferences("USERSIGN", Context.MODE_PRIVATE)

        setAdapter()

        binding.chattingroomSendCl.setOnClickListener {
            sendMessage()
        }
    }

    private fun getContent(): GetMessageRequest{
        val receiverIdx : Int = 1
        val roomIdx : Int = 1
        val senderIdx : Int = 1
        return GetMessageRequest(receiverIdx, roomIdx, senderIdx)
    }

    private fun loadMessage(){
        val getMessageService = GetMessageService()
        getMessageService.setMessageView(this)
        getMessageService.getMessage(getContent())
    }

    override fun onGetMessageSuccess(response: GetMessageResponse) {
        //채팅 내역 불러오기
    }

    override fun onGetMessageFailure() {
        Log.d("GETMESSGAE/FAILURE", "서버와 연결되지 않았습니다. ")
    }

    private fun setAdapter(){
        binding.chattingroomChattingRv.adapter = mAdapter
        binding.chattingroomChattingRv.layoutManager = LinearLayoutManager(this)
        binding.chattingroomChattingRv.setHasFixedSize(true)
    }

    fun sendMessage(){
        val now = System.currentTimeMillis()
        val date = Date(now)
        val sdf = SimpleDateFormat("yymmdd")
        val getTime = sdf.format(date)

        val item = ChattingModel(preferences.getString("name", "")!!, binding.chattingroomChatEt.text.toString(), "example", getTime)
        mAdapter.addItem(item)
        mAdapter.notifyDataSetChanged()

        //채팅 입력창 초기화
        binding.chattingroomChatEt.setText("")
    }
}