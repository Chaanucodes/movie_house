package com.trial.moviehouse.util

import java.text.SimpleDateFormat
import java.util.Locale

fun getReleaseDate(releaseDate : String?): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = releaseDate?.let { sdf.parse(it) }

    val outputFormatter = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
    return date?.let { outputFormatter.format(it) } ?: ""
}