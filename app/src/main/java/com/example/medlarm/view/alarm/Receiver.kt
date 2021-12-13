package com.example.medlarm.view.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import java.util.*

class Receiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        /*val intent = Intent(context, RingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context?.startActivity(intent)*/
        val ringId = intent?.getIntExtra("Ringtone",1)
        Log.e("RingId from reciver",ringId.toString())
        val intentService = Intent(context, AlarmService::class.java)
        intentService.putExtra("Ringtone", ringId)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context?.startForegroundService(intentService)
        } else {
            context?.startService(intentService)
        }
        

    }
}

