package com.example.there_android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.there_android.databinding.FragmentHistoryBinding

class HistoryFragment:Fragment() , GetHistoryListView{

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

    override fun onStart(){
        super.onStart()
        loadData()
    }

    private fun getContent(postIdx : Int) : GetHistoryListRequest{
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