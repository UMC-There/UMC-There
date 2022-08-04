package com.example.there_android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AddHistoryRetrofit {
    //통신할 서버 url
    private const val BASE_URL = "http://3.39.57.176:8080/swagger-ui/index.html"

    //Retrofit 객체 초기화
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val addHistory: AddHistoryInterface = retrofit.create(AddHistoryInterface::class.java)
}
