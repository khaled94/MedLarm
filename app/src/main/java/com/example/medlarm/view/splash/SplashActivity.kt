package com.example.medlarm.view.splash

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivitySplashBinding
import com.example.medlarm.view.alarm.Receiver
import com.example.medlarm.view.common.BaseActivity
import com.example.medlarm.view.login.LoginActivity
import java.util.*

class SplashActivity : BaseActivity() {

    lateinit var splashBinding : ActivitySplashBinding
    private val _splashTimeOut: Long = 2000
    lateinit var handler: Handler
    private val REQUEST_CODE = 100
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Creating the pending intent to send to the BroadcastReceiver
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val receiverIntent = Intent(this, Receiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, receiverIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Setting the specific time for the alarm manager to trigger the intent, in this example, the alarm is set to go off at 23:30, update the time according to your need
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY))
        calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)+1)

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )

        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)


    val myFadeInAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.splash_fading)
        splashBinding.ivLogo.startAnimation(myFadeInAnimation)

        handler = Handler(Looper.getMainLooper())
        handler.postDelayed(
            {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            },
            _splashTimeOut
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancels the pendingIntent if it is no longer needed after this activity is destroyed.
        alarmManager.cancel(pendingIntent)
    }

}