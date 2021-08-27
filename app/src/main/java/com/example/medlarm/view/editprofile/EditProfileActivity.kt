package com.example.medlarm.view.editprofile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivityEditProfileBinding
import com.example.medlarm.view.common.BaseActivity
import com.example.medlarm.view.common.Chronic
import com.example.medlarm.view.settings.SettingsActivity
import java.util.*
import javax.inject.Inject

class EditProfileActivity: BaseActivity<ActivityEditProfileBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var editProfileViewModel: EditProfileViewModel
    lateinit var gridLayoutManager: GridLayoutManager
    private val chronics = mutableListOf<Chronic>()
    private val selectedChronics = mutableListOf<Chronic>()
    private var calender: Calendar = Calendar.getInstance()

    override fun getViewBinding() = ActivityEditProfileBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        editProfileViewModel = ViewModelProvider(this, viewModelFactory).get(EditProfileViewModel::class.java)

        binding.ivAddChronic.setOnClickListener {
                binding.rvChronics.visibility = View.VISIBLE
        }

        gridLayoutManager = GridLayoutManager(this, 4)
        binding.rvChronics.layoutManager = gridLayoutManager

        val chronic1 = Chronic(getString(R.string.hypertension), R.drawable.ic_hypertension, false)
        val chronic2 = Chronic(
            getString(R.string.chronic_obstructive_pulmonary_disease),
            R.drawable.ic_high_cholesterol,
            false
        )
        val chronic3 = Chronic(getString(R.string.cancer), R.drawable.ic_ischemic_heart, false)
        val chronic4 = Chronic(getString(R.string.diabetes), R.drawable.ic_diabetes, false)
        val chronic5 =
            Chronic(getString(R.string.kidney_disease), R.drawable.ic_chronic_kidney, false)
        val chronic6 = Chronic(getString(R.string.arthritis), R.drawable.ic_arthritis, false)
        val chronic7 =
            Chronic(getString(R.string.liver_disease), R.drawable.ic_obstructive_pulmonary, false)
        val chronic8 = Chronic(getString(R.string.asthma), R.drawable.ic_inhaler, false)
        val chronic9 = Chronic(getString(R.string.depression), R.drawable.ic_depression, false)
        val chronic10 =
            Chronic(getString(R.string.heart_disease), R.drawable.ic_heart_disease, false)
        val chronic11 = Chronic(getString(R.string.osteoporosis), R.drawable.ic_suppository, false)
        val chronic12 = Chronic(getString(R.string.other), R.drawable.ic_suppository, false)

        chronics.add(chronic1)
        chronics.add(chronic2)
        chronics.add(chronic3)
        chronics.add(chronic4)
        chronics.add(chronic5)
        chronics.add(chronic6)
        chronics.add(chronic7)
        chronics.add(chronic8)
        chronics.add(chronic9)
        chronics.add(chronic10)
        chronics.add(chronic11)
        chronics.add(chronic12)

        binding.rvChronics.adapter = EditProfileAdapter(chronics) { chronic: Chronic ->
            selectChronic(chronic)
        }

        binding.btnSaveChanges.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnCancel.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun selectChronic(chronic: Chronic) {
        if (chronic.isChecked)
            selectedChronics.add(chronic)
        else
            selectedChronics.remove(chronic)
    }

}