package com.example.medlarm.view.editprofile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.medlarm.databinding.ChronicItemBinding
import com.example.medlarm.view.common.Chronic
import com.example.medlarm.R

class EditProfileAdapter(
    private val chronics: List<Chronic> = listOf(),
    private val selectChronic: ((Chronic) -> Unit)? = null
) : RecyclerView.Adapter<EditProfileAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EditProfileAdapter.ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ChronicItemBinding.inflate(layoutInflater)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(viewHolder: EditProfileAdapter.ViewHolder, position: Int) {
        viewHolder.bind(chronics[position])
    }

    override fun getItemCount() = chronics.size

    inner class ViewHolder(
        private val binding: ChronicItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chronic: Chronic) {
            binding.ivChronic.setImageResource(chronic.image)
            binding.tvChronicName.text = chronic.name
            if(chronic.isChecked)
                binding.ivChronic.setBackgroundResource(R.drawable.selected_chronicle)
            else
                binding.ivChronic.setBackgroundResource(R.drawable.unselected_chronicle)

            binding.ivChronic.setOnClickListener {
                if (chronic.isChecked) {
                    binding.ivChronic.setBackgroundResource(R.drawable.unselected_chronicle)
                    //binding.tvChronicName.setTextColor(context.resources.getColor(R.color.grey))
                    binding.tvChronicName.setTextColor(ContextCompat.getColor(context, R.color.grey))
                    chronic.isChecked = false
                    selectChronic?.invoke(chronic)
                } else {
                    binding.ivChronic.setBackgroundResource(R.drawable.selected_chronicle)
                    binding.tvChronicName.setTextColor(context.resources.getColor(R.color.colorPrimary))
                    chronic.isChecked = true
                    selectChronic?.invoke(chronic)
                }
            }
        }
    }
}