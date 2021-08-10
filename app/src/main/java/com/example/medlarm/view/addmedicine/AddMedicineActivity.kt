package com.example.medlarm.view.addmedicine

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.SearchManager
import android.app.TimePickerDialog
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivityAddMedicineBinding
import com.example.medlarm.view.common.BaseActivity
import com.example.medlarm.view.common.Medication
import com.simplemobiletools.commons.extensions.hideKeyboard
import com.simplemobiletools.commons.extensions.toast
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates


class AddMedicineActivity : BaseActivity<ActivityAddMedicineBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var addMedicineViewModel: AddMedicineViewModel

    private var gridLayoutManager: GridLayoutManager? = null
    private val medicationTypes = mutableListOf<Medication>()
    private var calender: Calendar = Calendar.getInstance()
    private val today: Long = Calendar.getInstance().timeInMillis
    var isStartDayToday: Boolean = false
    var startYear by Delegates.notNull<Int>()
    var startMonth by Delegates.notNull<Int>()
    var startDay by Delegates.notNull<Int>()

    override fun getViewBinding() = ActivityAddMedicineBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        addMedicineViewModel =
            ViewModelProvider(this, viewModelFactory).get(AddMedicineViewModel::class.java)
        gridLayoutManager = GridLayoutManager(this, 4)
        binding.rvMedications.layoutManager = gridLayoutManager

        val medication1 = Medication(getString(R.string.tablet), R.drawable.ic_tablet, false)
        val medication2 = Medication(getString(R.string.capsule), R.drawable.ic_capsule, false)
        val medication3 = Medication(getString(R.string.liquid), R.drawable.ic_cough_syrup, false)
        val medication4 = Medication(getString(R.string.drops), R.drawable.ic_drop, false)
        val medication5 = Medication(getString(R.string.injection), R.drawable.ic_drugs, false)
        val medication6 = Medication(getString(R.string.inhaler), R.drawable.ic_inhaler, false)
        val medication7 = Medication(getString(R.string.topical), R.drawable.ic_topical, false)
        val medication8 = Medication(getString(R.string.other), R.drawable.ic_suppository, false)

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

        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.item_label)
        val cursorAdapter = SimpleCursorAdapter(
            this, R.layout.medicine_name_item,
            null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )
        //val medication = arrayOf<String?>("Panadol", "Moov", "Decl")
        val newMedications = listOf("Panadol", "Moov", "Decl")
        //val adpater = CursorAdapter(this, null , R.layout.medicine_name_item)
        binding.svMedicationName.suggestionsAdapter = cursorAdapter

        binding.svMedicationName.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val cursor = MatrixCursor(
                    arrayOf(
                        BaseColumns._ID,
                        SearchManager.SUGGEST_COLUMN_TEXT_1
                    )
                )
                query?.let {
                    newMedications.forEachIndexed { index, medication ->
                        if (medication.contains(query, true))
                            cursor.addRow(arrayOf(index, medication))
                        Log.e("medication", medication)
                    }
                }
                cursorAdapter.changeCursor(cursor)
                return true
            }
        })

        binding.svMedicationName.setOnSuggestionListener(object :
            SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                hideKeyboard()
                val cursor = binding.svMedicationName.suggestionsAdapter.getItem(position) as Cursor
                val selection =
                    cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
                binding.svMedicationName.setQuery(selection, true)
                Log.e(
                    "selection",
                    cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1).toString()
                )
                // Do something with selection
                return true
            }
        })


        /*  val arrayAdapter: ArrayAdapter<Any?> =
              ArrayAdapter<Any?>(this, R.layout.medication_spinner_item, medication)
          arrayAdapter.setDropDownViewResource(R.layout.medication_spinner_item)
          binding.spMedicationName.adapter = arrayAdapter*/


        val startDateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

                if (calender.get(Calendar.YEAR) == year
                    && calender.get(Calendar.MONTH) == monthOfYear
                    && calender.get(Calendar.DAY_OF_MONTH) == dayOfMonth
                )
                    isStartDayToday = true
                startYear = year
                startMonth = monthOfYear
                startDay = dayOfMonth
                calender.set(Calendar.YEAR, year)
                calender.set(Calendar.MONTH, monthOfYear)
                calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }

        binding.ivStartCalender.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                startDateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.minDate = today
            datePickerDialog.show()
        }

        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            val currentDate: Calendar = Calendar.getInstance()
            calender.set(Calendar.HOUR_OF_DAY, hour)
            calender.set(Calendar.MINUTE, minute)

            if (isStartDayToday) {
                if (calender.timeInMillis >= currentDate.timeInMillis) {
                    //it's after current
                    // val hour = hour % 12
                    updateTimeInView()
                } else {
                    //it's before current'
                    Toast.makeText(applicationContext, "Invalid Time", Toast.LENGTH_LONG).show()
                }
            } else
                updateTimeInView()
        }

        binding.ivStartClock.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog,
                timeSetListener, calender.get(Calendar.HOUR_OF_DAY),
                calender.get(Calendar.MINUTE), true
            )
            timePickerDialog.show()
        }

        binding.tvIntakeUnit.setOnClickListener {
            lateinit var dialog: AlertDialog
            val choiceList = arrayOf("Daily", "weekly", "monthly", "sunday", "monday")
            val checkedList = booleanArrayOf(false, false, false, false, false)
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Choose")

            builder.setMultiChoiceItems(choiceList, checkedList) { _, which, isChecked ->
                // Update the clicked item checked status
                checkedList[which] = isChecked

                // Get the clicked item
                val color = choiceList[which]

                // Display the clicked item text
                toast("$color clicked.")
            }


            // Set the positive/yes button click listener
            builder.setPositiveButton("OK") { _, _ ->
                // Do something when click positive button
                choiceList.indices.forEach { i ->
                    val checked = checkedList[i]
                    if (checked) {
                        binding.tvIntakeUnit.text = choiceList[i]
                        // text_view.text = "${text_view.text}  ${arrayColors[i]} \n"
                    }
                }
            }


            // Initialize the AlertDialog using builder object
            dialog = builder.create()

            // Finally, display the alert dialog
            dialog.show()
        }

        /*  binding.btnAddAlarm.setOnClickListener {
              addMedicineViewModel.login(
                  binding.etUsername.text.toString(),
                  binding.etPassword.text.toString()
              )
          }*/
    }

    private fun selectMedication(medication: Medication) {
        binding.tvDoseType.text = medication.name
        if ((medication.name == "Tablet" || medication.name == "Capsule"
            || medication.name == "Liquid" || medication.name == "Drops")
            && medication.isChecked
        ) {
            binding.etDose.visibility = View.VISIBLE
            binding.tvDose.visibility = View.VISIBLE
            binding.tvDoseType.visibility = View.VISIBLE
        } else {
            binding.etDose.visibility = View.GONE
            binding.tvDose.visibility = View.GONE
            binding.tvDoseType.visibility = View.GONE
        }
    }

    private fun updateDateInView() {
        val format = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(format, Locale.US)
        binding.tvStartDate.text = sdf.format(calender.time)
    }

    private fun updateTimeInView() {
        val format = "HH:mm" // mention the format you need
        val sdf = SimpleDateFormat(format, Locale.US)
        binding.tvStartTime.text = sdf.format(calender.time)
    }
}

