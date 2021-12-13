package com.example.medlarm.view.ringtone

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivityRingtonesBinding
import com.example.medlarm.view.common.BaseActivity

class RingtoneActivity : BaseActivity<ActivityRingtonesBinding>() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private val rings = mutableListOf<Ringtone>()
    private var isAnotherMediaPlaying = false
    private lateinit var mediaPlayer : MediaPlayer

    override fun getViewBinding() = ActivityRingtonesBinding.inflate(layoutInflater)

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRingtonesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        linearLayoutManager = LinearLayoutManager(this)
        binding.rvRings.layoutManager = linearLayoutManager

        val ringtone1 = Ringtone("Ringtone 1 ", R.raw.alarm1)
        val ringtone2 = Ringtone("Ringtone 2 ", R.raw.alarm2)
        val ringtone3 = Ringtone("Ringtone 3 ", R.raw.alarm3)
        val ringtone4 = Ringtone("Ringtone 4 ", R.raw.alarm4)
        val ringtone5 = Ringtone("Ringtone 5 ", R.raw.alarm5)
        val ringtone6 = Ringtone("Ringtone 6 ", R.raw.alarm6)
        val ringtone7 = Ringtone("Ringtone 7 ", R.raw.alarm7)
        val ringtone8 = Ringtone("Ringtone 8 ", R.raw.alarm8)

        rings.add(ringtone1)
        rings.add(ringtone2)
        rings.add(ringtone3)
        rings.add(ringtone4)
        rings.add(ringtone5)
        rings.add(ringtone6)
        rings.add(ringtone7)
        rings.add(ringtone8)

        when {
            preferenceManager.getRingId() == R.raw.alarm1 -> {
                ringtone1.isSelected = true
            }
            preferenceManager.getRingId() == R.raw.alarm2 -> {
                ringtone1.isSelected = true
            }
            preferenceManager.getRingId() == R.raw.alarm3 -> {
                ringtone1.isSelected = true
            }
            preferenceManager.getRingId() == R.raw.alarm4 -> {
                ringtone1.isSelected = true
            }
            preferenceManager.getRingId() == R.raw.alarm5 -> {
                ringtone1.isSelected = true
            }
            preferenceManager.getRingId() == R.raw.alarm6 -> {
                ringtone1.isSelected = true
            }
            preferenceManager.getRingId() == R.raw.alarm7 -> {
                ringtone1.isSelected = true
            }
            preferenceManager.getRingId() == R.raw.alarm8 -> {
                ringtone1.isSelected = true
            }
        }

        binding.rvRings.adapter =
            RingtoneAdapter(rings) { ring: Ringtone ->
                selectRingTone(ring)
            }
    }

    private fun selectRingTone(ring: Ringtone) {
        if(ring.action == "ring"){
            if(!ring.isPlaying){
                 if(isAnotherMediaPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.release()
                }
                mediaPlayer = MediaPlayer.create(this, ring.ring)
                isAnotherMediaPlaying = true
                mediaPlayer.start()
                ring.isPlaying = true

            }
            else {
                mediaPlayer.stop()
                mediaPlayer.release()
                ring.isPlaying = false
            }
        }
        else{
            preferenceManager.setRing(ring.ring)
            Toast.makeText(applicationContext, "Selected", Toast.LENGTH_LONG).show()
            this.finish()
            mediaPlayer.stop()
            mediaPlayer.release()
            ring.isPlaying = false

        }

    }
}