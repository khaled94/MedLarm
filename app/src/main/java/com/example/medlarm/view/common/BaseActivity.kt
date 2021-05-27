package com.example.medlarm.view.common

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.medlarm.app.MedLarm
import com.example.medlarm.utils.LocaleManager
import com.example.medlarm.utils.PreferenceManager
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity : DaggerAppCompatActivity() {

    private lateinit var oldPrefLocaleCode : String
    protected val preferenceManager by lazy {
        (application as MedLarm).preferenceManager
    }
    protected val notificationManager by lazy {
        (application as MedLarm).notificationManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (preferenceManager.getCurrentLocale() == "ar")
              window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        else
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
    }

  override fun attachBaseContext(newBase: Context) {
      oldPrefLocaleCode = PreferenceManager(newBase).getCurrentLocale()
      applyOverrideConfiguration(LocaleManager.getLocalizedConfiguration(newBase, oldPrefLocaleCode))
      super.attachBaseContext(newBase)
  }

    override fun onResume() {
        val currentLocaleCode = PreferenceManager(this).getCurrentLocale()
        if(oldPrefLocaleCode != currentLocaleCode){
            recreate() //locale is changed, restart the activty to update
            oldPrefLocaleCode = currentLocaleCode
        }
        super.onResume()
    }
}