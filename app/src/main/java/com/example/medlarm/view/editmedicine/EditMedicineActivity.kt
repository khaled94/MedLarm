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

class EditMedicineActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var editMedicineBinding: ActivityEditMedicineBinding
    private var gridLayoutManager: GridLayoutManager? = null
    private val medicationTypes = mutableListOf<Medication>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editMedicineBinding = ActivityEditMedicineBinding.inflate(layoutInflater)
        setContentView(editMedicineBinding.root)

        gridLayoutManager = GridLayoutManager(this, 4)
        editMedicineBinding.rvMedications.layoutManager = gridLayoutManager

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

        editMedicineBinding.rvMedications.adapter =
            AddMedicineAdapter(medicationTypes) { medication: Medication ->
                selectMedication(medication)
            }

        editMedicineBinding.btnSaveChanges.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        editMedicineBinding.btnCancel.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun selectMedication(medication: Medication) {
        editMedicineBinding.tvDoseType.text = medication.name
        if( medication.name == "Tablet" || medication.name == "Capsule"
            || medication.name == "Liquid" || medication.name == "Drops" ){
            editMedicineBinding.etDose.visibility = View.VISIBLE
            editMedicineBinding.tvDose.visibility = View.VISIBLE
            editMedicineBinding.tvDoseType.visibility = View.VISIBLE
        }
        else{
            editMedicineBinding.etDose.visibility = View.GONE
            editMedicineBinding.tvDose.visibility = View.GONE
            editMedicineBinding.tvDoseType.visibility = View.GONE
        }
    }
}