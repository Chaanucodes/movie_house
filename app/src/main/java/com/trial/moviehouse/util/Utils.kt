package com.trial.moviehouse.util

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.trial.moviehouse.R
import java.text.SimpleDateFormat
import java.util.Locale

fun getReleaseDate(releaseDate : String?): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = releaseDate?.let { sdf.parse(it) }

    val outputFormatter = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
    return date?.let { outputFormatter.format(it) } ?: ""
}

fun View.showSnackBar(message: String) {

    val snackBar = Snackbar.make(
        this,
        message,
        Snackbar.LENGTH_LONG
    )
        snackBar.setAction("OK") {
        snackBar.dismiss() }.setActionTextColor(ContextCompat.getColor(context, R.color.secondary))
        .show()
}