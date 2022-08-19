package com.example.there_android

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    companion object {
        lateinit var spf : MySharedPreference
    }

    override fun onCreate() {
        spf = MySharedPreference(applicationContext)
        super.onCreate()

        KakaoSdk.init(this, "4eef694b16fcc83f835bc72fe162a5f7")
    }

}