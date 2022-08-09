package com.example.there_android

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddHistoryService{
    private lateinit var addHistoryView: AddHistoryView

    val addHistoryRetrofit : AddHistoryInterface = getRetrofit().create(AddHistoryInterface::class.java)

    fun setAddHistoryView(addHistoryView: AddHistoryActivity){
        this.addHistoryView = addHistoryView
    }

    fun addHistory(request: AddHistoryRequest){
        val addHistory = addHistoryRetrofit

        addHistory.postHistory(request).enqueue(object  : Callback<AddHistoryResponse>{
            override fun onResponse(
                call: Call<AddHistoryResponse>,
                response: Response<AddHistoryResponse>
            ) {
                val resp : AddHistoryResponse = response.body()!!
                Log.d("ADDHISTORY/SUCCESS", resp.message)
                when(resp.code){
                    2000 -> addHistoryView.onAddHistorySuccess(resp.code, resp.message)
                    else -> addHistoryView.onAddHistoryFailure()
                }
            }

            override fun onFailure(call: Call<AddHistoryResponse>, t: Throwable) {
                Log.d("ADDHISTORY/FAILURE", t.cause.toString())
            }
        })
    }
}