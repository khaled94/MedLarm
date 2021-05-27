package com.example.medlarm.view.settings

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivitySettingsBinding
import com.example.medlarm.view.aboutus.AboutUsActivity
import com.example.medlarm.view.changepassword.ChangePasswordActivity
import com.example.medlarm.view.common.BaseActivity
import com.example.medlarm.view.contactus.ContactUsActivity
import com.example.medlarm.view.editprofile.EditProfileActivity
import com.example.medlarm.view.language.ChooseLanguageActivity
import com.example.medlarm.view.login.LoginActivity
import com.example.medlarm.view.signup.SignUpActivity


class SettingsActivity : BaseActivity() {

    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var settingsBinding : ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(settingsBinding.root)

        if(preferenceManager.getCurrentLocale() == "ar")
            settingsBinding.tvLanguage.text = getString(R.string.arabic)
        else
            settingsBinding.tvLanguage.text = getString(R.string.english)

        settingsBinding.ivEdit.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        settingsBinding.ivAddProfile.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        settingsBinding.tvLanguageText.setOnClickListener {
            val intent = Intent(this, ChooseLanguageActivity::class.java)
            startActivity(intent)
        }

        settingsBinding.tvChangePasswordText.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        settingsBinding.tvAbout.setOnClickListener {
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }

        settingsBinding.tvContactUs.setOnClickListener {
            val intent = Intent(this, ContactUsActivity::class.java)
            startActivity(intent)
        }

        settingsBinding.btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}