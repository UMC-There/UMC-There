package com.example.there_android.Auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.there_android.R
import com.example.there_android.databinding.ActivityJoinBinding
import java.util.regex.Pattern

class JoinActivity : AppCompatActivity(), JoinView {
    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.joinBackIv.setOnClickListener{
            finish()
        }
        binding.joinPwvisibleIv.setOnClickListener{
            visblePw()
        }
        binding.joinBtn.setOnClickListener{
            checkPw()
        }
    }

    //비밀번호 show/hide
    private fun visblePw() {
        if(binding.joinPwEt.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
            binding.joinPwvisibleIv.setImageResource(R.drawable.btn_login_show);
            binding.joinPwEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            binding.joinCheckpwEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else{
            binding.joinPwvisibleIv.setImageResource(R.drawable.btn_login_hide);
            binding.joinPwEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
            binding.joinCheckpwEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    private fun checkPw() {
//        if (binding.joinEmailEt.text.toString().isEmpty()) {
//            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
//            return
//        }
//        //비밀번호 검사
//        if (binding.joinPwEt.text.toString().isEmpty()) {
//            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
//            return
//        }
        //비밀번호 유효성 검사
        val symbol = "^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{6,12}\$"
        // 비밀번호 유효성 검사식2 : 영문자 대소문자가 적어도 하나씩은 포함되어야 한다.
        val alpha = "([a-z].*[A-Z])|([A-Z].*[a-z])"
        if (!Pattern.matches(symbol, binding.joinPwEt.text.toString())) {
            binding.joinPwvalidTv.visibility = View.VISIBLE
            binding.joinPwview.setBackgroundColor(Color.parseColor("#B80D00"))
            return
        }else if(binding.joinPwEt.text.toString() != binding.joinCheckpwEt.text.toString()){
            binding.joinErrorTv.visibility = View.VISIBLE
            binding.joinCheckpwview.setBackgroundColor(Color.parseColor("#B80D00"))
            return
        }else{
            //회원가입 진행
            binding.joinPwvalidTv.visibility = View.INVISIBLE
            binding.joinPwview.setBackgroundColor(Color.parseColor("#B8C0CC"))
            binding.joinErrorTv.visibility = View.INVISIBLE
            binding.joinCheckpwview.setBackgroundColor(Color.parseColor("#B8C0CC"))
            join()
        }
    }

    private fun getUserAuth(): UserAuthRequest {
        val email: String = binding.joinEmailEt.text.toString()
        val nickName: String = binding.joinNameEt.text.toString()
        val password: String = binding.joinPwEt.text.toString()
        val checkpwd: String = binding.joinCheckpwEt.text.toString()

        return UserAuthRequest(nickName, email, password, checkpwd)
    }

    private fun join() {
        val userService = UserService()
        userService.setJoinView(this)
        userService.join(getUserAuth())

    }

    override fun onJoinSuccess() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onJoinFailure() {
        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
    }
}