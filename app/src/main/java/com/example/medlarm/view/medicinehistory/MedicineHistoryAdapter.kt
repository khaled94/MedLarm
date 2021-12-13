package com.example.medlarm.view.medicinehistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medlarm.R
import com.example.medlarm.data.model.responseModels.alarmbydate.AlarmByDateResponseItem
import com.example.medlarm.databinding.MedicineHistoryItemBinding


class MedicineHistoryAdapter(
    private val alarms: List<AlarmByDateResponseItem> = listOf()
) : RecyclerView.Adapter<MedicineHistoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineHistoryAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MedicineHistoryItemBinding.inflate(layoutInflater)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(viewHolder: MedicineHistoryAdapter.ViewHolder, position: Int) {
        viewHolder.bind(alarms[position])
    }

    override fun getItemCount() = alarms.size

    inner class ViewHolder(
        private val binding: MedicineHistoryItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(alarm: AlarmByDateResponseItem) {
            binding.tvMedicationName.text = alarm.MedicineName
            binding.tvMedicationTime.text = alarm.Time
            if(alarm.isTaken)
                   binding.ivTaken.setBackgroundResource(R.drawable.ic_check_mark)
            else
                binding.ivTaken.setBackgroundResource(R.drawable.ic_close)


        }
    }


}