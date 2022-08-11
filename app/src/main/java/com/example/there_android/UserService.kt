package com.example.there_android

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserService {
    private lateinit var joinView: JoinView
    private lateinit var loginView: LoginView

    fun setJoinView(joinView: JoinView){
        this.joinView = joinView
    }
    fun setLoginView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun join(user: User) {

        val joinService = getRetrofit().create(UserRetrofitInterface::class.java)

        joinService.join(user).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful && response.code() == 200) {
                    val joinResponse: UserResponse = response.body()!!
                    Log.d("JOIN-RESPONSE", joinResponse.toString())
                        //joinView.onJoinSuccess()
                    when (joinResponse.code) {
                        0 -> joinView.onJoinSuccess()
                        else -> joinView.onJoinFailure()
                    }
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("JOIN-ACT/ERROR", t.message.toString())
            }
        })
    }

    fun login(user: User) {
        val loginService = getRetrofit().create(UserRetrofitInterface::class.java)


        loginService.login(user).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful && response.code() == 200) {
                    val loginResponse: UserResponse = response.body()!!
                    //loginView.onLoginSuccess(loginResponse.code, loginResponse.result!!)
                    when (val code = loginResponse.code) {
                        0 -> loginView.onLoginSuccess(code, loginResponse.result!!)
                        else -> loginView.onLoginFailure()
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("LOGIN-ACT/ERROR", t.message.toString())
            }
        })
    }
}