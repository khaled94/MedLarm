package com.example.medlarm.view.aboutus

import android.os.Bundle
import com.example.medlarm.databinding.ActivityAboutUsBinding
import com.example.medlarm.databinding.ActivityLoginBinding
import com.example.medlarm.view.common.BaseActivity

class AboutUsActivity : BaseActivity<ActivityAboutUsBinding>() {

    override fun getViewBinding() = ActivityAboutUsBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}