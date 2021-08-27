package com.example.medlarm.view.signup

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivitySignupBinding
import com.example.medlarm.view.common.BaseActivity
import com.example.medlarm.view.common.Chronic
import com.example.medlarm.view.home.HomeActivity
import com.example.medlarm.view.login.LoginActivity
import com.simplemobiletools.commons.extensions.toInt
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SignUpActivity : BaseActivity<ActivitySignupBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getViewBinding() = ActivitySignupBinding.inflate(layoutInflater)

    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var gridLayoutManager: GridLayoutManager
    private val chronics = mutableListOf<Chronic>()
    private val selectedChronics = mutableListOf<Boolean>()
    private var calender: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signUpViewModel = ViewModelProvider(this, viewModelFactory).get(SignUpViewModel::class.java)

        binding.cbHaveChronics.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                binding.rvChronics.visibility = View.VISIBLE
            else
                binding.rvChronics.visibility = View.GONE
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
        /*    signUpViewModel.signUp(
                0,
                binding.etFirstName.toString(),
                binding.etLastName.toString(),
                binding.etEmail.toString(),
                binding.tvDateOfBirth.toString(),
                binding.etHeight.toInt(),
                binding.etWeight.toInt(),
                binding.etPassword.toInt(),
                binding.etConfirmPassword.toInt(),
                binding.,
                    IsHighCholesterol,
                    IsIschemicHeart,
                    IsDiabetes,
                    IsChronicKidney,
                    IsArthritis,
                    IsObstructivePulmonary,
                    IsAlzheimer,
                    IsDepression,
                    IsHeartFailure,
                    IsDeleted

            )*/
           /* val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()*/
        }

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

  /*  IsHighCholesterol, IsIschemicHeart,  IsDiabetes,IsChronicKidney, IsArthritis,
    IsObstructivePulmonary, IsAlzheimer,  IsDepression,  IsHeartFailure, IsDeleted */

    private fun selectChronic(chronic: Chronic) {
        if (chronic.isChecked){
            if(chronic.name == "Hypertension")
                selectedChronics[0] = true
            if(chronic.name == "HighCholesterol")
                selectedChronics[1] = true
            if(chronic.name == "Hypertension")
                selectedChronics[2] = true
            if(chronic.name == "Hypertension")
                selectedChronics[3] = true
            if(chronic.name == "Hpertension")
                selectedChronics[4] = true
            if(chronic.name == "Hypertension")
                selectedChronics[5] = true
            if(chronic.name == "Hypertension")
                selectedChronics[6] = true
            if(chronic.name == "Hypertension")
                selectedChronics[7] = true
        }

        else
            selectedChronics.remove(chronic)
    }

    private fun updateDateInView() {
        val format = "dd/MM/yyyy" // mention the format you need
        val simpleDateFormat = SimpleDateFormat(format, Locale.US)
        binding.tvDateOfBirth.text = simpleDateFormat.format(calender.time)
    }


}