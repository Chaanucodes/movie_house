package com.trial.moviehouse.data.repository

import com.trial.moviehouse.data.db.LocalPreferences
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val localPreferences: LocalPreferences
) {

    suspend fun saveThemePreferences(theme: Int) {
        localPreferences.saveCurrentThemeSelection(theme)
    }

    fun getCurrentThemeSelection() = localPreferences.getCurrentThemeSelection()
}