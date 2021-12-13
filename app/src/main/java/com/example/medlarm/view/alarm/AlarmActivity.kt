package com.example.medlarm.view.alarm

import android.os.Bundle
import com.example.medlarm.data.model.requestModels.takenalarms.TakenAlarmListItem
import com.example.medlarm.databinding.ActivityAlarmBinding
import com.example.medlarm.view.common.BaseActivity
import java.text.SimpleDateFormat
import java.util.*

class AlarmActivity : BaseActivity<ActivityAlarmBinding>() {

    override fun getViewBinding() = ActivityAlarmBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var index = preferenceManager.getCurrentAlarmIndex()
        val calender: Calendar = Calendar.getInstance()

        if( index < userAlarmList.size){
            index += 1
            takenAlarmList.add(TakenAlarmListItem(userAlarmList[index].Id,true))
            calender.time = specialFormat(userAlarmList[index].Date,userAlarmList[index].Time)
            setAlarm(calender.timeInMillis,userAlarmList[index].Id,index)
        }
        finish()

    }

    private fun specialFormat(inputDate : String , inputTime : String): Date {
        val myDateFormatter = inputDate.substring(0,10)+" "+inputTime
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        return sdf.parse(myDateFormatter)!!
    }

}