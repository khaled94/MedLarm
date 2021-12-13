package com.example.medlarm.view.settings

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.medlarm.view.ringtone.RingtoneActivity

class SettingsActivity : BaseActivity<ActivitySettingsBinding>() {

    lateinit var viewModelFactory: ViewModelProvider.Factory
    override fun getViewBinding() = ActivitySettingsBinding.inflate(layoutInflater)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(preferenceManager.getCurrentLocale() == "ar")
            binding.tvLanguage.text = getString(R.string.arabic)
        else
            binding.tvLanguage.text = getString(R.string.english)


        binding.tvProfileName.text = "Hi".plus(preferenceManager.getUserName())
        binding.ivEdit.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

      /*  binding.ivAddProfile.setOnClickListener {
            val intent = Intent(this, AddProfileActivity::class.java)
            startActivity(intent)
        }*/

        binding.tvLanguageText.setOnClickListener {
            val intent = Intent(this, ChooseLanguageActivity::class.java)
            startActivity(intent)
        }

        binding.tvChangePasswordText.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        binding.tvChangeRingtoneText.setOnClickListener {
            val intent = Intent(this, RingtoneActivity::class.java)
            startActivity(intent)
        }

        binding.tvAbout.setOnClickListener {
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }

        binding.tvContactUs.setOnClickListener {
            val intent = Intent(this, ContactUsActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            preferenceManager.clear()
            Log.e("my user id", preferenceManager.getUserId().toString())
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}