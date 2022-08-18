package com.example.there_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.there_android.databinding.FragmentHistoryBinding

class HistoryFragment:Fragment() , GetHistoryListView{

    lateinit var binding: FragmentHistoryBinding

    private var postIdx : Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadData()
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun getContent() : GetHistoryListRequest{
        return GetHistoryListRequest(postIdx)
    }

    private fun loadData(){
        val getHistoryListService = GetHistoryListService()
        getHistoryListService.setGetHistoryView(this)
        getHistoryListService.getHistory(getContent())
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