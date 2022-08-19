package com.example.there_android

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class MySharedPreference (context: Context) {

    private val spf = context.getSharedPreferences("user", Context.MODE_PRIVATE)

    var spfJwt: String?
        get() = spf.getString("jwt", "")
        set(value) = spf.edit().putString("jwt", value).apply()
    var spfIdx: Int?
        get() = spf.getInt("userIdx", 0)
        set(value) = spf.edit().putInt("userIdx", value!!).apply()
}