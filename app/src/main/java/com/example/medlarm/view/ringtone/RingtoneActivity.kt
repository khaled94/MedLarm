package com.example.medlarm.view.ringtone

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivityRingtonesBinding
import com.example.medlarm.view.common.BaseActivity

class RingtoneActivity : BaseActivity<ActivityRingtonesBinding>() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private val rings = mutableListOf<Ringtone>()
    private var isAnotherMediaPlaying = false
    private  var mediaPlayer : MediaPlayer ? = null

    override fun getViewBinding() = ActivityRingtonesBinding.inflate(layoutInflater)

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRingtonesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        linearLayoutManager = LinearLayoutManager(this)
        binding.rvRings.layoutManager = linearLayoutManager

        val ringtone1 = Ringtone("alarm1", R.raw.alarm1)
        val ringtone2 = Ringtone("alarm2", R.raw.alarm2)
        val ringtone3 = Ringtone("alarm3", R.raw.alarm3)
        val ringtone4 = Ringtone("alarm4", R.raw.alarm4)
        val ringtone5 = Ringtone("alarm5", R.raw.alarm5)
        val ringtone6 = Ringtone("alarm6", R.raw.alarm6)
        val ringtone7 = Ringtone("alarm7", R.raw.alarm7)
        val ringtone8 = Ringtone("alarm8", R.raw.alarm8)

        rings.add(ringtone1)
        rings.add(ringtone2)
        rings.add(ringtone3)
        rings.add(ringtone4)
        rings.add(ringtone5)
        rings.add(ringtone6)
        rings.add(ringtone7)
        rings.add(ringtone8)

        when {
            preferenceManager.getRingName() == "alarm1" -> {
                ringtone1.isSelected = true
            }
            preferenceManager.getRingName() == "alarm2" -> {
                ringtone2.isSelected = true
            }
            preferenceManager.getRingName() == "alarm3" -> {
                ringtone3.isSelected = true
            }
            preferenceManager.getRingName() == "alarm4" -> {
                ringtone4.isSelected = true
            }
            preferenceManager.getRingName() == "alarm5" -> {
                ringtone5.isSelected = true
            }
            preferenceManager.getRingName() == "alarm6" -> {
                ringtone6.isSelected = true
            }
            preferenceManager.getRingName() == "alarm7" -> {
                ringtone7.isSelected = true
            }
            preferenceManager.getRingName() == "alarm8" -> {
                ringtone8.isSelected = true
            }
        }

        binding.rvRings.adapter =
            RingtoneAdapter(rings) { ring: Ringtone ->
                selectRingTone(ring)
            }
    }

    private fun selectRingTone(ring: Ringtone) {

        if(isAnotherMediaPlaying) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
        }

        if(ring.action == "ring"){
            if(!ring.isPlaying){
                mediaPlayer = MediaPlayer.create(this, ring.ring)
                isAnotherMediaPlaying = true
                mediaPlayer?.start()
                ring.isPlaying = true

            }
            else {
                mediaPlayer?.stop()
                mediaPlayer?.release()
                ring.isPlaying = false
            }
        }

        else{
            preferenceManager.setRingName(ring.name)
            Toast.makeText(applicationContext, "Alarm Selected", Toast.LENGTH_SHORT).show()
            ring.isPlaying = false
            this.finish()


        }

    }
}