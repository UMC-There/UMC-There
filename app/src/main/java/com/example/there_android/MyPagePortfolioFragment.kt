package com.example.there_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.there_android.databinding.FragmentMypagePortfolioBinding

class MyPagePortfolioFragment : Fragment(), PortfolioView {
    private lateinit var binding: FragmentMypagePortfolioBinding
    private var myPageData = ArrayList<MyPageData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypagePortfolioBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onStart() {
        super.onStart()
        getUserPfol()
    }

    private fun getUserPfol() {
        val portfolioService = PortfolioService()
        portfolioService.setPortfolioView(this)
        portfolioService.getUserPfol(GlobalApplication.spf.spfIdx!!)
    }

    private fun initRecyclerView(result: List<PortfolioResult>) {
        //리사이클러뷰 어댑터
        val myPagePortfolioRVAdapter = MyPagePortfolioRVAdapter(requireContext(), result)
        binding.mypagePortfolioRecyclerview.adapter = myPagePortfolioRVAdapter
        binding.mypagePortfolioRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        //외부 객체 리스너 전달
        myPagePortfolioRVAdapter.setPortfolioClickListener(object :
            MyPagePortfolioRVAdapter.PortfolioClickListener {
            override fun onItemClick(portfolioIdx: Int) {
                toPortfolioFragment(portfolioIdx)
            }
        })
    }

    private fun toPortfolioFragment(portfolioIdx: Int) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, PortfolioFragment())
            .commitAllowingStateLoss()
//        val intent = Intent(context, PostActivity::class.java)
//        ContextCompat.startActivity(intent)
    }

    override fun onPortfolioSuccess(result: List<PortfolioResult>) {
        initRecyclerView(result)
        Log.d("MyPortfolio-SUCCESS", result.toString())
    }

    override fun onPortfolioFailure(code: Int, message: String) {
        Log.d("MyPortfolio-RESPONSE", code.toString() + message)
    }

}

