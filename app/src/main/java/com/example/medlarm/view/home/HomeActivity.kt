package com.example.medlarm.view.home

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivityHomeBinding
import com.example.medlarm.view.addmedicine.AddMedicineActivity
import com.example.medlarm.view.alarm.AlarmActivity
import com.example.medlarm.view.alarm.Receiver
import com.example.medlarm.view.common.*
import com.example.medlarm.view.editmedicine.EditMedicineActivity
import com.example.medlarm.view.medicinehistory.MedicineHistoryActivity
import com.example.medlarm.view.settings.SettingsActivity
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    //  lateinit var loginViewModel: LoginViewModel
    lateinit var homeBinding: ActivityHomeBinding

    private lateinit var linearLayoutManager: LinearLayoutManager
    private val alarms = mutableListOf<Alarm>()
    private var currentMonth = 0

    private val REQUEST_CODE = 100
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        // Creating the pending intent to send to the BroadcastReceiver
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val receiverIntent = Intent(this, Receiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, receiverIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Setting the specific time for the alarm manager to trigger the intent, in this example, the alarm is set to go off at 23:30, update the time according to your need
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY))
        calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)+1)

        // Starts the alarm manager
        /* alarmManager.setRepeating(
             AlarmManager.RTC,
             calendar.timeInMillis,
             AlarmManager.INTERVAL_DAY,
             pendingIntent
         )*/

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )

        linearLayoutManager = LinearLayoutManager(this)
        homeBinding.rvAlarms.layoutManager = linearLayoutManager
//        loginViewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        calendar.time = Date()
        currentMonth = calendar[Calendar.MONTH]
        val mFormat = SimpleDateFormat("MMM yyyy")

        val currentDate = mFormat.format(Date())
        homeBinding.tvCurrentMonth.text = currentDate

        val startDate = Calendar.getInstance()
        startDate.add(Calendar.DAY_OF_MONTH, 0)

        val endDate = Calendar.getInstance()
        endDate.add(Calendar.YEAR, 1)

        val horizontalCalendar = HorizontalCalendar.Builder(this, R.id.calendarView)
            .range(startDate, endDate)
            .datesNumberOnScreen(5)
            .build()

        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDateSelected(date: Calendar?, position: Int) {
                homeBinding.tvCurrentMonth.text = mFormat.format(date?.timeInMillis)
            }
        }

        val alarm1 = Alarm("panadol", "once", false, "",null,null)
        val alarm2 = Alarm("decl", "once", false, "",null,null)
        val alarm3 = Alarm("moov", "twice", false, "",null,null)

        alarms.add(alarm1)
        alarms.add(alarm2)
        alarms.add(alarm3)

        homeBinding.rvAlarms.adapter = HomeAdapter(alarms) { alarm: Alarm ->
            selectAlarm(alarm)
        }

        homeBinding.ivSetting.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        homeBinding.btnAddAlarmFull.setOnClickListener {
            val intent = Intent(this, AddMedicineActivity::class.java)
            startActivity(intent)
        }

        homeBinding.btnAddAlarmEmpty.setOnClickListener {
            val intent = Intent(this, AddMedicineActivity::class.java)
            startActivity(intent)
        }

    }

    private fun selectAlarm(alarm: Alarm) {
        when (alarm.action) {
            "edit" -> {
                val intent = Intent(this, EditMedicineActivity::class.java)
                startActivity(intent)
            }
            "delete" -> {
                val intent = Intent(this, AlarmActivity::class.java)
                startActivity(intent)
            }
            "history" -> {
                val intent = Intent(this, MedicineHistoryActivity::class.java)
                startActivity(intent)
            }
        }

    }

}

//var swipeController = SwipeController()
//var itemTouchHelper = ItemTouchHelper(swipeController)

//import com.alexandrius.accordionswipelayout.library.SwipeLayout
/*
 fun collapse(view: View?) {
        (rv_alarms.findViewHolderForAdapterPosition(0)!!.itemView as SwipeLayout).setItemState(
            SwipeLayout.ITEM_STATE_COLLAPSED,
            true
        )
    }
 */
///////////////////////////////Option 1 /////////////////////////////////////////////////

/* val touchListener = RecyclerTouchListener(this, homeBinding.rvAlarms)

        touchListener
            .setClickable(object : RecyclerTouchListener.OnRowClickListener {

                override fun onRowClicked(position: Int) {
                    Toast.makeText(applicationContext, alarms[position].name, Toast.LENGTH_SHORT).show();

                }

                override fun onIndependentViewClicked(independentViewID: Int, position: Int) {
                    TODO("Not yet implemented")
                }
            })
            .setSwipeOptionViews(R.id.iv_bg_delete,R.id.iv_bg_edit,R.id.iv_bg_calender)
            .setSwipeable(R.id.foreground_view, R.id.background_view, object : RecyclerTouchListener.OnSwipeOptionsClickListener {

                override fun onSwipeOptionClicked(viewID: Int, position: Int) {

                    when (viewID) {
                        R.id.iv_bg_delete -> {

                        }
                        R.id.iv_bg_edit -> {

                        }
                        R.id.iv_bg_calender -> {

                        }
                    }

                    }
                })
        homeBinding.rvAlarms.addOnItemTouchListener(touchListener)*/
////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////option 2 ////////////////////////////////////////
/* var itemTouchHelper = ItemTouchHelper(swipeController)
      itemTouchHelper.attachToRecyclerView(rv_alarms)

      rv_alarms.addItemDecoration(object : RecyclerView.ItemDecoration() {
          override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
              swipeController.onDraw(c)
          }
      })*/

/*   object : SwipeHelper(this, homeBinding.rvAlarms, false) {

       override fun instantiateUnderlayButton(
           viewHolder: RecyclerView.ViewHolder?,
           underlayButtons: MutableList<UnderlayButton>?
       ) {
           // Archive Button
           underlayButtons?.add(UnderlayButton(
               "Archive",
               AppCompatResources.getDrawable(
                   this@HomeActivity,
                   R.drawable.ic_clock
               ),
               Color.parseColor("#339EFF"), Color.parseColor("#ffffff"),
               UnderlayButtonClickListener { pos: Int ->
                   Toast.makeText(
                       this@HomeActivity,
                       "check $pos",
                       Toast.LENGTH_SHORT
                   ).show()
               }
           ))
       }
   }*/

///////////////////////////////////////////////////////////////////////////////////////////////
/*var swipeController = SwipeController(object : SwipeControllerActions() {
           override fun onLeftClicked(position: Int) {

           }
       })*/

/*  val dates = getFutureDatesOfCurrentMonth()
       linearLayoutManager = LinearLayoutManager(this)
       linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
       homeBinding.rvCalender.layoutManager = linearLayoutManager
       homeBinding.rvCalender.adapter=
               HomeAdapter(dates) { date: Date -> selectDate(date) }*/
/*
private fun getFutureDatesOfCurrentMonth(): List<Date> {
    // get all next dates of current month
    currentMonth = calendar[Calendar.MONTH]
    return getDates(mutableListOf())
}

private fun getDates(list: MutableList<Date>): List<Date> {
    // load dates of whole month
    calendar.set(Calendar.MONTH, currentMonth)
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    list.add(calendar.time)
    while (currentMonth == calendar[Calendar.MONTH]) {
        calendar.add(Calendar.DATE, +1)
        if (calendar[Calendar.MONTH] == currentMonth)
            list.add(calendar.time)
    }
    calendar.add(Calendar.DATE, -1)
    return list
}

private fun selectDate(date: Date){

} */