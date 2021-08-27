package com.example.medlarm.view.passwordrecovery

import android.content.Intent
import android.os.Bundle
import com.example.medlarm.databinding.ActivityPasswordRecoveryBinding
import com.example.medlarm.view.changepassword.ChangePasswordActivity
import com.example.medlarm.view.common.BaseActivity

class PasswordRecoveryActivity: BaseActivity<ActivityPasswordRecoveryBinding>() {

    override fun getViewBinding() = ActivityPasswordRecoveryBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordRecoveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}
/*
 lateinit var etEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        viewInitializations()
    }

    fun viewInitializations() {

        etEmail = findViewById(R.id.et_email)

        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    fun validateInput(): Boolean {

        if (etEmail.text.toString().equals("")) {
            etEmail.setError("Please Enter Email")
            return false
        }
        // checking the proper email format
        if (!isEmailValid(etEmail.text.toString())) {
            etEmail.setError("Please Enter Valid Email")
            return false
        }
        return true
    }

    fun isEmailValid(email: String): Boolean {
        return EMAIL_ADDRESS.matcher(email).matches()
    }

    fun performForgetPassword (view: View) {
        if (validateInput()) {

            // Input is valid, here send data to your server


            val email = etEmail.text.toString()

            val intent = Intent(this, EmailVerify::class.java)
            startActivity(intent)

            Toast.makeText(this,"Email send to Register Email Address", Toast.LENGTH_SHORT).show()
 */