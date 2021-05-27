package com.example.medlarm.view.contactus

import android.os.Bundle
import com.example.medlarm.databinding.ActivityContactUsBinding
import com.example.medlarm.view.common.BaseActivity

class ContactUsActivity : BaseActivity() {

    lateinit var contactUsBinding: ActivityContactUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactUsBinding = ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(contactUsBinding.root)
    }
}