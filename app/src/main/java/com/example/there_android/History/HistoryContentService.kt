package com.example.there_android.History

import android.util.Log
import com.example.there_android.utils.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryContentService {
    private lateinit var historyContentView : HistoryContentView

    private var historyContentRetrofit : HistoryContentInterface = getRetrofit().create(
        HistoryContentInterface::class.java)

    fun setHistoryContentView(historyContentView: HistoryContentView){
        this.historyContentView = historyContentView
    }

    fun getHistory(request : HistoryContentRequest){
        historyContentRetrofit.getHistory(request.historyIdx).enqueue(object : Callback<HistoryContentResponse>{
            override fun onResponse(
                call: Call<HistoryContentResponse>,
                response: Response<HistoryContentResponse>
            ) {
                val resp : HistoryContentResponse = response.body()!!
                Log.d("HISTORYCONTENT/SUCCESS", resp.message)
                when(resp.code){
                    1000 -> historyContentView.onHistoryContentSuccess(resp.result)
                    else -> historyContentView.onHistoryContentFailure()
                }
            }

            override fun onFailure(call: Call<HistoryContentResponse>, t: Throwable) {
                Log.d("HISTORYCONTENT/FAILURE", t.message.toString())
            }
        })
    }
}