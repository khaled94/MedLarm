package com.example.medlarm.view.splash

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.medlarm.R
import com.example.medlarm.databinding.ActivitySplashBinding
import com.example.medlarm.view.common.BaseActivity
import com.example.medlarm.view.login.LoginActivity
import java.util.*

class SplashActivity :  BaseActivity<ActivitySplashBinding>() {

    private val _splashTimeOut: Long = 2000
    private lateinit var handler: Handler

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

    val myFadeInAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.splash_fading)
        binding.ivLogo.startAnimation(myFadeInAnimation)

        if(preferenceManager.getUserId() != 0)
                                         navigateToLogin()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val overlaySettings = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse(
                        "package:$packageName"
                    )
                )
                //   startActivityForResult(overlaySettings, 100)
                val startForResult =
                    registerForActivityResult(ActivityResultContracts.StartActivityForResult())
                    { result: ActivityResult ->
                        if (Settings.canDrawOverlays(this)) {
                           navigateToLogin()
                        }
                        //if (result.resultCode == Activity.RESULT_OK)
                    }
                startForResult.launch(overlaySettings)
            }
            else{
                navigateToLogin()
            }
        }
        else{
            navigateToLogin()
        }
                //}
    }

    private fun navigateToLogin(){
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed(
            {
                //calendar.timeInMillis,
                setAlarm(Calendar.getInstance().timeInMillis,-3)
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            },
            _splashTimeOut
        )
    }
}

/*
   /*if ("xiaomi" == Build.MANUFACTURER.toLowerCase(Locale.ROOT)) {
                    Log.e("test","first")
                    val intent = Intent("miui.intent.action.APP_PERM_EDITOR")
                    intent.setClassName(
                        "com.miui.securitycenter",
                        "com.miui.permcenter.permissions.PermissionsEditorActivity"
                    )
                    intent.putExtra("extra_pkgname", packageName)
                    AlertDialog.Builder(this)
                        .setTitle("Please Enable the additional permissions")
                        .setMessage("You will not receive notifications while the app is in background if you disable these permissions")
                        .setPositiveButton("Go to Settings",
                            DialogInterface.OnClickListener { dialog, which -> startActivity(intent) })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setCancelable(false)
                        .show()
                    Log.e("test","third")
                } else {*/
 */