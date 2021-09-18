package com.example.medlarm.view.alarm

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.PixelFormat
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.NotificationCompat
import com.example.medlarm.R
import com.example.medlarm.data.Constants.CHANNEL_ID
import kotlin.properties.Delegates

class AlarmService : Service() {

    //lateinit var notificationManager: NotificationManager
    private lateinit var notification: Notification
    private lateinit var mediaPlayer : MediaPlayer
    private lateinit var vibrator: Vibrator
    private var layoutType by Delegates.notNull<Int>()


    override fun onCreate() {
        super.onCreate()
        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val floatView = (getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.window_alarm, null)

        val taken = floatView.findViewById(R.id.btn_taken) as Button
        val snooze = floatView.findViewById(R.id.btn_snooze) as Button
        val close = floatView.findViewById(R.id.iv_cancel) as ImageView

        layoutType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        else
            WindowManager.LayoutParams.TYPE_SYSTEM_ALERT

        val params =
                WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                layoutType,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT)

        params.gravity = Gravity.CENTER
        params.x = 0
        params.y = 0
        windowManager.addView(floatView, params)

        floatView.setOnClickListener {
            windowManager.removeView(floatView)
            val intent = Intent(applicationContext, AlarmActivity::class.java)
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            applicationContext.startActivity(intent)
            mediaPlayer.stop()
            vibrator.cancel()
        }

        taken.setOnClickListener {
            windowManager.removeView(floatView)
            //val intent = Intent(applicationContext, AlarmActivity::class.java)
            //intent.flags = FLAG_ACTIVITY_NEW_TASK
            //applicationContext.startActivity(intent)

            mediaPlayer.stop()
            vibrator.cancel()
        }

        close.setOnClickListener {
            windowManager.removeView(floatView)
            //val intent = Intent(applicationContext, LoginActivity::class.java)
            //intent.flags = FLAG_ACTIVITY_NEW_TASK
            //applicationContext.startActivity(intent)
            mediaPlayer.stop()
            vibrator.cancel()
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.eraser)
        mediaPlayer.isLooping = true
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        //notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }


    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, RingActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0,
            notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmTitle = String.format("%s Alarm", intent.getStringExtra("Title"))

        //val notificationLayout = RemoteViews(packageName, R.layout.notification_alarm)
        notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(alarmTitle)
            .setSmallIcon(R.drawable.ic_bell)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            //.setCustomContentView(notificationLayout)
            //.setCustomBigContentView(notificationLayout)
            //.setPriority(NotificationCompat.PRIORITY_HIGH)
            //.setFullScreenIntent(pendingIntent, true)
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                vibrator.vibrate(VibrationEffect.createOneShot
                                                (30000, VibrationEffect.DEFAULT_AMPLITUDE))
        else
            vibrator.vibrate(30000)

        mediaPlayer.start()
        notificationIntent.flags = FLAG_ACTIVITY_NEW_TASK
        startForeground(1, notification)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(STOP_FOREGROUND_REMOVE)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // notificationManager.cancel(1)
         mediaPlayer.stop()
         vibrator.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}

/*
  //vibrator.vibrate(pattern, 0)
       // notification.flags = Notification.FLAG_AUTO_CANCEL
       // notificationManager.notify(0, notification)
       // startForeground(1, notification)

        //this.startActivity(notificationIntent)
      /*  val builder = AlertDialog.Builder(applicationContext)
        builder.setTitle("Android Alert")
        builder.setMessage("We have a message")
        val dialog: AlertDialog = builder.create()
        dialog.window!!.setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL)
        dialog.show*/
        //notificationManager.notify(0, notification)
 */