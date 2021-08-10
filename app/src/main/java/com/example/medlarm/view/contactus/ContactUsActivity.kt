package com.example.medlarm.view.contactus

import android.os.Bundle
import com.example.medlarm.databinding.ActivityAboutUsBinding
import com.example.medlarm.databinding.ActivityContactUsBinding
import com.example.medlarm.view.common.BaseActivity

class ContactUsActivity : BaseActivity<ActivityContactUsBinding>() {

    override fun getViewBinding() = ActivityContactUsBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}