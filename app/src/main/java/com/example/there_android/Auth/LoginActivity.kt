package com.example.there_android.Auth

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.there_android.*
import com.example.there_android.databinding.ActivityLoginBinding
import com.example.there_android.utils.GlobalApplication
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient

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
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }
        binding.loginPwvisibleIv.setOnClickListener{
            visblePw()
        }

        binding.loginBtn.setOnClickListener{
            login()
        }
        binding.loginKakaoBtn.setOnClickListener{
            //kakaoWeb()
            kakaoLogin()
        }
    }

    //비밀번호 show/hide
    private fun visblePw() {
        if(binding.loginPwEt.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
            binding.loginPwvisibleIv.setImageResource(R.drawable.btn_login_show);
            binding.loginPwEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else{
            binding.loginPwvisibleIv.setImageResource(R.drawable.btn_login_hide);
            binding.loginPwEt.setTransformationMethod(PasswordTransformationMethod.getInstance());

        }
    }

    private fun getUserAuth(): UserAuth {
        val email = binding.loginIdEt.text.toString()
        val password = binding.loginPwEt.text.toString()

        return UserAuth(email = email, password = password, nickName = "", checkpwd = "")
    }

    private fun login() {
        val userService = UserService()
        userService.setLoginView(this)
        userService.login(getUserAuth())
    }
//jwt, userIdx 토큰 저장
    private fun saveJwt(jwt: String, userIdx: Int) {
        GlobalApplication.spf.spfJwt= jwt
        GlobalApplication.spf.spfIdx = userIdx
        Log.d("login-jwt", "토큰 저장됨")
    }

    //카카오 웹뷰
    private fun kakaoWeb() {
        val intent = Intent(this, WebViewActivity::class.java)
        startActivity(intent)
    }

    //카카오 로그인
    private fun kakaoLogin() {
        // 토큰 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                //Toast.makeText(this, "토큰 정보 보기 실패", Toast.LENGTH_SHORT).show()
            }
        else if (tokenInfo != null) {
            Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()
        }
    }

    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            when {
                error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                    Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                }
                error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                    Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                }
                error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                    Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                }
                error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                    Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                }
                error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                    Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                }
                error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                    Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                }
                error.toString() == AuthErrorCause.ServerError.toString() -> {
                    Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                }
                error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                    Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                }
                else -> { // Unknown
                    Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else if (token != null) {
            Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()
        }
    }

    //카카오 설치 시 카카오로 로그인, 미설치 시 카카오톡 계정으로 로그입
    if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
       // UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
    }else{
        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
    }
    }

    override fun onLoginSuccess(code : Int , result: Result) {
        when(code) {
            1000 -> {
                saveJwt(result.jwt, result.userIdx)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
        }
    }
    override fun onLoginFailure() {

    }


}