package com.example.medlarm.view.aboutus

import android.os.Bundle
import com.example.medlarm.databinding.ActivityAboutUsBinding
import com.example.medlarm.view.common.BaseActivity

class AboutUsActivity : BaseActivity() {

    lateinit var aboutUsBinding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aboutUsBinding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(aboutUsBinding.root)
    }
}