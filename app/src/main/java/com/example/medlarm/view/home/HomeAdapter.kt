package com.example.medlarm.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponseItem
import com.example.medlarm.databinding.AlarmItemBinding

class HomeAdapter(
    private val alarms: List<AlarmByDateResponseItem> = listOf(),
    private val selectAlarm: ((AlarmByDateResponseItem) -> Unit)? = null
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
        private val binding: AlarmItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(alarm: AlarmByDateResponseItem) {
            binding.tvMedicationName.text = alarm.MedicineName
            binding.tvMedicationNameBg.text = alarm.MedicineName
            binding.tvMedicationDose.text =
                (alarm.FrequentlyIntakeNo.toString() + " " +"times" + " " +alarm.FrequentlyIntakeType)
            binding.tvMedicationDoseBg.text =
                (alarm.FrequentlyIntakeNo.toString() + " " +"times" + " " +alarm.FrequentlyIntakeType)
            binding.tvTime.text = alarm.Time
            binding.tvTimeBg.text = alarm.Time

            itemView.setOnClickListener {
                if (alarm.showOptions) {
                    binding.foregroundView.visibility = View.VISIBLE
                    binding.ivMedicationType.visibility = View.VISIBLE
                    binding.backgroundView.visibility = View.INVISIBLE
                    alarm.showOptions = false
                } else {
                    binding.foregroundView.visibility = View.INVISIBLE
                    binding.ivMedicationTypeBg.visibility = View.INVISIBLE
                    binding.backgroundView.visibility = View.VISIBLE
                    alarm.showOptions = true
                }
            }

            binding.ivBgEdit.setOnClickListener {
                alarm.action = "edit"
                selectAlarm?.invoke(alarm)
            }

            binding.ivBgCalender.setOnClickListener {
                alarm.action = "history"
                selectAlarm?.invoke(alarm)
            }

            binding.ivBgDelete.setOnClickListener {
                alarm.action = "delete"
                selectAlarm?.invoke(alarm)
            }

        }
    }


}