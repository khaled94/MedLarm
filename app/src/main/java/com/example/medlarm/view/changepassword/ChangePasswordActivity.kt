package com.example.medlarm.view.changepassword

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.medlarm.databinding.ActivityAboutUsBinding
import com.example.medlarm.databinding.ActivityChangePasswordBinding
import com.example.medlarm.view.common.BaseActivity
import com.example.medlarm.view.login.LoginActivity
import javax.inject.Inject

class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    //  lateinit var loginViewModel: LoginViewModel

    override fun getViewBinding() = ActivityChangePasswordBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        binding.btnSaveChanges.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}