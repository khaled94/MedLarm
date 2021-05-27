package com.example.medlarm.view.alarm

import android.os.Bundle
import com.example.medlarm.databinding.ActivityAlarmBinding
import com.example.medlarm.view.common.BaseActivity

class RingActivity : BaseActivity() {
    lateinit var binding: ActivityAlarmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}