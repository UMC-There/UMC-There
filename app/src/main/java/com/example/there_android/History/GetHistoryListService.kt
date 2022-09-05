package com.example.there_android.History

import android.util.Log
import com.example.there_android.utils.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetHistoryListService {

    private lateinit var getHistoryListView : GetHistoryListView

    private var getHistoryListRetrofit : GetHistoryListInterface = getRetrofit().create(
        GetHistoryListInterface::class.java)

    fun setGetHistoryView(getHistoryView: GetHistoryListView){
        this.getHistoryListView = getHistoryView
    }

    fun getHistory(request : GetHistoryListRequest){
        getHistoryListRetrofit.getHistory(request.postIdx).enqueue(object : Callback<GetHistoryListResponse>{
            override fun onResponse(
                call: Call<GetHistoryListResponse>,
                response: Response<GetHistoryListResponse>
            ) {
                val resp : GetHistoryListResponse = response.body()!!
                Log.d("GETHISTORYLIST/SUCCESS", resp.message)
                Log.d("CHECK", resp.result.toString())
                when(resp.code){
                    1000 -> getHistoryListView.onGetHistorySuccess(resp.result)
                    else -> getHistoryListView.onGetHistoryFailure()
                }
            }

            override fun onFailure(call: Call<GetHistoryListResponse>, t: Throwable) {
                Log.d("GETHISTORYLIST/FAILURE", t.message.toString())
            }
        })
    }
}