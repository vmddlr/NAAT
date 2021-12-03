package com.example.naat.data.util

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.math.BigInteger
import java.security.AccessControlContext
import java.security.MessageDigest

class Utility {

    companion object {
        fun covertSHA256(text: String): String {
            return MessageDigest
                .getInstance("SHA-256")
                .digest(text.toByteArray())
                .fold("", { str, it -> str + "%02x".format(it) })
        }

        fun alertDialog(text: String, context: Context){
            MaterialAlertDialogBuilder(context)
                .setTitle("Alert")
                .setMessage(text)
                .setPositiveButton("Ok") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

}