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
import android.os.Vibrator
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.medlarm.R
import com.example.medlarm.data.Constants.CHANNEL_ID
import com.example.medlarm.view.common.WindowView
import java.util.*


class AlarmService : Service() {

    // declaring variables
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager

    private lateinit var mediaPlayer : MediaPlayer
    private lateinit var vibrator: Vibrator

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val mView = WindowView(this)
        val chatHead = ImageView(this)
        chatHead.setImageResource(R.drawable.ic_alzheimer)

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT, 150, 10, 10,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )
        params.gravity = Gravity.CENTER
        params.title = "test"
        windowManager.addView(mView, params)

        mediaPlayer = MediaPlayer.create(this, R.raw.eraser)
        mediaPlayer.isLooping = true
        notificationManager =
                             getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("RemoteViewLayout")
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, RingActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0,
            notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmTitle = String.format("%s Alarm", intent.getStringExtra("Title"))

        val notificationLayout = RemoteViews(packageName, R.layout.notification_alarm)

            val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle(alarmTitle)
                    .setSmallIcon(R.drawable.ic_bell)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    //.setCustomContentView(notificationLayout)
                    //.setCustomBigContentView(notificationLayout)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setFullScreenIntent(pendingIntent, true)
                    .build()
            }
            else {
                TODO("VERSION.SDK_INT < O")
            }

        mediaPlayer.start()
        val pattern = longArrayOf(0, 100, 1000)
        //vibrator.vibrate(pattern, 0)
       // notification.flags = Notification.FLAG_AUTO_CANCEL
       // notificationManager.notify(0, notification)
       // startForeground(1, notification)
        notificationIntent.flags = FLAG_ACTIVITY_NEW_TASK
        //this.startActivity(notificationIntent)
      /*  val builder = AlertDialog.Builder(applicationContext)
        builder.setTitle("Android Alert")
        builder.setMessage("We have a message")
        val dialog: AlertDialog = builder.create()
        dialog.window!!.setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL)
        dialog.show*/
        notificationManager.notify(0, notification)

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationManager.cancel(1)
         mediaPlayer.stop()
 //       vibrator.cancel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showCustomPopupMenu() {

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}