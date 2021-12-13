package com.example.medlarm.view.editprofile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivityEditProfileBinding
import com.example.medlarm.utils.ErrorEntity
import com.example.medlarm.utils.State
import com.example.medlarm.view.common.BaseActivity
import com.example.medlarm.view.common.Chronic
import com.example.medlarm.view.home.HomeActivity
import com.example.medlarm.view.settings.SettingsActivity
import com.simplemobiletools.commons.extensions.toInt
import java.util.*
import javax.inject.Inject

class EditProfileActivity : BaseActivity<ActivityEditProfileBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var editProfileViewModel: EditProfileViewModel
    private lateinit var gridLayoutManager: GridLayoutManager
    private val chronics = mutableListOf<Chronic>()
    private val selectedChronics = mutableListOf<Boolean>()
    private var calender: Calendar = Calendar.getInstance()
    var IsGridVisible = false
    var dateOfBirth = ""
    var password = ""

    override fun getViewBinding() = ActivityEditProfileBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        editProfileViewModel =
            ViewModelProvider(this, viewModelFactory).get(EditProfileViewModel::class.java)

        editProfileViewModel.getUserDate(preferenceManager.getUserId())
        observeOnCurrentData()

        binding.ivAddChronic.setOnClickListener {
            if (IsGridVisible){
                binding.rvChronics.visibility = View.GONE
                IsGridVisible = false
            }

            else{
                binding.rvChronics.visibility = View.VISIBLE
                IsGridVisible = true
            }
        }


        gridLayoutManager = GridLayoutManager(this, 4)
        binding.rvChronics.layoutManager = gridLayoutManager

        binding.root.setOnClickListener {
            // it hide soft keyboard on edit text outside root layout click
            hideSoftKeyboard()

            // remove focus from edit text
            binding.etFirstName.clearFocus()
            binding.etHeight.clearFocus()
            binding.etLastName.clearFocus()
            binding.etWeight.clearFocus()
            binding.etEmail.clearFocus()
        }

        binding.btnSaveChanges.setOnClickListener {
                if (checkEmpty()){
                    editProfileViewModel.editProfile(
                        preferenceManager.getUserId(),
                        binding.etFirstName.text.toString(),
                        binding.etLastName.text.toString(),
                        binding.etEmail.text.toString(),
                        dateOfBirth,
                        binding.etHeight.text.toString().toDouble(),
                        binding.etWeight.text.toString().toDouble(),
                        password,
                        chronics[0].isChecked,
                        chronics[1].isChecked,
                        chronics[2].isChecked,
                        chronics[3].isChecked,
                        chronics[4].isChecked,
                        chronics[5].isChecked,
                        chronics[6].isChecked,
                        chronics[7].isChecked,
                        chronics[8].isChecked,
                        chronics[9].isChecked,
                        chronics[10].isChecked,
                        chronics[11].isChecked,
                        false
                    )
                    observeOnUser()
                }
                else{
                    Toast.makeText(
                        this,
                        getString(R.string.empty_data),
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

        binding.btnCancel.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun observeOnCurrentData() {
        editProfileViewModel.userProfile.observe(this, {
            when (it) {
                is State.Loading -> {
                    dialog.show()
                }
                is State.Success -> {
                    dialog.dismiss()
                    if (it.data.Id != -1) {
                        preferenceManager.setUserId(userId = it.data.Id)
                        binding.etFirstName.setText(it.data.Fname)
                        binding.etLastName.setText(it.data.Lname)
                        binding.etEmail.setText(it.data.Email)
                        dateOfBirth = it.data.DateOfBirth
                        password = it.data.Password
                        //binding.tvDateOfBirth.setText(it.data.DateOfBirth)
                        binding.etHeight.setText(it.data.Height.toString())
                        binding.etWeight.setText(it.data.Weight.toString())

                        val chronic1 = Chronic(
                            getString(R.string.hypertension),
                            R.drawable.ic_hypertension,
                            it.data.IsHypertension
                        )
                        val chronic2 = Chronic(
                            getString(R.string.chronic_obstructive_pulmonary_disease),
                            R.drawable.ic_high_cholesterol,
                            it.data.IsChronicObtructivePulmonaryDisease
                        )
                        val chronic3 = Chronic(
                            getString(R.string.cancer),
                            R.drawable.ic_ischemic_heart,
                            it.data.IsCancer
                        )
                        val chronic4 = Chronic(
                            getString(R.string.diabetes),
                            R.drawable.ic_diabetes,
                            it.data.IsDiabetes
                        )
                        val chronic5 = Chronic(
                            getString(R.string.kidney_disease),
                            R.drawable.ic_chronic_kidney,
                            it.data.IsKidneyDisease
                        )
                        val chronic6 = Chronic(
                            getString(R.string.arthritis),
                            R.drawable.ic_arthritis,
                            it.data.IsArthritis
                        )
                        val chronic7 = Chronic(
                            getString(R.string.liver_disease),
                            R.drawable.ic_obstructive_pulmonary,
                            it.data.IsLiverDisease
                        )
                        val chronic8 = Chronic(
                            getString(R.string.asthma),
                            R.drawable.ic_inhaler,
                            it.data.IsAsthma
                        )
                        val chronic9 = Chronic(
                            getString(R.string.depression),
                            R.drawable.ic_depression,
                            it.data.IsDepression
                        )
                        val chronic10 = Chronic(
                            getString(R.string.heart_disease),
                            R.drawable.ic_heart_disease,
                            it.data.IsHeartDisease
                        )
                        val chronic11 = Chronic(
                            getString(R.string.osteoporosis),
                            R.drawable.ic_suppository,
                            it.data.IsOsteoporosis
                        )
                        val chronic12 = Chronic(
                            getString(R.string.other),
                            R.drawable.ic_suppository,
                            it.data.IsOther
                        )

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

                        binding.rvChronics.adapter =
                            EditProfileAdapter(chronics) { chronic: Chronic ->
                                selectChronic(chronic)
                            }


                        if (it.data.IsHypertension)
                            chronics[0].isChecked = true
                        if (it.data.IsDiabetes)
                            chronics[0].isChecked = true
                        if (it.data.IsHeartDisease)
                            chronics[0].isChecked = true
                        if (it.data.IsKidneyDisease)
                            chronics[0].isChecked = true
                        if (it.data.IsLiverDisease)
                            chronics[0].isChecked = true
                        if (it.data.IsAsthma)
                            chronics[0].isChecked = true
                        if (it.data.IsChronicObtructivePulmonaryDisease)
                            chronics[0].isChecked = true
                        if (it.data.IsArthritis)
                            chronics[0].isChecked = true
                        if (it.data.IsOsteoporosis)
                            chronics[0].isChecked = true
                        if (it.data.IsCancer)
                            chronics[0].isChecked = true
                        if (it.data.IsAlzheimer)
                            chronics[0].isChecked = true
                        if (it.data.IsDepression)
                            chronics[0].isChecked = true

                    } else {
                        Toast.makeText(
                            this,
                            getString(R.string.network_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is State.Error -> {
                    dialog.dismiss()
                    when (it.exception) {
                        is ErrorEntity.NetworkError -> {

                        }
                        is ErrorEntity.AccessDenied -> {

                        }
                        is ErrorEntity.BadRequest -> {

                        }
                        is ErrorEntity.NotFound -> {

                        }
                        is ErrorEntity.ServiceUnavailable -> {

                        }
                        is ErrorEntity.UnKnownError -> {

                        }
                    }
                    Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun observeOnUser() {
        editProfileViewModel.user.observe(this, {
            when (it) {
                is State.Loading -> {
                    dialog.show()
                }
                is State.Success -> {
                    dialog.dismiss()
                    if (it.data.Status == "Done") {
                        preferenceManager.setUserId(userId = it.data.userResponseData.Id)
                        val intent = Intent(this, SettingsActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            getString(R.string.network_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is State.Error -> {
                    dialog.dismiss()
                    when (it.exception) {
                        is ErrorEntity.NetworkError -> {

                        }
                        is ErrorEntity.AccessDenied -> {

                        }
                        is ErrorEntity.BadRequest -> {

                        }
                        is ErrorEntity.NotFound -> {

                        }
                        is ErrorEntity.ServiceUnavailable -> {

                        }
                        is ErrorEntity.UnKnownError -> {

                        }
                    }
                    Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun selectChronic(chronic: Chronic) {
        if (chronic.isChecked) {
            if (chronic.name == "Hypertension")
                chronics[0].isChecked = true
            if (chronic.name == "Diabetes")
                chronics[1].isChecked = true
            if (chronic.name == "HeartDisease")
                chronics[2].isChecked = true
            if (chronic.name == "KidneyDisease")
                chronics[3].isChecked = true
            if (chronic.name == "LiverDisease")
                chronics[4].isChecked = true
            if (chronic.name == "Asthma")
                chronics[5].isChecked = true
            if (chronic.name == "Chronic Obstructive Pulmonary Disease")
                chronics[6].isChecked = true
            if (chronic.name == "Arthritis")
                chronics[7].isChecked = true
            if (chronic.name == "Osteoporosis")
                chronics[8].isChecked = true
            if (chronic.name == "Cancer")
                chronics[9].isChecked = true
            if (chronic.name == "Alzheimer")
                chronics[10].isChecked = true
            if (chronic.name == "Other")
                chronics[11].isChecked = true
        } else {
            if (chronic.name == "Hypertension")
                selectedChronics[0] = false
            if (chronic.name == "Diabetes")
                selectedChronics[1] = false
            if (chronic.name == "HeartDisease")
                selectedChronics[2] = false
            if (chronic.name == "KidneyDisease")
                selectedChronics[3] = false
            if (chronic.name == "LiverDisease")
                selectedChronics[4] = false
            if (chronic.name == "Asthma")
                selectedChronics[5] = false
            if (chronic.name == "Chronic Obstructive Pulmonary Disease")
                selectedChronics[6] = false
            if (chronic.name == "Arthritis")
                selectedChronics[7] = false
            if (chronic.name == "Osteoporosis")
                selectedChronics[8] = false
            if (chronic.name == "Cancer")
                selectedChronics[9] = false
            if (chronic.name == "Alzheimer")
                selectedChronics[10] = false
            if (chronic.name == "Other")
                selectedChronics[11] = false
        }
    }

    private fun checkEmpty(): Boolean {
        if (binding.etFirstName.text.trim().isNotEmpty() &&
            binding.etLastName.text.trim().isNotEmpty() &&
            binding.etEmail.text.trim().isNotEmpty()
            )
            return true
        return false
    }

}