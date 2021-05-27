package com.example.medlarm.view.userhistory

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.recyclerview.widget.RecyclerView
import com.example.medlarm.R
import com.example.medlarm.databinding.UserHistoryItemBinding
import com.example.medlarm.view.common.Alarm
import java.text.SimpleDateFormat
import java.util.*

class UserHistoryAdapter (private val alarms: List<Alarm> = listOf(),
private val selectAlarm: ((Alarm) -> Unit)? = null
) : RecyclerView.Adapter<UserHistoryAdapter.ViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHistoryAdapter.ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserHistoryItemBinding.inflate(layoutInflater)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(viewHolder: UserHistoryAdapter.ViewHolder, position: Int) {
        viewHolder.bind(alarms[position])
    }

    override fun getItemCount() = alarms.size

    inner class ViewHolder(
        private val binding: UserHistoryItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(alarm: Alarm) {

            val sdf = SimpleDateFormat("dd MMM yyyy")
            val startDate = sdf.format(alarm.startDate!!).toString()
            val endDate = sdf.format(alarm.endDate!!).toString()

            binding.tvMedicationName.text = alarm.name
            binding.tvMedicationDose.text = alarm.dose
            binding.tvStartDate.text =  startDate
            binding.tvEndDate.text = endDate

        }
    }
}