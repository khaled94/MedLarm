package com.example.medlarm.view.changepassword

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivityChangePasswordBinding
import com.example.medlarm.utils.ErrorEntity
import com.example.medlarm.utils.State
import com.example.medlarm.view.common.BaseActivity
import com.example.medlarm.view.login.LoginActivity
import javax.inject.Inject

class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var changePasswordViewModel: ChangePasswordViewModel

    override fun getViewBinding() = ActivityChangePasswordBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        changePasswordViewModel =
            ViewModelProvider(this, viewModelFactory).get(ChangePasswordViewModel::class.java)

        binding.btnSaveChanges.setOnClickListener {
            if (binding.etNewPassword == binding.etConfirmPassword) {
                changePasswordViewModel.changePassword(
                    preferenceManager.getUserId(),
                    binding.etNewPassword.text.toString()
                )
            }
            else {
                Toast.makeText(
                    this,
                    getString(R.string.wrong_username_password),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        observeOnPasswordChanged()
    }

    private fun observeOnPasswordChanged() {
        changePasswordViewModel.newPasswordResponse.observe(this, {
            when (it) {
                is State.Loading -> {
                    dialog.show()
                }
                is State.Success -> {
                    dialog.dismiss()
                    if (it.data.Status == "Done") {
                        val intent = Intent(this, LoginActivity::class.java)
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

}