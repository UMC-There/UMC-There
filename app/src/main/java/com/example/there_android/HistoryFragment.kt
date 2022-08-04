package com.example.there_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.there_android.databinding.FragmentHistoryBinding

class HistoryFragment:Fragment() {
    lateinit var binding: FragmentHistoryBinding
    private lateinit var adapter: HistoryRVAdapter

    val hData = mutableListOf<HistoryData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)



        return binding.root
    }

    fun initHistoryRecyclerView(){
        //어댑터 객체 생성
        val adapter = HistoryRVAdapter()
        adapter.datalist = hData
        binding.historyListRv.adapter = adapter
        binding.historyListRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}