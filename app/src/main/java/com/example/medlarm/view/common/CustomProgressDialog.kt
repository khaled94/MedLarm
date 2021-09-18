package com.example.medlarm.view.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.content.res.ResourcesCompat
import com.example.medlarm.R
import kotlinx.android.synthetic.main.custom_progess_dialog_view.view.*

class CustomProgressDialog (private val context: Context, private val title: String = "") {

    lateinit var dialog: CustomDialog

    @SuppressLint("InflateParams")
    fun init() {
        val inflater = (context as Activity).layoutInflater
        val view = inflater.inflate(R.layout.custom_progess_dialog_view, null)
        view.cp_title.text = title

        // Card Color
        view.cp_cardview.setCardBackgroundColor(null)

        // Progress Bar Color
        setColorFilter(view.cp_pbar.indeterminateDrawable, ResourcesCompat.getColor(context.resources, R.color.colorPrimary, null))

        // Text Color
        view.cp_title.setTextColor(Color.WHITE)

        dialog = CustomDialog(context)
        dialog.setContentView(view)
    }

    fun show(context: Context): Dialog {
        return show(context, null)
    }

    @SuppressLint("InflateParams")
    fun show(context: Context, title: CharSequence?): Dialog {
        val inflater = (context as Activity).layoutInflater
        val view = inflater.inflate(R.layout.custom_progess_dialog_view, null)
        if (title != null) {
            view.cp_title.text = title
        }

        // Card Color
        view.cp_cardview.setCardBackgroundColor(
            ResourcesCompat.getColor(
                context.resources,
                R.color.card_faded_black,
                null
            )
        )

        // Progress Bar Color
        setColorFilter(
            view.cp_pbar.indeterminateDrawable,
            ResourcesCompat.getColor(context.resources, R.color.colorPrimary, null)
        )

        // Text Color
        view.cp_title.setTextColor(Color.WHITE)

        dialog = CustomDialog(context)
        dialog.setContentView(view)
        dialog.show()
        return dialog
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    fun show() {
        return dialog.show()
    }

    fun dismiss() {
        dialog.hide()
    }

    class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
        init {
            // Set Semi-Transparent Color for Dialog Background
            window?.decorView?.rootView?.setBackgroundResource(R.color.transparent)
            window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
                insets.consumeSystemWindowInsets()
            }
        }
    }
}