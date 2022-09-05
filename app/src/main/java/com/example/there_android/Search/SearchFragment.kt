package com.example.there_android.Search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.there_android.databinding.FragmentSearchBinding

class SearchFragment: Fragment() {
    lateinit var binding: FragmentSearchBinding
    lateinit var searchview: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchEt.setOnClickListener{

        }
        binding.searchSearchbtn.setOnClickListener{

        }
        return binding.root
    }
    private fun searchInput(){

    }

    private fun searchview(){
        searchview.queryHint = "검색"
        searchview.isSubmitButtonEnabled = true
        searchview.onActionViewExpanded()
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            // 검색 버튼 누를 때 호출
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            // 검색창에서 글자가 변경이 일어날 때마다 호출
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

    }
}