package com.trial.moviehouse.data.db

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore

import androidx.datastore.preferences.core.intPreferencesKey

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.trial.moviehouse.util.Constants.LOCAL_PREFERENCES
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private val CURRENT_SELECTED_THEME = intPreferencesKey("last_exam_correct_answers")
class LocalPreferences(
     private val context: Context
) {

    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
        name = LOCAL_PREFERENCES
    )

    suspend fun saveCurrentThemeSelection(theme: Int) {
        context.dataStore.edit { preferences ->
            preferences[CURRENT_SELECTED_THEME] = theme
        }
    }

    fun getCurrentThemeSelection(): Flow<Int> {
        return context.dataStore.data.map { preferences ->
            preferences[CURRENT_SELECTED_THEME] ?: AppCompatDelegate.MODE_NIGHT_NO
        }
    }
}

