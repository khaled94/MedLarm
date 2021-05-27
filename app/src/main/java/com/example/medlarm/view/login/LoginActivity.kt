package com.example.medlarm.view.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.medlarm.databinding.ActivityLoginBinding
import com.example.medlarm.view.common.BaseActivity
import com.example.medlarm.view.home.HomeActivity
import com.example.medlarm.view.passwordrecovery.PasswordRecoveryActivity
import com.example.medlarm.view.signup.SignUpActivity
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
  //  lateinit var loginViewModel: LoginViewModel
    lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

//        loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        loginBinding.btnLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginBinding.tvForgetPassword.setOnClickListener {
            val intent = Intent(this, PasswordRecoveryActivity::class.java)
            startActivity(intent)
        }

        loginBinding.tvSignUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}