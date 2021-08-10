package com.example.medlarm.view.editmedicine

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivityEditMedicineBinding
import com.example.medlarm.view.addmedicine.AddMedicineAdapter
import com.example.medlarm.view.common.BaseActivity
import com.example.medlarm.view.common.Medication
import com.example.medlarm.view.home.HomeActivity
import javax.inject.Inject

class EditMedicineActivity : BaseActivity<ActivityEditMedicineBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var gridLayoutManager: GridLayoutManager? = null
    private val medicationTypes = mutableListOf<Medication>()

    override fun getViewBinding() = ActivityEditMedicineBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        gridLayoutManager = GridLayoutManager(this, 4)
        binding.rvMedications.layoutManager = gridLayoutManager

        val medication1 = Medication(getString(R.string.tablet), R.drawable.ic_tablet, false)
        val medication2 = Medication(getString(R.string.capsule), R.drawable.ic_capsule, false)
        val medication3 = Medication(getString(R.string.liquid), R.drawable.ic_cough_syrup, false)
        val medication4 = Medication(getString(R.string.drops), R.drawable.ic_drop, false)
        val medication5 = Medication(getString(R.string.injection), R.drawable.ic_drugs, false)
        val medication6 = Medication(getString(R.string.inhaler), R.drawable.ic_inhaler, false)
        val medication7 = Medication(
            getString(R.string.suppository),
            R.drawable.ic_suppository,
            false
        )
        val medication8 = Medication(getString(R.string.topical), R.drawable.ic_topical, false)

        medicationTypes.add(medication1)
        medicationTypes.add(medication2)
        medicationTypes.add(medication3)
        medicationTypes.add(medication4)
        medicationTypes.add(medication5)
        medicationTypes.add(medication6)
        medicationTypes.add(medication7)
        medicationTypes.add(medication8)

        binding.rvMedications.adapter =
            AddMedicineAdapter(medicationTypes) { medication: Medication ->
                selectMedication(medication)
            }

        binding.btnSaveChanges.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnCancel.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun selectMedication(medication: Medication) {
        binding.tvDoseType.text = medication.name
        if( medication.name == "Tablet" || medication.name == "Capsule"
            || medication.name == "Liquid" || medication.name == "Drops" ){
            binding.etDose.visibility = View.VISIBLE
            binding.tvDose.visibility = View.VISIBLE
            binding.tvDoseType.visibility = View.VISIBLE
        }
        else{
            binding.etDose.visibility = View.GONE
            binding.tvDose.visibility = View.GONE
            binding.tvDoseType.visibility = View.GONE
        }
    }
}