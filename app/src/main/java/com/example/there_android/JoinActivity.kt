package com.example.there_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.there_android.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity(), JoinView {
    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.joinBackIv.setOnClickListener{
            finish()
        }
        binding.joinBtn.setOnClickListener{
            join()
        }
    }

    private fun getUser(): User {
        val email: String = binding.joinEmailEt.text.toString()
        val name: String = binding.joinNameEt.text.toString()
        val pwd: String = binding.joinPwEt.text.toString()
        val checkpwd: String = binding.joinCheckpwEt.text.toString()

        return User(name, email, pwd, checkpwd)
    }

    private fun join() {
        if (binding.joinEmailEt.text.toString().isEmpty()) {
            Toast.makeText(this, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding.joinPwEt.text.toString() != binding.joinCheckpwEt.text.toString()) {
            binding.joinErrorTv.visibility = View.VISIBLE
            //Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val userService = UserService()
        userService.setJoinView(this)
        userService.join(getUser())

    }

    override fun onJoinSuccess() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onJoinFailure() {
        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
    }
}