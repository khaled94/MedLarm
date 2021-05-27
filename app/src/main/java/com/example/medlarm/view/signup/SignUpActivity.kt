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
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SignUpActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var signupBinding: ActivitySignupBinding
    private lateinit var gridLayoutManager: GridLayoutManager
    private val chronics = mutableListOf<Chronic>()
    private val selectedChronics = mutableListOf<Chronic>()
    private var calender: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)
        signUpViewModel = ViewModelProvider(this, viewModelFactory).get(SignUpViewModel::class.java)

        signupBinding.cbHaveChronics.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                signupBinding.rvChronics.visibility = View.VISIBLE
            else
                signupBinding.rvChronics.visibility = View.GONE
        }

        gridLayoutManager = GridLayoutManager(this, 4)
        signupBinding.rvChronics.layoutManager = gridLayoutManager

        val chronic1 = Chronic(getString(R.string.hypertension), R.drawable.ic_hypertension, false)
        val chronic2 = Chronic(getString(R.string.high_cholesterol), R.drawable.ic_high_cholesterol, false)
        val chronic3 = Chronic(getString(R.string.ischemic_heart), R.drawable.ic_ischemic_heart, false)
        val chronic4 = Chronic(getString(R.string.diabetes), R.drawable.ic_diabetes, false)
        val chronic5 = Chronic(getString(R.string.chronic_kidney), R.drawable.ic_chronic_kidney, false)
        val chronic6 = Chronic(getString(R.string.arthritis), R.drawable.ic_arthritis, false)
        val chronic7 = Chronic(getString(R.string.obstructive_pulmonary), R.drawable.ic_obstructive_pulmonary, false)
        val chronic8 = Chronic(getString(R.string.alzheimer), R.drawable.ic_alzheimer, false)
        val chronic9 = Chronic(getString(R.string.depression), R.drawable.ic_depression, false)
        val chronic10 = Chronic(getString(R.string.heart_failure), R.drawable.ic_heart_failure, false)

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

        signupBinding.rvChronics.adapter = SignUpAdapter(chronics) { chronic: Chronic ->
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
        signupBinding.ivCalender.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH))
            datePickerDialog.show()
        }

        signupBinding.btnSignUp.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        signupBinding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
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

    private fun updateDateInView() {
        val format = "dd/MM/yyyy" // mention the format you need
        val simpleDateFormat = SimpleDateFormat(format, Locale.US)
        signupBinding.tvDateOfBirth.text = simpleDateFormat.format(calender.time)
    }

}