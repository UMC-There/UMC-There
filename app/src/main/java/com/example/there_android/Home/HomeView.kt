package com.example.there_android.Home

interface HomeView {
    fun onHomeViewSuccess (response: HomeResponse)
    fun onHomeViewFailure ()
}