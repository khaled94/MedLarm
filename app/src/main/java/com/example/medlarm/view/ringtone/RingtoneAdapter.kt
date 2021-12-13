package com.example.medlarm.view.ringtone

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medlarm.databinding.RingtoneItemBinding
import com.example.medlarm.view.common.Alarm
import java.text.SimpleDateFormat

class RingtoneAdapter(private val rings: List<Ringtone> = listOf(),
private val selectRing: ((Ringtone) -> Unit)? = null
) : RecyclerView.Adapter<RingtoneAdapter.ViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RingtoneAdapter.ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RingtoneItemBinding.inflate(layoutInflater)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(viewHolder: RingtoneAdapter.ViewHolder, position: Int) {
        viewHolder.bind(rings[position])
    }

    override fun getItemCount() = rings.size

    inner class ViewHolder(
        private val binding: RingtoneItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(ring: Ringtone) {
            binding.tvRingName.text = ring.name

            if(ring.isSelected)
                          binding.tvSelect.visibility = View.INVISIBLE

            binding.ivRing.setOnClickListener {
                ring.action = "ring"
                selectRing?.invoke(ring)
            }
            binding.tvSelect.setOnClickListener {
                ring.action = "select"
                selectRing?.invoke(ring)
                ring.isSelected = true
                binding.tvSelect.visibility = View.INVISIBLE
            }

        }
    }
}