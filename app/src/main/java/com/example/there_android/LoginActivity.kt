package com.example.there_android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.there_android.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBackIv.setOnClickListener{
            finish()
        }
        binding.loginJoinTv.setOnClickListener{
            val intent = Intent(this,JoinActivity::class.java)
            startActivity(intent)
        }
        binding.loginBtn.setOnClickListener{
            login()
        }
    }

    private fun getUser(): User {
        val email = binding.loginIdEt.text.toString()
        val password = binding.loginPwEt.text.toString()

        return User(email = email, password = password, name = "", checkpwd = "")
    }

    private fun login() {
        if (binding.loginIdEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
//비밀번호 검사
        if (binding.loginPwEt.text.toString().isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        val userService = UserService()
        userService.setLoginView(this)
        userService.login(getUser())
    }
//jwt 토큰 저장
    private fun saveJwt(jwt: String) {
        val spf = getSharedPreferences("user" , MODE_PRIVATE)
        val editor = spf.edit()
        editor.putString("jwt", jwt)
        editor.apply()
    }

    override fun onLoginSuccess(code : Int , result: Result) {
        when(code) {
            0 -> {
                saveJwt(result.jwt)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
        }
    }
    override fun onLoginFailure() {

    }


}