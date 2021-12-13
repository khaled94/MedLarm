package com.example.medlarm.view.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medlarm.R
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponseItem
import com.example.medlarm.databinding.ActivityHomeBinding
import com.example.medlarm.utils.ErrorEntity
import com.example.medlarm.utils.State
import com.example.medlarm.view.addmedicine.AddMedicineActivity
import com.example.medlarm.view.common.*
import com.example.medlarm.view.editmedicine.EditMedicineActivity
import com.example.medlarm.view.medicinehistory.MedicineHistoryActivity
import com.example.medlarm.view.settings.SettingsActivity
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var homeViewModel: HomeViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager
    private var currentMonth = 0
    val calendar = Calendar.getInstance()

    override fun getViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        // Setting the specific time for the alarm manager to trigger the intent, in this example, the alarm is set to go off at 23:30, update the time according to your need
        //calendar.timeInMillis = System.currentTimeMillis()
        //calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY))
        //calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 1)

        linearLayoutManager = LinearLayoutManager(this)
        binding.rvAlarms.layoutManager = linearLayoutManager

        calendar.time = Date()
        currentMonth = calendar[Calendar.MONTH]
        val mFormat = SimpleDateFormat("MMM yyyy")
        val currentDate = mFormat.format(Date())
        binding.tvCurrentMonth.text = currentDate

        val startDate = Calendar.getInstance()
        startDate.add(Calendar.DAY_OF_MONTH, 0)

        val endDate = Calendar.getInstance()
        endDate.add(Calendar.YEAR, 1)

        if (isOnline()) {
            homeViewModel.updateTakenAlarms(takenAlarmList)
        }

        homeViewModel.getAlarmByDate(
            preferenceManager.getUserId(),
            specialFormat(calendar.time.toString())
        )
        observeOnAlarmByDate()
        //specialFormat(calendar.time.toString()).let { Log.e("today", it) }

        val horizontalCalendar = HorizontalCalendar.Builder(this, R.id.calendarView)
            .range(startDate, endDate)
            .datesNumberOnScreen(5)
            .build()

        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDateSelected(date: Calendar?, position: Int) {
                binding.tvCurrentMonth.text = mFormat.format(date?.timeInMillis)
                homeViewModel.getAlarmByDate(
                    preferenceManager.getUserId(),
                    specialFormat(date?.time.toString())
                )
                //specialFormat(date?.time.toString()).let { Log.e("newDate", it) }
            }
        }

        binding.ivSetting.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.btnAddAlarmFull.setOnClickListener {
            val intent = Intent(this, AddMedicineActivity::class.java)
            startActivity(intent)
        }

        binding.btnAddAlarmEmpty.setOnClickListener {
            val intent = Intent(this, AddMedicineActivity::class.java)
            startActivity(intent)
        }

    }

    private fun selectAlarm(alarm: AlarmByDateResponseItem) {
        when (alarm.action) {
            "edit" -> {
                val intent = Intent(this, EditMedicineActivity::class.java)
                intent.putExtra("alarmId",alarm.Id)
                startActivity(intent)
            }
            "delete" -> {
                homeViewModel.removeAlarm(alarm.Id)
                observeOnRemoveAlarm()
            }
            "history" -> {
                val intent = Intent(this, MedicineHistoryActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun observeOnAlarmByDate() {
        homeViewModel.alarmByDateList.observe(this, {
            when (it) {
                is State.Loading -> {
                    dialog.show()
                }
                is State.Success -> {
                    dialog.dismiss()
                    if (it.data.isNotEmpty()) {
                        binding.rvAlarms.adapter =
                            HomeAdapter(it.data) { alarm: AlarmByDateResponseItem ->
                                selectAlarm(alarm)
                            }
                    } else {
                         Toast.makeText(
                             this,
                             getString(R.string.network_error),
                             Toast.LENGTH_SHORT
                         ).show()
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

    private fun observeOnRemoveAlarm() {
        homeViewModel.alarmRemoved.observe(this, {
            when (it) {
                is State.Loading -> {
                    dialog.show()
                }
                is State.Success -> {
                    dialog.dismiss()
                    if (it.data.Code == "200") {
                        homeViewModel.getAlarmByDate(
                            preferenceManager.getUserId(),
                            specialFormat(calendar.time.toString())
                        )
                    } else {
                        Toast.makeText(
                             this,
                             getString(R.string.network_error),
                             Toast.LENGTH_SHORT
                         ).show()
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

    @SuppressLint("SimpleDateFormat")
    private fun dateSent(myd: String): String? {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(myd)
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: String): String? {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = format.parse(date)
        return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(formattedDate!!.time)
    }

    private fun specialFormat(inputDate: String): String {
        val format = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
        val date = format.parse(inputDate)
        return SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date!!.time)
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getAlarmByDate(
            preferenceManager.getUserId(),
            specialFormat(calendar.time.toString())
        )
        observeOnAlarmByDate()
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connectivityManager.activeNetworkInfo != null &&
                connectivityManager.activeNetworkInfo!!.isConnected
    }

}
/*
   binding.rvAlarms.adapter = HomeAdapter(alarms) { alarm: Alarm ->
            selectAlarm(alarm)
        }
 */