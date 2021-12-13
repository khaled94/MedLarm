package com.example.medlarm.view.medicinehistory

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.EventDay
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivityMedicineHistoryBinding
import com.example.medlarm.utils.ErrorEntity
import com.example.medlarm.utils.State
import com.example.medlarm.view.common.BaseActivity
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class MedicineHistoryActivity : BaseActivity<ActivityMedicineHistoryBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var medicineHistoryViewModel: MedicineHistoryViewModel
    override fun getViewBinding() = ActivityMedicineHistoryBinding.inflate(layoutInflater)
    private val events = mutableListOf<EventDay>()
    private var calender: Calendar = Calendar.getInstance()



    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        medicineHistoryViewModel = ViewModelProvider(this, viewModelFactory).get(MedicineHistoryViewModel::class.java)

        /*val calendar = Calendar.getInstance()
        val eventDay1= EventDay(Calendar.getInstance(), R.drawable.ic_check_mark)
        events.add(eventDay1)

        calendar.set(2021,3,14)
        val eventDay2= EventDay(calendar, R.drawable.ic_close)
        events.add(eventDay2)
        binding.calendarView.setEvents(events)*/
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        var date = sdf.format(calender.time)
        medicineHistoryViewModel.getAlarmByDate(
            preferenceManager.getUserId(),
            date
        )

        binding.calendarView.setOnDayClickListener { eventDay ->
            val clickedDayCalendar = eventDay.calendar
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            var date = sdf.format(clickedDayCalendar.time)
            medicineHistoryViewModel.getAlarmByDate(
                preferenceManager.getUserId(),
                date
            )
            observeOnAlarmByDate()
        }
    }

    private fun observeOnAlarmByDate() {
        medicineHistoryViewModel.alarmByDateList.observe(this, {
            when (it) {
                is State.Loading -> {
                    dialog.show()
                }
                is State.Success -> {
                    dialog.dismiss()
                    if (it.data.isNotEmpty()) {
                        binding.rvAlarms.adapter =
                           MedicineHistoryAdapter(it.data)
                    } else {
                        /* Toast.makeText(
                             this,
                             getString(R.string.wrong_username_password),
                             Toast.LENGTH_SHORT
                         ).show()*/
                    }
                }
                is State.Error -> {
                    dialog.dismiss()
                    when (it.exception) {
                        is ErrorEntity.NetworkError -> {

                        }
                        is ErrorEntity.AccessDenied -> {

                        }
                        is ErrorEntity.BadRequest -> {

                        }
                        is ErrorEntity.NotFound -> {

                        }
                        is ErrorEntity.ServiceUnavailable -> {

                        }
                        is ErrorEntity.UnKnownError -> {

                        }
                    }
                    Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}