package com.example.medlarm.view.addmedicine

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medlarm.R
import com.example.medlarm.databinding.MedicationItemBinding
import com.example.medlarm.view.common.Medication
import kotlinx.android.synthetic.main.medication_item.view.*

class AddMedicineAdapter(
        private val medications: List<Medication> = listOf(),
        private val selectMedication: ((Medication) -> Unit)? = null
) : RecyclerView.Adapter<AddMedicineAdapter.ViewHolder>(){
    lateinit var context: Context
    var medicationViewList: MutableList<View> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddMedicineAdapter.ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MedicationItemBinding.inflate(layoutInflater)
        return ViewHolder(
                binding
        )
    }

    override fun onBindViewHolder(viewHolder: AddMedicineAdapter.ViewHolder, position: Int) {
        viewHolder.bind(medications[position])
        medicationViewList.add(viewHolder.itemView.iv_medication)

    }

    override fun getItemCount() = medications.size

    inner class ViewHolder(
            private val binding: MedicationItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(medication: Medication) {
            binding.ivMedication.setImageResource(medication.image)
            binding.tvMedicationName.text = medication.name
            binding.ivMedication.setOnClickListener{
                if(medication.isChecked){
                    binding.ivMedication.setBackgroundResource(R.drawable.unselected_medication)
                    medication.isChecked = false
                    selectMedication?.invoke(medication)
                }
                else {
                    for ( view in medicationViewList) {
                        view.setBackgroundResource(R.drawable.unselected_medication)
                    }
                    binding.ivMedication.setBackgroundResource(R.drawable.selected_medication)
                    medication.isChecked = true
                    selectMedication?.invoke(medication)
                }
            }
        }
    }
}