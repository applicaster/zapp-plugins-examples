package com.applicaster.iaptestplugin.view

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.util.Log
import com.applicaster.iaptestplugin.R

class ErrorDialog : DialogFragment() {

    private var errorMessage: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val extras: Bundle? = this.arguments
        errorMessage = extras?.get(KEY_ERROR_DIALOG_MESSAGE) as? String
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(errorMessage)
                .setTitle(R.string.title_error_dialog)
                .setNegativeButton(R.string.btn_error_dialog_ok) { _, _ -> }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    /**
     * Manually set message if [ErrorDialog] instance already created.
     * After calling this function just call [show] function.
     * @param message Message that will be shown bu this dialog
     */
    fun setMessage(message: String) {
        errorMessage = message
    }

    override fun show(manager: FragmentManager?, tag: String?) {
        try {
            val ft = manager?.beginTransaction()
            ft?.add(this, tag)
            ft?.commitAllowingStateLoss()
        } catch (ignored: IllegalStateException) {
            Log.e(ErrorDialog::class.java.simpleName, ignored.message)
        }
    }


    companion object {
        private const val KEY_ERROR_DIALOG_MESSAGE = "error_dialog_message"

        /**
         *  Creates a new instance of this dialog and returns it.
         *  @param message Message that will be shown by this dialog
         */
        fun newInstance(message: String): ErrorDialog {
            val dialog = ErrorDialog()
            val bundle = Bundle().apply {
                putString(KEY_ERROR_DIALOG_MESSAGE, message)
            }
            dialog.arguments = bundle
            return dialog
        }
    }
}
