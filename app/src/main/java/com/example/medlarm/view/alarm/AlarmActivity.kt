package com.example.medlarm.view.alarm

import android.os.Bundle
import com.example.medlarm.databinding.ActivityAboutUsBinding
import com.example.medlarm.databinding.ActivityAlarmBinding
import com.example.medlarm.view.common.BaseActivity

class AlarmActivity : BaseActivity<ActivityAlarmBinding>() {

    override fun getViewBinding() = ActivityAlarmBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}