package com.trial.moviehouse.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trial.moviehouse.data.db.LocalPreferences
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val dataPreferences: LocalPreferences
): ViewModel() {


}