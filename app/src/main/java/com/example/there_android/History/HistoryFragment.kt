package com.example.there_android.History

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.there_android.databinding.FragmentHistoryBinding

class HistoryFragment:Fragment() , GetHistoryListView {

    lateinit var binding: FragmentHistoryBinding

    private var content = GetHistoryListRequest(0)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        val postIdx = 40 //arguments?.getInt("postIdx", 0)!!
        if(postIdx == null){
            Log.d("CHECK", "null값 들어감")
        }

        content = getContent(postIdx)

        //history header 불러오기
        loadData()

        return binding.root
    }

<<<<<<< HEAD:app/src/main/java/com/example/there_android/HistoryFragment.kt
    override fun onStart(){
        super.onStart()
        loadData()
    }

    private fun getContent(postIdx : Int) : GetHistoryListRequest{
=======
    private fun getContent(postIdx : Int) : GetHistoryListRequest {
>>>>>>> d5b860252cf5c41e8a2b1d8db5e63300f3fc03d6:app/src/main/java/com/example/there_android/History/HistoryFragment.kt
        return GetHistoryListRequest(postIdx)
    }

    private fun loadData(){
        val getHistoryListService = GetHistoryListService()
        getHistoryListService.setGetHistoryView(this)
        getHistoryListService.getHistory(content)
    }
    private fun setAdapter(result : List<GetHistoryListResponse.Result>){
        val getHistoryAdapter = HistoryRVAdapter(this.requireContext(), result)
        binding.historyListRv.adapter = getHistoryAdapter
        binding.historyListRv.layoutManager = LinearLayoutManager(this.context)
        binding.historyListRv.setHasFixedSize(true)
    }


    override fun onGetHistorySuccess(result: List<GetHistoryListResponse.Result>) {
        setAdapter(result)
    }

    override fun onGetHistoryFailure() {

    }
}