package com.leconcoin.test.albumstore.view


import android.os.Bundle
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.leconcoin.test.albumstore.R

abstract class BaseActivity(@LayoutRes private val layoutResourceId: Int) : AppCompatActivity() {

    /*
     * *********************************************************************************************
     * DATA
     * *********************************************************************************************
     */

    private var savedInstanceState: Bundle? = null


    /*
     * *********************************************************************************************
     * UI
     * *********************************************************************************************
     */


    var dialogProgress: AlertDialog? = null

    /*
     * *********************************************************************************************
     * LIFECYCLE METHODS
     * *********************************************************************************************
     */

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
        this.savedInstanceState = savedInstanceState

    }

    /*
    * *********************************************************************************************
    * PUBLIC METHODS
    * *********************************************************************************************
    */
    fun handleError(error: Throwable) {
        Toast.makeText(
            this,
            getString(R.string.common_error) + error.localizedMessage,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun showProgressDialog() {
        dismissProgressDialog()
        dialogProgress = ProgressDialogUtils.showProgressDialog(this, false)
        dialogProgress?.show()
    }

    fun dismissProgressDialog() {
        dialogProgress?.let {
            it.dismiss()
            dialogProgress = null
        }
    }
}
