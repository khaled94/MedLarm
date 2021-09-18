package com.example.medlarm.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivityLoginBinding
import com.example.medlarm.utils.ErrorEntity
import com.example.medlarm.utils.State
import com.example.medlarm.view.common.BaseActivity
import com.example.medlarm.view.home.HomeActivity
import com.example.medlarm.view.passwordrecovery.PasswordRecoveryActivity
import com.example.medlarm.view.signup.SignUpActivity
import javax.inject.Inject


class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var loginViewModel: LoginViewModel

    override fun getViewBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        // TODO       sharedPref.clearSharedPref()// to be cleared if directed from old credentials

        binding.root.setOnClickListener {
            // it hide soft keyboard on edit text outside root layout click
            hideSoftKeyboard()

            // remove focus from edit text
            binding.etUsername.clearFocus()
            binding.etPassword.clearFocus()
        }

        binding.btnLogin.setOnClickListener {
            if (checkEmpty()){
                loginViewModel.login(
                    binding.etUsername.text.toString(),
                    binding.etPassword.text.toString()
                )
            }
            else{
                Toast.makeText(
                    this,
                    getString(R.string.wrong_username_password),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.tvForgetPassword.setOnClickListener {
            val intent = Intent(this, PasswordRecoveryActivity::class.java)
            startActivity(intent)
        }

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
        observeOnUser()
    }

    private fun observeOnUser() {
        loginViewModel.user.observe(this, {
            when (it) {
                is State.Loading -> {
                    dialog.show()
                }
                is State.Success -> {
                    dialog.dismiss()
                    if (it.data.Status == "Done") {
                        preferenceManager.setUserId(userId = it.data.userResponseData.Id)
                        Log.e("id",preferenceManager.getUserId().toString())
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            getString(R.string.wrong_username_password),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is State.Error -> {
                    dialog.dismiss()
                    when (it.exception) {
                        is ErrorEntity.NetworkError -> {

                        }
                        is ErrorEntity.AccessDenied -> {

                        }
                        is ErrorEntity.BadRequest -> {

                        }
                        is ErrorEntity.NotFound -> {

                        }
                        is ErrorEntity.ServiceUnavailable -> {

                        }
                        is ErrorEntity.UnKnownError -> {

                        }
                    }
                    Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun checkEmpty(): Boolean {
        Log.e("username test",binding.etUsername.text.trim().toString())
        Log.e("username check",binding.etUsername.text.trim().isNotEmpty().toString())
        Log.e("Password test",binding.etPassword.text.trim().toString())
        Log.e("Password check",binding.etPassword.text.trim().isNotEmpty().toString())

        if (binding.etUsername.text.trim().isNotEmpty() &&
            binding.etPassword.text.trim().isNotEmpty())
                return true
        return false
    }
}