//import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
//import com.wdullaer.materialdatetimepicker.time.Timepoint

/*    val timePickerDialog = TimePickerDialog.newInstance(
                        timeSetListener,
                        calender.get(Calendar.HOUR_OF_DAY),
                        calender.get(Calendar.MINUTE),
                        false)
                //val now: Long = calender.timeInMillis
                timepoint = Timepoint(Calendar.HOUR_OF_DAY,Calendar.MINUTE,Calendar.SECOND)
                timePickerDialog.setMinTime(timepoint)
                timePickerDialog.show(supportFragmentManager, "TimePickerDialog")*/


/*   val timePickerDialog = ModifiedTimePicker(
                   this,
                   1,
                   timeSetListener,
                   calender.get(Calendar.HOUR_OF_DAY),
                   calender.get(Calendar.MINUTE),
                  false)
           //val now: Long = calender.timeInMillis
           timepoint = Timepoint(Calendar.HOUR_OF_DAY,Calendar.MINUTE,Calendar.SECOND)
           timePickerDialog.setMinTime(timepoint)
           timePickerDialog.show(supportFragmentManager, "TimePickerDialog")*/

/*
val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute,second ->
    calender.set(Calendar.HOUR_OF_DAY, hour)
    calender.set(Calendar.MINUTE, minute)
    calender.set(Calendar.SECOND,second)
    updateTimeInView()
}*/

//val currentDateTime = Calendar.getInstance().time
// timePickerDialog.updateTime()
//Log.e("hour",Calendar.getInstance().time.toString())
//Log.e("hour",SimpleDateFormat.getDateTimeInstance().format(currentDateTime))