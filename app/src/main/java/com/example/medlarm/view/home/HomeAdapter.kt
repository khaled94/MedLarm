package com.example.medlarm.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medlarm.databinding.AlarmItemBinding
import com.example.medlarm.view.common.Alarm
import kotlinx.android.synthetic.main.alarm_item.view.*

class HomeAdapter(private val alarms: List<Alarm> = listOf(),
                  private val selectAlarm: ((Alarm) -> Unit)? = null
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AlarmItemBinding.inflate(layoutInflater)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(viewHolder: HomeAdapter.ViewHolder, position: Int) {
        viewHolder.bind(alarms[position])
    }

    override fun getItemCount() = alarms.size

    inner class ViewHolder(
        private val binding: AlarmItemBinding ) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(alarm: Alarm) {
                  binding.tvMedicationName.text = alarm.name
                  binding.tvMedicationNameBg.text = alarm.name
                  binding.tvMedicationDose.text = alarm.dose
                  binding.tvMedicationDoseBg.text = alarm.dose
                  binding.tvTime.text = "9:30"
                  binding.tvTimeBg.text ="9:30"

                    itemView.setOnClickListener {
                        if(alarm.showOptions!!){
                            binding.foregroundView.visibility = View.VISIBLE
                            binding.backgroundView.visibility = View.INVISIBLE
                            alarm.showOptions = false
                        }
                        else {
                            binding.foregroundView.visibility = View.INVISIBLE
                            binding.backgroundView.visibility = View.VISIBLE
                            alarm.showOptions = true
                        }
                    }

                    itemView.iv_bg_edit.setOnClickListener {
                        alarm.action = "edit"
                        selectAlarm?.invoke(alarm)
                    }

                    itemView.iv_bg_calender.setOnClickListener {
                        alarm.action = "history"
                        selectAlarm?.invoke(alarm)
                    }

                    itemView.iv_bg_delete.setOnClickListener {
                       alarm.action="delete"
                       selectAlarm?.invoke(alarm)
                    }

                }
            }



}