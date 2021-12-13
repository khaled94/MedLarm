package com.example.medlarm.view.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.viewbinding.ViewBinding
import com.example.medlarm.app.MedLarm
import com.example.medlarm.data.model.requestModels.takenalarms.TakenAlarmListItem
import com.example.medlarm.data.model.responseModels.alarmlist.AlarmListResponseItem
import com.example.medlarm.utils.LocaleManager
import com.example.medlarm.utils.PreferenceManager
import com.example.medlarm.view.alarm.Receiver
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<B : ViewBinding> : DaggerAppCompatActivity() {

    lateinit var binding: B
    //lateinit var dialog: android.app.AlertDialog
    val dialog = CustomProgressDialog(this)
    var isAddProfile = false

    private val REQUEST_CODE = 100
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    var userAlarmList = mutableListOf<AlarmListResponseItem>()
    var takenAlarmList = ArrayList<TakenAlarmListItem>()

    private lateinit var oldPrefLocaleCode: String
    protected val preferenceManager by lazy {
        (application as MedLarm).preferenceManager
    }
    protected val notificationManager by lazy {
        (application as MedLarm).notificationManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (preferenceManager.getCurrentLocale() == "ar")
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        else
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR

       binding = getViewBinding()
       dialog.init()
   /*     dialog = SpotsDialog.Builder()
            .setContext(this)
            .setMessage(getString(R.string.loading))
            .build()*/
    }

    abstract fun getViewBinding(): B

    override fun attachBaseContext(newBase: Context) {
        oldPrefLocaleCode = PreferenceManager(newBase).getCurrentLocale()
        applyOverrideConfiguration(
            LocaleManager.getLocalizedConfiguration(
                newBase,
                oldPrefLocaleCode
            )
        )
        super.attachBaseContext(newBase)
    }

    override fun onResume() {
        val currentLocaleCode = PreferenceManager(this).getCurrentLocale()
        if (oldPrefLocaleCode != currentLocaleCode) {
            recreate() //locale is changed, restart the activity to update
            oldPrefLocaleCode = currentLocaleCode
        }
        super.onResume()
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun setAlarm(alarmTime: Long , alarmId : Int , alarmIndex: Int) {
        // Creating the pending intent to send to the BroadcastReceiver
        preferenceManager.setCurrentAlarmId(alarmId)
        preferenceManager.setCurrentAlarmIndex(alarmIndex)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val receiverIntent = Intent(this, Receiver::class.java)
        receiverIntent.putExtra("Ringtone",preferenceManager.getRingId())
        Log.e("time",alarmTime.toString())
        pendingIntent = PendingIntent.getBroadcast(
            this,
            REQUEST_CODE,
            receiverIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            //calendar.timeInMillis,
            alarmTime,
            pendingIntent
        )
    }

    fun Activity.hideSoftKeyboard() {
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

   /* fun showErrorToast(
        message: String = getString(R.string.error)
    ) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    } */

}
// Starts the alarm manager
/* alarmManager.setRepeating(
     AlarmManager.RTC,
     calendar.timeInMillis,
     AlarmManager.INTERVAL_DAY,
     pendingIntent
 )*/