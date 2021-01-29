package net.borlis.mapboxandroidapp

import android.content.Context
import androidx.appcompat.app.AlertDialog

class DialogManager {

    fun unexpectedErrorDialog(
        context: Context
    ): AlertDialog.Builder = AlertDialog.Builder(context)
        .setMessage(R.string.unexpected_error_text)
        .setPositiveButton(R.string.ok, null)
}