package com.example.medlarm.view.medicinehistory

import android.annotation.SuppressLint
import android.os.Bundle
import com.applandeo.materialcalendarview.EventDay
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivityAboutUsBinding
import com.example.medlarm.databinding.ActivityMedicineHistoryBinding
import com.example.medlarm.view.common.BaseActivity
import java.text.SimpleDateFormat
import java.util.*


class MedicineHistoryActivity : BaseActivity<ActivityMedicineHistoryBinding>() {

    override fun getViewBinding() = ActivityMedicineHistoryBinding.inflate(layoutInflater)
    private val events = mutableListOf<EventDay>()

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val calendar = Calendar.getInstance()
        val eventDay1= EventDay(Calendar.getInstance(), R.drawable.ic_check_mark)
        events.add(eventDay1)

        calendar.set(2021,3,14)
        val eventDay2= EventDay(calendar, R.drawable.ic_close)
        events.add(eventDay2)
        binding.calendarView.setEvents(events)



    }
}