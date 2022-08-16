package com.example.there_android

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.there_android.databinding.ActivityChattingroomBinding
import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient


class ChattingRoomActivity:AppCompatActivity(), GetMessageView{

    lateinit var binding: ActivityChattingroomBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = ActivityChattingroomBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onGetMessageSuccess(result: List<GetMessageResponse.Result>) {

    }

    override fun onGetMessageFailure() {
        Log.d("FAILURE/SERVER", "서버 연결에 실패하였습니다.")
    }

    lateinit var stompConnection : Disposable
    lateinit var topic : Disposable

    val url = "ws://3.39.57.176:8080/connect/websocket"
    val intervalMillis = 1000L
    val client = OkHttpClient()
    val stomp = StompClient(client, intervalMillis, url)

    fun connect(){
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

        stomp.join("/topic/rm").subscribe {  }

        stomp.send("/app/1004", "message").subscribe { }
    }

    override fun StompClient(
        okHttpClient: OkHttpClient,
        reconnectAfter: Long,
        url: String
    ): StompClient {
        TODO("Not yet implemented")
    }
}