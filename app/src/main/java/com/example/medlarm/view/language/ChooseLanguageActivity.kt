package com.example.medlarm.view.language

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivityChooseLanguageBinding
import com.example.medlarm.utils.LocaleManager
import com.example.medlarm.view.common.BaseActivity
import kotlin.properties.Delegates


class ChooseLanguageActivity : BaseActivity<ActivityChooseLanguageBinding>() {

    private var typeCheckedItem by Delegates.notNull<Int>()
    lateinit var currentLanguage : String
     private var newLanguage = ""
    //var currentSystemLocaleCode = ConfigurationCompat.getLocales(Resources.getSystem().configuration).get(0).language

    override fun getViewBinding() = ActivityChooseLanguageBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        currentLanguage =
            if(preferenceManager.getCurrentLocale() == "ar"){
                binding.rgChooseLanguage.check(R.id.rb_second_language)
            "ar"
        } else{
                binding.rgChooseLanguage.check(R.id.rb_first_language)
            "en"
        }

        binding.btnConfirm.setOnClickListener {
            if( newLanguage != currentLanguage ){
                currentLanguage = newLanguage
                preferenceManager.setCurrentLocale(currentLanguage)
                updateAppLocale(newLanguage)
            }
        }
    }

    private fun updateAppLocale(locale: String) {
        preferenceManager.setCurrentLocale(locale)
        LocaleManager.applyLocalizedContext(applicationContext, locale)
        recreate()
    }

    fun onLanguageChecked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.rb_first_language ->
                    if (checked && currentLanguage == "ar")
                                                   newLanguage = "en"
                R.id.rb_second_language ->
                    if (checked && currentLanguage == "en")
                                                   newLanguage = "ar"
            }
        }
    }
}

/*
        languageBinding.tvChooseLanguage.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Choose")
            val statusList = arrayOf("English", "Arabic")

           builder.setSingleChoiceItems(statusList, typeCheckedItem) { _, choice ->
               //  user checked an item
               if( typeCheckedItem != choice ){
                   typeCheckedItem = choice
                   changeLanguage = true
               }
               chosenLanguage = if( choice == 0) "en" else "ar"
            }

            // add OK and Cancel buttons
            builder.setPositiveButton("OK") { _, _ ->
              if(changeLanguage){
                  updateAppLocale(chosenLanguage)
              }
            }
            // builder.setNegativeButton("Cancel", null)
            // create and show the alert dialog
            val dialog = builder.create()
            dialog.show()
        }*/

/* //LocaleManagerMew.setLocale(this@LoginCustomerFragment.activity?.applicationContext)
      var mCurrentLanguage = LocaleManagerMew.getCurrentLanguage(this@LoginCustomerFragment.activity?.applicationContext)
      if (mCurrentLanguage == LocaleManagerMew.mArabicFlag) {
          LocaleManagerMew.setNewLocale(this@LoginCustomerFragment.context!!, LocaleManagerMew.mEnglishFlag)
      } else if (mCurrentLanguage == LocaleManagerMew.mEnglishFlag) {
          LocaleManagerMew.setNewLocale(this@LoginCustomerFragment.context!!, LocaleManagerMew.mArabicFlag)
      }
      activity?.recreate()
}*/

/*  Log.e("prefernce",preferenceManager.getCurrentLocale())
      Log.e("locale", Locale(currentSystemLocaleCode).displayLanguage)
      Log.e("locale", LocaleManager.getLocaleFromPrefCode("en").toString())*/

/*   if(preferenceManager.getPreferredLocale() == LocaleManager.OPTION_PHONE_LANGUAGE){
       if(currentSystemLocaleCode in LocaleManager.supportedLocales){
           //tvAppLocale.text = getString(R.string.phone_language, Locale(currentSystemLocaleCode).displayLanguage)
       } else {
           //current system language is neither English nor my second language (for me Bangla)
           //tvAppLocale.text = "English"
       }*/

/*  Log.e("prefernce",preferenceManager.getCurrentLocale())
      Log.e("locale", Locale(currentSystemLocaleCode).displayLanguage)
      Log.e("locale", LocaleManager.getLocaleFromPrefCode("en").toString())*/

/*   if(preferenceManager.getPreferredLocale() == LocaleManager.OPTION_PHONE_LANGUAGE){
       if(currentSystemLocaleCode in LocaleManager.supportedLocales){
           //tvAppLocale.text = getString(R.string.phone_language, Locale(currentSystemLocaleCode).displayLanguage)
       } else {
           //current system language is neither English nor my second language (for me Bangla)
           //tvAppLocale.text = "English"
       }*/
