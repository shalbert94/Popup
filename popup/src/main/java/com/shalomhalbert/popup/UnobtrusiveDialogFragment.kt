package com.shalomhalbert.popup

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.WindowManager

/**
 * An open class that acts like a popup window, and can be fully customized.
 */

open class UnobtrusiveDialogFragment : android.support.v4.app.DialogFragment() {

    companion object {
        val TAG: String = UnobtrusiveDialogFragment::class.java.simpleName
        const val TOP_MARGIN_OVERLAP_TOOLBAR = 25 //Translates to 25dp
        const val TOP_MARGIN_BELOW_TOOLBAR = 8
    }

    fun setDialogAttributes(overlapToolbar: Boolean) {
        //Removes dimming when dialog is drawn
        dialog.window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        //Removes background view inherent with DialogFragments
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //Moves dialog to top
        dialog.window.attributes.gravity = Gravity.TOP
        //Shifts dialog down
        if (overlapToolbar) {
            dialog.window.attributes.y = TOP_MARGIN_OVERLAP_TOOLBAR
        } else {
            dialog.window.attributes.y = TOP_MARGIN_BELOW_TOOLBAR + getActionBarSize()
        }
        //Tapping outside of this dialog won't dismiss it
        dialog.setCanceledOnTouchOutside(false)
        //Allows user to interact with views besides this DialogFragment
        dialog.window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager
            .LayoutParams.FLAG_NOT_TOUCH_MODAL)
    }

    private fun getActionBarSize(): Int {
        var actionBarHeight = 0

        // Calculate ActionBar height
        val tv = TypedValue()
        if (context?.theme?.resolveAttribute(android.R.attr.actionBarSize, tv, true)!!) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources
                .displayMetrics)
        }

        return actionBarHeight
    }
}