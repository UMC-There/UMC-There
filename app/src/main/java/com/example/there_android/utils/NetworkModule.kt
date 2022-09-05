package com.example.there_android.utils

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

const val BASE_URL = "https://recordinthere.shop/"
//class AuthInterceptor : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        var req = chain.request().newBuilder().addHeader("Authorization", GlobalApplication.spf.spfJwt ?: "").build()
//        return chain.proceed(req)
//    }
//    val okHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()
//    val retrofit: Retrofit by lazy {
//        Retrofit.Builder()
//            .client(okHttpClient)
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create()).build()
//    }
//}
object networkModule {
    fun getRetrofit(): Retrofit{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient(AppInterceptor()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }
    //okhttp : 헤더에 토큰 저장
    private fun okHttpClient(interceptor: AppInterceptor): OkHttpClient
            = OkHttpClient.Builder().run {
        addInterceptor(interceptor)
        build()
    }
    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain) : Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("jwt", GlobalApplication.spf.spfJwt.toString() ?: "") //헤더에 토큰 저장
                .build()
            proceed(newRequest)
        }
    }

}
