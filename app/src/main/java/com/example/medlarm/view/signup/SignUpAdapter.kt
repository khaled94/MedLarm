package com.example.medlarm.view.signup

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medlarm.databinding.ChronicItemBinding
import com.example.medlarm.view.common.Chronic
import com.example.medlarm.R

class SignUpAdapter(
    private val chronics: List<Chronic> = listOf(),
    private val selectChronic: ((Chronic) -> Unit)? = null
) : RecyclerView.Adapter<SignUpAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignUpAdapter.ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ChronicItemBinding.inflate(layoutInflater)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(viewHolder: SignUpAdapter.ViewHolder, position: Int) {
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
            binding.ivChronic.setOnClickListener {
                if (chronic.isChecked) {
                    binding.ivChronic.setBackgroundResource(R.drawable.unselected_chronicle)
                    binding.tvChronicName.setTextColor(context.resources.getColor(R.color.grey))
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