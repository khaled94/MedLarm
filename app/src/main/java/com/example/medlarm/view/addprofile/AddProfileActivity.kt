package com.example.medlarm.view.addprofile

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivityAddProfileBinding
import com.example.medlarm.utils.ErrorEntity
import com.example.medlarm.utils.State
import com.example.medlarm.view.common.BaseActivity
import com.example.medlarm.view.common.Chronic
import com.example.medlarm.view.home.HomeActivity
import com.example.medlarm.view.signup.SignUpAdapter
import com.example.medlarm.view.signup.SignUpViewModel
import com.simplemobiletools.commons.extensions.toInt
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AddProfileActivity : BaseActivity<ActivityAddProfileBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getViewBinding() = ActivityAddProfileBinding.inflate(layoutInflater)

    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var gridLayoutManager: GridLayoutManager
    private val chronics = mutableListOf<Chronic>()
    private val selectedChronics = mutableListOf<Boolean>()
    private var calender: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signUpViewModel = ViewModelProvider(this, viewModelFactory).get(SignUpViewModel::class.java)

        binding.root.setOnClickListener {
            // it hide soft keyboard on edit text outside root layout click
            hideSoftKeyboard()

            // remove focus from edit text
            binding.etFirstName.clearFocus()
            binding.etConfirmPassword.clearFocus()
            binding.etHeight.clearFocus()
            binding.etLastName.clearFocus()
            binding.etWeight.clearFocus()
            binding.etEmail.clearFocus()
            binding.etPassword.clearFocus()
        }

        binding.cbHaveChronics.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                binding.rvChronics.visibility = View.VISIBLE
            else
                binding.rvChronics.visibility = View.GONE
        }

        gridLayoutManager = GridLayoutManager(this, 4)
        binding.rvChronics.layoutManager = gridLayoutManager

        val chronic1 = Chronic(getString(R.string.hypertension), R.drawable.ic_hypertension, false)
        val chronic2 = Chronic(getString(R.string.diabetes), R.drawable.ic_diabetes, false)
        val chronic3 =
            Chronic(getString(R.string.heart_disease), R.drawable.ic_heart_disease, false)
        val chronic4 =
            Chronic(getString(R.string.kidney_disease), R.drawable.ic_chronic_kidney, false)
        val chronic5 =
            Chronic(getString(R.string.liver_disease), R.drawable.ic_obstructive_pulmonary, false)
        val chronic6 = Chronic(getString(R.string.asthma), R.drawable.ic_inhaler, false)
        val chronic7 = Chronic(
            getString(R.string.chronic_obstructive_pulmonary_disease),
            R.drawable.ic_high_cholesterol,
            false
        )
        val chronic8 = Chronic(getString(R.string.arthritis), R.drawable.ic_arthritis, false)
        val chronic9 = Chronic(getString(R.string.osteoporosis), R.drawable.ic_suppository, false)
        val chronic10 = Chronic(getString(R.string.cancer), R.drawable.ic_ischemic_heart, false)
        val chronic11 = Chronic(getString(R.string.depression), R.drawable.ic_depression, false)
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


        binding.rvChronics.adapter = SignUpAdapter(chronics) { chronic: Chronic ->
            selectChronic(chronic)
        }

        // create an OnDateSetListener
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calender.set(Calendar.YEAR, year)
                calender.set(Calendar.MONTH, monthOfYear)
                calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        binding.ivCalender.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        binding.btnSignUp.setOnClickListener {
            if (checkEmpty()){
                signUpViewModel.signUp(
                    0,
                    binding.etFirstName.text.toString(),
                    binding.etLastName.text.toString(),
                    binding.etEmail.text.toString(),
                    binding.tvDateOfBirth.text.toString(),
                    binding.etHeight.text.toInt(),
                    binding.etWeight.text.toInt(),
                    binding.etPassword.text.toString(),
                    binding.etConfirmPassword.text.toString(),
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
    }

    private fun observeOnUser() {
        signUpViewModel.user.observe(this, {
            when (it) {
                is State.Loading -> {
                    dialog.show()
                }
                is State.Success -> {
                    dialog.dismiss()
                    if (it.data.Status == "Done") {
                        preferenceManager.setUserId(userId = it.data.userResponseData.Id)
                        val intent = Intent(this, HomeActivity::class.java)
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

    private fun updateDateInView() {
        val format = "dd/MM/yyyy" // mention the format you need
        val simpleDateFormat = SimpleDateFormat(format, Locale.US)
        binding.tvDateOfBirth.text = simpleDateFormat.format(calender.time)
    }

    private fun checkEmpty(): Boolean {
        if (binding.etFirstName.text.trim().isNotEmpty() &&
            binding.etLastName.text.trim().isNotEmpty() &&
            binding.etEmail.text.trim().isNotEmpty() &&
            binding.etPassword.text.trim().isNotEmpty() &&
            binding.etConfirmPassword.text.trim().isNotEmpty()
        )
            return true
        return false
    }
}