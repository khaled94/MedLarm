package com.example.medlarm.view.userhistory

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medlarm.databinding.ActivityUserHistoryBinding
import com.example.medlarm.view.common.Alarm
import com.example.medlarm.view.common.BaseActivity
import java.util.*

class UserHistoryActivity : BaseActivity<ActivityUserHistoryBinding>() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private val alarms = mutableListOf<Alarm>()

    override fun getViewBinding() = ActivityUserHistoryBinding.inflate(layoutInflater)

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        linearLayoutManager = LinearLayoutManager(this)
        binding.rvAlarms.layoutManager = linearLayoutManager

        val alarm1 = Alarm("panadol", "once", null, null,
            Date(2019, 3, 3),Date(2019, 5, 10))
        val alarm2 = Alarm("decl", "once", false, "",
            Date(2020, 4, 4),Date(2020, 5, 15))
        val alarm3 = Alarm("moov", "twice", false, "",
            Date(2021, 3, 8),Date(2021, 9, 10))

        alarms.add(alarm1)
        alarms.add(alarm2)
        alarms.add(alarm3)

        binding.rvAlarms.adapter = UserHistoryAdapter(alarms)
    }
}