package com.leconcoin.test.albumstore.view

import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.leconcoin.test.albumstore.R

class ProgressDialogUtils {

    companion object {

        fun showProgressDialog(activity: BaseActivity, cancelable: Boolean): AlertDialog? {
            val builder = AlertDialog.Builder(activity)
            val parent = LinearLayout(activity)
            parent.apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
            }
            parent.setPadding(36, 50, 36, 50)
            parent.addView(ProgressBar(activity))
            val textView = TextView(activity)
            textView.setPadding(36, 0, 0, 0)
            textView.text = activity.getString(R.string.common_loading)
            textView.gravity = Gravity.CENTER_VERTICAL
            parent.addView(textView)
            return builder.setView(parent)
                .setCancelable(cancelable)
                .create()
        }
    }
}