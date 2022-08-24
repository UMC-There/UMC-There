package com.example.there_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.there_android.databinding.ActivityChattingroomBinding
import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient


class ChattingRoomActivity:AppCompatActivity(), GetMessageView{

    lateinit var binding: ActivityChattingroomBinding

    private lateinit var request : GetMessageRequest

    lateinit var text : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChattingroomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val roomIdx = intent.getIntExtra("roomIdx", 0)
        val senderIdx = intent.getIntExtra("senderIdx", 0)
        val receiverIdx = intent.getIntExtra("receiverIdx", 0)

        Log.d("CHECK/INTENT", "$receiverIdx, $roomIdx, $senderIdx")
        request = GetMessageRequest(receiverIdx, roomIdx, senderIdx)

        loadData()

        binding.chattingroomSendCl.setOnClickListener {
            text = binding.chattingroomChatEt.text.toString()
            //connect(roomIdx)
            binding.chattingroomChatEt.setText("")
        }

        binding.chattingroomBackIv.setOnClickListener {
            finish()
        }
    }

    private fun loadData(){
        val getMessageService = GetMessageService()
        getMessageService.setMessageView(this)
        getMessageService.getMessage(request)
    }

    private fun setAdapter(messageList : List<GetMessageResponse.Result>, request: GetMessageRequest){
        val mAdapter = ChattingRoomRVAdapter(this, messageList, request)
        binding.chattingroomChattingRv.adapter = mAdapter
        binding.chattingroomChattingRv.layoutManager = LinearLayoutManager(this)
        //binding.chattingroomChattingRv.setHasFixedSize(true)
    }

    override fun onGetMessageSuccess(result: List<GetMessageResponse.Result>) {
        Log.d("SUCCESS/SERVER", "서버 연결에 성공했습니다.")
        val sorted = result.sortedBy { it.created_At }
        setAdapter(sorted, request)
    }

    override fun onGetMessageFailure() {
        Log.d("FAILURE/SERVER", "서버 연결에 실패하였습니다.")
    }


    //메세지 보내기 STOMP 연결
    lateinit var stompConnection : Disposable
    lateinit var topic : Disposable

    val intervalMillis = 1000L
    val client = OkHttpClient()
    val stomp = StompClient(client, intervalMillis)

    fun connect(roomIdx : Int){
        stomp.url = "wss://3.39.57.176:8080/connect/websocket"
        stompConnection = stomp.connect().subscribe{
            when(it.type){
                Event.Type.OPENED->{

                }
                Event.Type.CLOSED->{

                }
                Event.Type.ERROR->{

                }
            }
        }

        stomp.join("/topic/$roomIdx")
            .doOnError{ error -> }
            .subscribe { message -> }

        stomp.send("/app/$roomIdx", text).subscribe {
            if (it){}
        }
    }
}
